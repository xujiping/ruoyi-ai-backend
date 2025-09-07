package org.ruoyi.personal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优先级枚举
 *
 * @author ruoyi
 */
@Getter
@AllArgsConstructor
public enum PriorityLevelEnum {

    /**
     * 高优先级
     */
    HIGH(1, "高"),

    /**
     * 中优先级
     */
    MEDIUM(2, "中"),

    /**
     * 低优先级
     */
    LOW(3, "低");

    private final Integer value;
    private final String desc;

    /**
     * 根据值获取枚举
     */
    public static PriorityLevelEnum getByValue(Integer value) {
        for (PriorityLevelEnum priority : values()) {
            if (priority.getValue().equals(value)) {
                return priority;
            }
        }
        return null;
    }
}