package org.ruoyi.personal.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 任务信息对象 task_info
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("task_info")
public class TaskInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "task_id")
    private Long taskId;

    /**
     * 父任务ID，0表示顶级任务
     */
    private Long parentId;

    /**
     * 任务类型：personal/work
     */
    private String taskType;

    /**
     * 任务标题
     */
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
    private Date plannedStartTime;

    /**
     * 计划结束时间
     */
    private Date plannedEndTime;

    /**
     * 实际开始时间
     */
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
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
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注信息
     */
    private String remark;
}