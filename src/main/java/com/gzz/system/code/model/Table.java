package com.gzz.system.code.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @summary 表名描述
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
public final class Table {
	private String tableName;// 表名
	private String comment;// 表名注释
	private String className;// 类名(首字母大写)
	private String fullName;// 完整类名(首字母大写)
	private String cnName;// 类中文名
}
