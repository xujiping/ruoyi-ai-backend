package org.ruoyi.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态枚举
 *
 * @author ruoyi
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

    /**
     * 待处理
     */
    PENDING("pending", "待处理"),

    /**
     * 进行中
     */
    IN_PROGRESS("in_progress", "进行中"),

    /**
     * 已完成
     */
    COMPLETED("completed", "已完成"),

    /**
     * 已取消
     */
    CANCELLED("cancelled", "已取消");

    private final String value;
    private final String desc;

    /**
     * 根据值获取枚举
     */
    public static TaskStatusEnum getByValue(String value) {
        for (TaskStatusEnum taskStatus : values()) {
            if (taskStatus.getValue().equals(value)) {
                return taskStatus;
            }
        }
        return null;
    }
}