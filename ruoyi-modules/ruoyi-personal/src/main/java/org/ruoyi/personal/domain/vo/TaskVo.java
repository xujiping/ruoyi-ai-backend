package org.ruoyi.personal.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.personal.domain.TaskInfo;
import org.ruoyi.personal.enums.PriorityLevelEnum;
import org.ruoyi.personal.enums.TaskStatusEnum;
import org.ruoyi.personal.enums.TaskTypeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 任务信息视图对象 task_info
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TaskInfo.class)
public class TaskVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @ExcelProperty(value = "任务ID")
    private Long taskId;

    /**
     * 父任务ID，0表示顶级任务
     */
    @ExcelProperty(value = "父任务ID")
    private Long parentId;

    /**
     * 父任务标题
     */
    @ExcelProperty(value = "父任务标题")
    private String parentTitle;

    /**
     * 任务类型：personal/work
     */
    @ExcelProperty(value = "任务类型")
    private String taskType;

    /**
     * 任务类型名称
     */
    @ExcelProperty(value = "任务类型名称")
    private String taskTypeName;

    /**
     * 任务标题
     */
    @ExcelProperty(value = "任务标题")
    private String taskTitle;

    /**
     * 任务详细内容
     */
    @ExcelProperty(value = "任务详细内容")
    private String taskContent;

    /**
     * 任务状态：pending/in_progress/completed/cancelled
     */
    @ExcelProperty(value = "任务状态")
    private String taskStatus;

    /**
     * 任务状态名称
     */
    @ExcelProperty(value = "任务状态名称")
    private String taskStatusName;

    /**
     * 优先级：1高 2中 3低
     */
    @ExcelProperty(value = "优先级")
    private Integer priorityLevel;

    /**
     * 优先级名称
     */
    @ExcelProperty(value = "优先级名称")
    private String priorityLevelName;

    /**
     * 计划开始时间
     */
    @ExcelProperty(value = "计划开始时间")
    private Date plannedStartTime;

    /**
     * 计划结束时间
     */
    @ExcelProperty(value = "计划结束时间")
    private Date plannedEndTime;

    /**
     * 实际开始时间
     */
    @ExcelProperty(value = "实际开始时间")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @ExcelProperty(value = "实际结束时间")
    private Date actualEndTime;

    /**
     * 任务进度百分比
     */
    @ExcelProperty(value = "任务进度百分比")
    private Integer taskProgress;

    /**
     * 任务负责人
     */
    @ExcelProperty(value = "任务负责人")
    private Long assignedUserId;

    /**
     * 负责人姓名
     */
    @ExcelProperty(value = "负责人姓名")
    private String assignedUserName;

    /**
     * 任务创建人
     */
    @ExcelProperty(value = "任务创建人")
    private Long creatorUserId;

    /**
     * 创建人姓名
     */
    @ExcelProperty(value = "创建人姓名")
    private String creatorUserName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注信息")
    private String remark;

    /**
     * 获取任务类型名称
     */
    public String getTaskTypeName() {
        if (this.taskType == null) {
            return null;
        }
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.getByValue(this.taskType);
        return taskTypeEnum != null ? taskTypeEnum.getDesc() : this.taskType;
    }

    /**
     * 获取任务状态名称
     */
    public String getTaskStatusName() {
        if (this.taskStatus == null) {
            return null;
        }
        TaskStatusEnum taskStatusEnum = TaskStatusEnum.getByValue(this.taskStatus);
        return taskStatusEnum != null ? taskStatusEnum.getDesc() : this.taskStatus;
    }

    /**
     * 获取优先级名称
     */
    public String getPriorityLevelName() {
        if (this.priorityLevel == null) {
            return null;
        }
        PriorityLevelEnum priorityLevelEnum = PriorityLevelEnum.getByValue(this.priorityLevel);
        return priorityLevelEnum != null ? priorityLevelEnum.getDesc() : String.valueOf(this.priorityLevel);
    }
}