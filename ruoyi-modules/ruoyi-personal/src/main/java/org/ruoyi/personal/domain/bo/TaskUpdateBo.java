package org.ruoyi.personal.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * 任务更新业务对象
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Data
public class TaskUpdateBo {

    /**
     * 任务ID
     */
    @NotNull(message = "任务ID不能为空")
    private Long taskId;

    /**
     * 父任务ID，0表示顶级任务
     */
    private Long parentId;

    /**
     * 任务类型：personal/work
     */
    @NotBlank(message = "任务类型不能为空")
    private String taskType;

    /**
     * 任务标题
     */
    @NotBlank(message = "任务标题不能为空")
    @Size(max = 200, message = "任务标题长度不能超过200个字符")
    private String taskTitle;

    /**
     * 任务详细内容
     */
    private String taskContent;

    /**
     * 任务状态：pending/in_progress/completed/cancelled
     */
    private String taskStatus;

    /**
     * 优先级：1高 2中 3低
     */
    private Integer priorityLevel;

    /**
     * 计划开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedStartTime;

    /**
     * 计划结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedEndTime;

    /**
     * 实际开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualEndTime;

    /**
     * 任务进度百分比
     */
    private Integer taskProgress;

    /**
     * 任务负责人
     */
    private Long assignedUserId;

    /**
     * 备注信息
     */
    @Size(max = 500, message = "备注信息长度不能超过500个字符")
    private String remark;
}