package org.ruoyi.personal.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 任务树形结构视图对象
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskTreeVo extends TaskVo {

    /**
     * 子任务列表
     */
    private List<TaskTreeVo> children;
}