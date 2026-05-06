package com.gzz.system.code.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @summary 字段描述
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
public final class Field {
	private String name;// 字段名
	private String comment;// 注释
	private String type;// 数据类型
	private Long length; // 字符型的长度
	private String upper; // 首字母大写
	private String lower; // 首字母小写
	private String data; // 测试数据
	private Byte showColumn = 1;// 表格列 1显示2隐藏
	private Byte fillType = 1; // 填写类型
	private String dictKey; // 字典关键字
	// fillTypes:  0-隐藏列,1-输入框,2-文本域,3-字典下拉,4-日期框;
}
