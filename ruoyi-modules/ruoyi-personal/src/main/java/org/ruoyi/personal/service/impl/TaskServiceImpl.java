package org.ruoyi.personal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.common.core.exception.ServiceException;
import org.ruoyi.common.core.utils.MapstructUtils;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.common.satoken.utils.LoginHelper;
import org.ruoyi.personal.domain.TaskInfo;
import org.ruoyi.personal.domain.bo.TaskBo;
import org.ruoyi.personal.domain.bo.TaskQueryBo;
import org.ruoyi.personal.domain.bo.TaskCreateBo;
import org.ruoyi.personal.domain.bo.TaskUpdateBo;
import org.ruoyi.personal.domain.vo.TaskTreeVo;
import org.ruoyi.personal.domain.vo.TaskVo;
import org.ruoyi.personal.enums.TaskStatusEnum;
import org.ruoyi.personal.enums.TaskTypeEnum;
import org.ruoyi.personal.mapper.TaskInfoMapper;
import org.ruoyi.personal.service.ITaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskInfoMapper baseMapper;

    /**
     * 查询任务信息
     */
    @Override
    public TaskVo queryById(Long taskId) {
        return baseMapper.selectTaskWithUsers(taskId);
    }

    /**
     * 查询任务信息列表
     */
    @Override
    public TableDataInfo<TaskVo> queryPageList(TaskQueryBo queryBo, PageQuery pageQuery) {
        LambdaQueryWrapper<TaskInfo> lqw = buildQueryWrapper(queryBo);
        Page<TaskVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询任务信息列表
     */
    @Override
    public List<TaskVo> queryList(TaskQueryBo queryBo) {
        LambdaQueryWrapper<TaskInfo> lqw = buildQueryWrapper(queryBo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询任务树形结构
     */
    @Override
    public List<TaskTreeVo> queryTaskTree(TaskQueryBo queryBo) {
        List<TaskVo> taskList = queryList(queryBo);
        return buildTaskTree(taskList, 0L);
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<TaskInfo> buildQueryWrapper(TaskQueryBo queryBo) {
        LambdaQueryWrapper<TaskInfo> lqw = Wrappers.lambdaQuery();
        
        // 权限控制：个人任务只能查看自己相关的任务
        Long currentUserId = LoginHelper.getUserId();
        if (TaskTypeEnum.PERSONAL.getValue().equals(queryBo.getTaskType())) {
            lqw.and(wrapper -> wrapper
                .eq(TaskInfo::getAssignedUserId, currentUserId)
                .or()
                .eq(TaskInfo::getCreatorUserId, currentUserId)
            );
        }
        
        lqw.eq(queryBo.getParentId() != null, TaskInfo::getParentId, queryBo.getParentId());
        lqw.eq(StringUtils.isNotBlank(queryBo.getTaskType()), TaskInfo::getTaskType, queryBo.getTaskType());
        lqw.like(StringUtils.isNotBlank(queryBo.getTaskTitle()), TaskInfo::getTaskTitle, queryBo.getTaskTitle());
        lqw.eq(StringUtils.isNotBlank(queryBo.getTaskStatus()), TaskInfo::getTaskStatus, queryBo.getTaskStatus());
        lqw.eq(queryBo.getPriorityLevel() != null, TaskInfo::getPriorityLevel, queryBo.getPriorityLevel());
        lqw.eq(queryBo.getAssignedUserId() != null, TaskInfo::getAssignedUserId, queryBo.getAssignedUserId());
        
        // 时间范围查询
        lqw.ge(queryBo.getPlannedStartTimeBegin() != null, TaskInfo::getPlannedStartTime, queryBo.getPlannedStartTimeBegin());
        lqw.le(queryBo.getPlannedStartTimeEnd() != null, TaskInfo::getPlannedStartTime, queryBo.getPlannedStartTimeEnd());
        lqw.ge(queryBo.getPlannedEndTimeBegin() != null, TaskInfo::getPlannedEndTime, queryBo.getPlannedEndTimeBegin());
        lqw.le(queryBo.getPlannedEndTimeEnd() != null, TaskInfo::getPlannedEndTime, queryBo.getPlannedEndTimeEnd());
        
        lqw.orderByDesc(TaskInfo::getCreateTime);
        
        return lqw;
    }

    /**
     * 构建任务树形结构
     */
    private List<TaskTreeVo> buildTaskTree(List<TaskVo> taskList, Long parentId) {
        return taskList.stream()
            .filter(task -> Objects.equals(task.getParentId(), parentId))
            .map(task -> {
                TaskTreeVo treeVo = BeanUtil.copyProperties(task, TaskTreeVo.class);
                treeVo.setChildren(buildTaskTree(taskList, task.getTaskId()));
                return treeVo;
            })
            .collect(Collectors.toList());
    }

    /**
     * 新增任务信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertTask(TaskCreateBo createBo) {
        TaskInfo add = MapstructUtils.convert(createBo, TaskInfo.class);
        validEntityBeforeSave(add);
        
        // 设置默认值
        if (add.getParentId() == null) {
            add.setParentId(0L);
        }
        add.setTaskStatus(TaskStatusEnum.PENDING.getValue()); // 默认待处理状态
        if (add.getPriorityLevel() == null) {
            add.setPriorityLevel(3); // 默认低优先级
        }
        add.setTaskProgress(0); // 默认进度为0
        if (add.getAssignedUserId() == null) {
            add.setAssignedUserId(LoginHelper.getUserId());
        }
        add.setCreatorUserId(LoginHelper.getUserId()); // 系统自动设置创建者
        
        boolean flag = baseMapper.insert(add) > 0;
        return flag;
    }

    /**
     * 修改任务信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTask(TaskUpdateBo updateBo) {
        TaskInfo update = MapstructUtils.convert(updateBo, TaskInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 更新任务状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTaskStatus(Long taskId, String taskStatus) {
        // 验证任务状态
        TaskStatusEnum statusEnum = TaskStatusEnum.getByValue(taskStatus);
        if (statusEnum == null) {
            throw new ServiceException("无效的任务状态");
        }
        
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(taskId);
        taskInfo.setTaskStatus(taskStatus);
        
        // 如果任务变为进行中，设置实际开始时间
        if (TaskStatusEnum.IN_PROGRESS.getValue().equals(taskStatus)) {
            taskInfo.setActualStartTime(new Date());
        }
        // 如果任务完成，设置实际结束时间和进度为100%
        else if (TaskStatusEnum.COMPLETED.getValue().equals(taskStatus)) {
            taskInfo.setActualEndTime(new Date());
            taskInfo.setTaskProgress(100);
        }
        
        return baseMapper.updateById(taskInfo) > 0;
    }

    /**
     * 更新任务进度
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTaskProgress(Long taskId, Integer taskProgress) {
        if (taskProgress < 0 || taskProgress > 100) {
            throw new ServiceException("任务进度必须在0-100之间");
        }
        
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(taskId);
        taskInfo.setTaskProgress(taskProgress);
        
        // 如果进度为100%，自动设置为已完成
        if (taskProgress == 100) {
            taskInfo.setTaskStatus(TaskStatusEnum.COMPLETED.getValue());
            taskInfo.setActualEndTime(new Date());
        }
        
        return baseMapper.updateById(taskInfo) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TaskInfo entity) {
        // 验证任务类型约束
        validateTaskTypeConstraint(entity);
        
        // 验证任务状态
        if (StringUtils.isNotBlank(entity.getTaskStatus())) {
            TaskStatusEnum statusEnum = TaskStatusEnum.getByValue(entity.getTaskStatus());
            if (statusEnum == null) {
                throw new ServiceException("无效的任务状态");
            }
        }
        
        // 验证任务类型
        if (StringUtils.isNotBlank(entity.getTaskType())) {
            TaskTypeEnum typeEnum = TaskTypeEnum.getByValue(entity.getTaskType());
            if (typeEnum == null) {
                throw new ServiceException("无效的任务类型");
            }
        }
        
        // 验证优先级
        if (entity.getPriorityLevel() != null && (entity.getPriorityLevel() < 1 || entity.getPriorityLevel() > 3)) {
            throw new ServiceException("优先级必须在1-3之间");
        }
        
        // 验证进度
        if (entity.getTaskProgress() != null && (entity.getTaskProgress() < 0 || entity.getTaskProgress() > 100)) {
            throw new ServiceException("任务进度必须在0-100之间");
        }
        
        // 验证时间逻辑
        if (entity.getPlannedStartTime() != null && entity.getPlannedEndTime() != null) {
            if (entity.getPlannedStartTime().after(entity.getPlannedEndTime())) {
                throw new ServiceException("计划开始时间不能晚于计划结束时间");
            }
        }
    }

    /**
     * 验证任务类型约束
     */
    private void validateTaskTypeConstraint(TaskInfo entity) {
        if (entity.getParentId() != null && entity.getParentId() > 0) {
            TaskInfo parentTask = baseMapper.selectById(entity.getParentId());
            if (parentTask != null && !parentTask.getTaskType().equals(entity.getTaskType())) {
                throw new ServiceException("任务类型与父任务类型不一致");
            }
        }
    }

    /**
     * 批量删除任务信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 检查是否有子任务
            for (Long id : ids) {
                Long childCount = baseMapper.selectChildCountByParentId(id);
                if (childCount > 0) {
                    throw new ServiceException("存在子任务，无法删除");
                }
            }
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}