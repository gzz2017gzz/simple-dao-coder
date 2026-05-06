package com.gzz.common.utils;

import static com.gzz.common.config.Const.COUNT;
import static com.gzz.common.config.Const.EMPTY;
import static com.gzz.common.config.Const.INT_0;
import static com.gzz.common.config.Const.INT_1;

import java.util.ArrayList;
import java.util.List;

import com.gzz.system.code.model.Field;

/**
 * @author 高振中
 * @summary 代码生成工具类
 * @date 2026-05-05 11:25:52
 **/
public final class CodeTools {
	private CodeTools() { }

	public static String header(final List<Field> fields) {
		return String.join(",", fields.stream().map(i -> "\"" + i.getComment() + "\"").toList());
	}

	/**
	 * 把组数拼接成(?, ?, ?)的形式
	 */
	public static String question(final Object... ids) {
		return "?" + ",?".repeat(ids.length - INT_1);
	}

	public static StringBuilder insertParams(final List<Field> fields, final String prefix, final String suffix, final String wrap) {
		StringBuilder sb = new StringBuilder();

		for (int i = INT_0; i < fields.size(); i++) {
			Field field = fields.get(i);
			sb.append((i != INT_0 && i % COUNT == INT_0) ? wrap : EMPTY);
			sb.append(prefix.concat(field.getLower()).concat(suffix));
		}
		return sb.delete(sb.length() - INT_1, sb.length());
	}

	public static StringBuilder insertFields(final List<Field> fields, final String prefix, final String suffix, final String wrap) {
		StringBuilder sb = new StringBuilder();
		for (int i = INT_0; i < fields.size(); i++) {
			Field field = fields.get(i);
			sb.append((i != INT_0 && i % COUNT == INT_0) ? wrap : EMPTY);
			sb.append(prefix.concat(field.getName()).concat(suffix));
		}
		return sb.delete(sb.length() - INT_1, sb.length());
	}

	public static String insertProps(final List<Field> fields, final String prefix, final String suffix) {
		return String.join(suffix, fields.stream().map(field -> prefix.concat(field.getUpper()).concat("()")).toList());
	}

	public static String updateProps(final List<Field> fields, final String prefix, final String suffix) {
		List<Field> temps = new ArrayList<>(fields);
		temps.add(fields.getFirst());
		temps.removeFirst();
		return String.join(suffix, temps.stream().map(field -> prefix.concat(field.getUpper()).concat("()")).toList());
	}

	public static StringBuilder updateFields(final List<Field> fields, final String prefix, final String suffix, final String wrap) {
		List<Field> temps = new ArrayList<>(fields);
		temps.removeFirst();
		StringBuilder sb = new StringBuilder();
		for (int i = INT_0; i < temps.size(); i++) {
			Field field = fields.get(i);
			sb.append((i != INT_0 && i % COUNT == INT_0) ? wrap : EMPTY);
			sb.append(prefix.concat(field.getName()).concat(suffix));
		}
		return sb.delete(sb.length() - INT_1, sb.length());
	}

	public static StringBuilder updateParams(final List<Field> fields, final String wrap) {
		List<Field> temps = new ArrayList<>(fields);
		temps.removeFirst();
		StringBuilder sb = new StringBuilder();
		for (int i = INT_0; i < temps.size(); i++) {
			Field field = fields.get(i);
			sb.append((i != INT_0 && i % COUNT == INT_0) ? wrap : EMPTY);
			sb.append(field.getName().concat("=:" + field.getLower() + ","));
		}
		return sb.delete(sb.length() - INT_1, sb.length());
	}

	/**
	 * 实体类文件中是否增加java.util.Date的导入
	 */

	public static String importDate(final List<Field> fields) {
		return (fields.parallelStream().anyMatch(i -> "BigDecimal".equals(i.getType())) ? "\r\nimport java.math.BigDecimal;" : "")
				+ (fields.parallelStream().anyMatch(i -> "LocalDateTime".equals(i.getType())) ? "\r\nimport java.time.LocalDateTime;" : "")
				+ (fields.parallelStream().anyMatch(i -> "LocalTime".equals(i.getType())) ? "\r\nimport java.time.LocalTime;" : "") 
				+ (fields.parallelStream().anyMatch(i -> "LocalDate".equals(i.getType())) ? "\r\nimport java.time.LocalDate;" : "");
	}
	/**
	 * 主键数据类型
	 */
	public static String keyType(final List<Field> fields) {
		return fields.getFirst().getType();
	}

	public static String data(final String type) {
		return switch (type) {
		case "Byte" -> "Byte.valueOf(\"1\")";
		case "Short" -> "Short.valueOf(\"1\")";
		case "Integer" -> "1";
		case "LocalTime" -> "LocalTime.now()";
		case "LocalDate" -> "LocalDate.now()";
		case "LocalDateTime" -> "LocalDateTime.now()";
		case "Long" -> "1L";
		case "Float" -> "1F";
		case "Byte[]" -> "\"1\".getBytes()";
		case "Double" -> "1D";
		case "BigDecimal" -> "BigDecimal.valueOf(0)";
		case "Boolean" -> "true";
		case "String" -> "\"1\"";
		default -> "\"Unknown_data_type\"";
		};
	}
}
