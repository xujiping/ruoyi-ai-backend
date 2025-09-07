package org.ruoyi.personal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.core.mapper.BaseMapperPlus;
import org.ruoyi.personal.domain.TaskInfo;
import org.ruoyi.personal.domain.vo.TaskVo;

import java.util.List;

/**
 * 任务信息Mapper接口
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Mapper
public interface TaskInfoMapper extends BaseMapperPlus<TaskInfo, TaskVo> {

    /**
     * 查询任务详情，包含用户信息
     *
     * @param taskId 任务ID
     * @return 任务详情
     */
    TaskVo selectTaskWithUsers(@Param("taskId") Long taskId);

    /**
     * 查询任务列表，包含用户信息
     *
     * @param taskInfo 查询条件
     * @return 任务列表
     */
    List<TaskVo> selectTaskListWithUsers(TaskInfo taskInfo);

    /**
     * 查询指定用户的任务树形结构
     *
     * @param taskType 任务类型
     * @param userId 用户ID
     * @return 任务树形结构
     */
    List<TaskVo> selectTaskTreeByUser(@Param("taskType") String taskType, @Param("userId") Long userId);

    /**
     * 查询子任务数量
     *
     * @param parentId 父任务ID
     * @return 子任务数量
     */
    Long selectChildCountByParentId(@Param("parentId") Long parentId);
}