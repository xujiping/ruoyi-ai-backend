package org.ruoyi.personal.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.tenant.core.TenantEntity;
import org.ruoyi.personal.domain.TaskInfo;

import java.util.Date;

/**
 * 任务信息业务对象 task_info
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TaskInfo.class, reverseConvertGenerate = false)
public class TaskBo extends TenantEntity {

    /**
     * 任务ID
     */
    @NotNull(message = "任务ID不能为空", groups = { EditGroup.class })
    private Long taskId;

    /**
     * 父任务ID，0表示顶级任务
     */
    private Long parentId;

    /**
     * 任务类型：personal/work
     */
    @NotBlank(message = "任务类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String taskType;

    /**
     * 任务标题
     */
    @NotBlank(message = "任务标题不能为空", groups = { AddGroup.class, EditGroup.class })
    @Size(max = 200, message = "任务标题长度不能超过200个字符", groups = { AddGroup.class, EditGroup.class })
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
     * 任务创建人
     */
    private Long creatorUserId;

    /**
     * 备注信息
     */
    @Size(max = 500, message = "备注信息长度不能超过500个字符", groups = { AddGroup.class, EditGroup.class })
    private String remark;
}