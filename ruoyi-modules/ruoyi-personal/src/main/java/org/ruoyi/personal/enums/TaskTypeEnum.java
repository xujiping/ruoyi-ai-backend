package org.ruoyi.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型枚举
 *
 * @author ruoyi
 */
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    /**
     * 个人任务
     */
    PERSONAL("personal", "个人任务"),

    /**
     * 工作任务
     */
    WORK("work", "工作任务");

    private final String value;
    private final String desc;

    /**
     * 根据值获取枚举
     */
    public static TaskTypeEnum getByValue(String value) {
        for (TaskTypeEnum taskType : values()) {
            if (taskType.getValue().equals(value)) {
                return taskType;
            }
        }
        return null;
    }
}