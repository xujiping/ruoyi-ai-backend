package org.ruoyi.personal.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 任务查询业务对象
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Data
public class TaskQueryBo {

    /**
     * 父任务ID，0表示顶级任务
     */
    private Long parentId;

    /**
     * 任务类型：personal/work
     */
    private String taskType;

    /**
     * 任务标题（支持模糊查询）
     */
    private String taskTitle;

    /**
     * 任务状态：pending/in_progress/completed/cancelled
     */
    private String taskStatus;

    /**
     * 优先级：1高 2中 3低
     */
    private Integer priorityLevel;

    /**
     * 计划开始时间（查询范围开始）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedStartTimeBegin;

    /**
     * 计划开始时间（查询范围结束）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedStartTimeEnd;

    /**
     * 计划结束时间（查询范围开始）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedEndTimeBegin;

    /**
     * 计划结束时间（查询范围结束）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedEndTimeEnd;

    /**
     * 任务负责人
     */
    private Long assignedUserId;
}