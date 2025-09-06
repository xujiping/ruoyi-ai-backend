package org.ruoyi.generator.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.generator.domain.SchemaGroup;

import java.io.Serializable;

/**
 * 数据模型分组业务对象 SchemaGroupBo
 *
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@AutoMapper(target = SchemaGroup.class, reverseConvertGenerate = false)
public class SchemaGroupBo implements Serializable {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 分组名称
     */
    @NotBlank(message = "分组名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 分组编码
     */
    @NotBlank(message = "分组编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

}