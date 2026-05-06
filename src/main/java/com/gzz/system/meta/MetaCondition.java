package com.gzz.system.meta;

import com.simple.common.base.BaseCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @summary 【元数据】查询条件
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaCondition extends BaseCondition {

	/**
	 * 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("database_name =", databaseName);
		and("table_name =", tableName);
	}

	/* 默认条件↓ */
	private String databaseName; /* 数据库名 */
	private String tableName; /* 表名 */
}
