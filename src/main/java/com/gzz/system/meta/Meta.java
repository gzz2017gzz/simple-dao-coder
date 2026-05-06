package com.gzz.system.meta;

import java.time.LocalDateTime;

import com.simple.common.base.annotation.Id;
import com.simple.common.base.annotation.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @summary 【元数据】实体
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
@Table("coder_meta")
public class Meta {
	// 字段↓
	@Id
	private Long id; /* 主键 */
	private String databaseName; /* 数据库名 */
	private String tableName; /* 表名 */
	private String fieldName; /* 字段名 */
	private Byte fillType; /* 填充类型 */
	private String dictKey; /* 字典关键字 */
	private Byte showColumn;/* 表格列是否显示 */
	private LocalDateTime createTime; /* 创建时间 */
	private String createBy; /* 创建人IP */
}
