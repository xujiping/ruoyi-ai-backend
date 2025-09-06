package org.ruoyi.generator.controller;

import cn.hutool.core.net.URLDecoder;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.generator.service.IGenTableService;
import org.ruoyi.generator.service.SchemaFieldService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * 代码生成 操作处理
 *
 * @author Lion Li
 */
@Profile("dev")
@RequiredArgsConstructor
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    private final IGenTableService genTableService;
    private final SchemaFieldService schemaFieldService;

    /**
     * 根据表名获取代码生成元数据-前端代码生成
     *
     * @param tableName 表名
     */
    @GetMapping("/getByTableName")
    public R<Object> getByTableName(@NotNull(message = "表名不能为空") String tableName) {
        return R.ok(schemaFieldService.getMetaDataByTableName(tableName));
    }

    /**
     * 生成后端代码
     *
     * @param tableNameStr 表名
     */
    @GetMapping("/batchGenCode")
    public R<String> batchGenCode(@NotNull(message = "表名不能为空") String tableNameStr) {
        genTableService.generateCodeToClasspathByTableNames(tableNameStr);
        return R.ok("代码生成成功");
    }

    /**
     * 生成前端代码
     *
     * @param workPath    执行命令路径
     * @param previewCode 执行生成前端文件命令
     */
    @GetMapping("/batchGenFrontendCode")
    public R<String> batchGenFrontendCode(@NotNull(message = "路径不能为空") String workPath, @NotNull(message = "指令不能为空") String previewCode) {
        String decodedWorkPath = URLDecoder.decode(workPath, StandardCharsets.UTF_8);
        String decodedPreviewCode = URLDecoder.decode(previewCode, StandardCharsets.UTF_8);
        genTableService.generateFrontendTemplateFiles(decodedWorkPath, decodedPreviewCode);
        return R.ok("代码生成成功");
    }
}
