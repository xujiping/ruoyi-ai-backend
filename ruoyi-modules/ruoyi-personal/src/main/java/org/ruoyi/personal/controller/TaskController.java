package org.ruoyi.personal.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.excel.utils.ExcelUtil;
import org.ruoyi.common.idempotent.annotation.RepeatSubmit;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.personal.domain.bo.TaskBo;
import org.ruoyi.personal.domain.vo.TaskTreeVo;
import org.ruoyi.personal.domain.vo.TaskVo;
import org.ruoyi.personal.service.ITaskService;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务信息
 *
 * @author ruoyi
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/personal/task")
public class TaskController extends BaseController {

    private final ITaskService taskService;

    /**
     * 查询任务信息列表
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:list')")
    @GetMapping("/list")
    public TableDataInfo<TaskVo> list(TaskBo bo, PageQuery pageQuery) {
        return taskService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询任务树形结构
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:list')")
    @GetMapping("/tree")
    public R<List<TaskTreeVo>> tree(TaskBo bo) {
        List<TaskTreeVo> list = taskService.queryTaskTree(bo);
        return R.ok(list);
    }

    /**
     * 导出任务信息列表
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:export')")
    @Log(title = "任务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TaskBo bo, HttpServletResponse response) {
        List<TaskVo> list = taskService.queryList(bo);
        ExcelUtil.exportExcel(list, "任务信息", TaskVo.class, response);
    }

    /**
     * 获取任务信息详细信息
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:query')")
    @GetMapping("/{taskId}")
    public R<TaskVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long taskId) {
        return R.ok(taskService.queryById(taskId));
    }

    /**
     * 新增任务信息
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:add')")
    @Log(title = "任务信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TaskBo bo) {
        return toAjax(taskService.insertTask(bo));
    }

    /**
     * 修改任务信息
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:edit')")
    @Log(title = "任务信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TaskBo bo) {
        return toAjax(taskService.updateTask(bo));
    }

    /**
     * 更新任务状态
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:edit')")
    @Log(title = "更新任务状态", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/{taskId}/status")
    public R<Void> updateStatus(@NotNull(message = "任务ID不能为空") @PathVariable Long taskId,
                               @NotEmpty(message = "任务状态不能为空") @RequestParam String taskStatus) {
        return toAjax(taskService.updateTaskStatus(taskId, taskStatus));
    }

    /**
     * 更新任务进度
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:edit')")
    @Log(title = "更新任务进度", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/{taskId}/progress")
    public R<Void> updateProgress(@NotNull(message = "任务ID不能为空") @PathVariable Long taskId,
                                 @NotNull(message = "任务进度不能为空") @RequestParam Integer taskProgress) {
        return toAjax(taskService.updateTaskProgress(taskId, taskProgress));
    }

    /**
     * 删除任务信息
     */
    // @PreAuthorize("@ss.hasPermi('personal:task:remove')")
    @Log(title = "任务信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] taskIds) {
        return toAjax(taskService.deleteWithValidByIds(List.of(taskIds), true));
    }
}