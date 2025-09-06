package org.ruoyi.generator.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.ruoyi.core.mapper.BaseMapperPlus;
import org.ruoyi.generator.domain.SchemaField;
import org.ruoyi.generator.domain.vo.SchemaFieldVo;

/**
 * 数据模型字段Mapper接口
 *
 * @author ruoyi
 * @date 2024-01-01
 */
@Mapper
public interface SchemaFieldMapper extends BaseMapperPlus<SchemaField, SchemaFieldVo> {

}