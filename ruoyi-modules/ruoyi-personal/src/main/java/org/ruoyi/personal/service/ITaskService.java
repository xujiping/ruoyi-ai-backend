package org.ruoyi.personal.service;

import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.personal.domain.bo.TaskBo;
import org.ruoyi.personal.domain.vo.TaskTreeVo;
import org.ruoyi.personal.domain.vo.TaskVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务信息Service接口
 *
 * @author ruoyi
 * @date 2025-01-07
 */
public interface ITaskService {

    /**
     * 查询任务信息
     *
     * @param taskId 任务ID
     * @return 任务信息
     */
    TaskVo queryById(Long taskId);

    /**
     * 查询任务信息列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询
     * @return 任务信息分页列表
     */
    TableDataInfo<TaskVo> queryPageList(TaskBo bo, PageQuery pageQuery);

    /**
     * 查询任务信息列表
     *
     * @param bo 查询条件
     * @return 任务信息列表
     */
    List<TaskVo> queryList(TaskBo bo);

    /**
     * 查询任务树形结构
     *
     * @param bo 查询条件
     * @return 任务树形结构
     */
    List<TaskTreeVo> queryTaskTree(TaskBo bo);

    /**
     * 新增任务信息
     *
     * @param bo 任务信息业务对象
     * @return 新增结果
     */
    Boolean insertTask(TaskBo bo);

    /**
     * 修改任务信息
     *
     * @param bo 任务信息业务对象
     * @return 修改结果
     */
    Boolean updateTask(TaskBo bo);

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param taskStatus 新状态
     * @return 更新结果
     */
    Boolean updateTaskStatus(Long taskId, String taskStatus);

    /**
     * 更新任务进度
     *
     * @param taskId 任务ID
     * @param taskProgress 进度百分比
     * @return 更新结果
     */
    Boolean updateTaskProgress(Long taskId, Integer taskProgress);

    /**
     * 校验并批量删除任务信息
     *
     * @param ids 任务ID集合
     * @param isValid 是否校验
     * @return 删除结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}