package com.gzz.common.utils;

import static com.gzz.common.config.Const.COUNT;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.gzz.system.code.model.Field;

/**
 * @author 高振中
 * @summary 代码生成工具类
 * @date 2026-09-04 16:32:28
 **/
public final class CodeTools {
    private CodeTools() {
    }

    public static String header(final List<Field> fields) {
        return String.join(",", fields.stream().map(i -> "\"" + i.getComment() + "\"").toList());
    }

    /**
     * 把组数拼接成(?, ?, ?)的形式
     */
    public static String question(final Object... ids) {
        return String.join(",", Collections.nCopies(ids.length, "?"));
    }

    public static StringBuilder insertParams(final List<Field> fields, final String prefix, final String suffix, final String wrap) {
        return new StringBuilder(IntStream.range(0, fields.size())
                .mapToObj(i -> (i != 0 && i % COUNT == 0 ? wrap : "") + prefix + fields.get(i).getLower())
                .collect(Collectors.joining(suffix)));
    }

    public static StringBuilder insertFields(final List<Field> fields, final String prefix, final String suffix, final String wrap) {
        return new StringBuilder(IntStream.range(0, fields.size())
                .mapToObj(i -> (i != 0 && i % COUNT == 0 ? wrap : "") + prefix + fields.get(i).getName())
                .collect(Collectors.joining(suffix)));
    }

    public static String insertProps(final List<Field> fields, final String prefix, final String suffix) {
        return String.join(suffix, fields.stream().map(field -> prefix.concat(field.getUpper()).concat("()")).toList());
    }

    public static String updateProps(final List<Field> fields, final String prefix, final String suffix) {
        return Stream.concat(fields.stream().skip(1), fields.stream().limit(1))
                .map(field -> prefix + field.getUpper() + "()")
                .collect(Collectors.joining(suffix));
    }

    public static StringBuilder updateFields(final List<Field> fields, final String prefix, final String suffix, final String wrap) {
        return new StringBuilder(IntStream.range(1, fields.size())
                .mapToObj(i -> (i != 1 && (i - 1) % COUNT == 0 ? wrap : "") + prefix + fields.get(i).getName())
                .collect(Collectors.joining(suffix)));
    }

    public static StringBuilder updateParams(final List<Field> fields, final String wrap) {
        return new StringBuilder(IntStream.range(1, fields.size())
                .mapToObj(i -> (i != 1 && (i - 1) % COUNT == 0 ? wrap : "") + fields.get(i).getName() + "=:" + fields.get(i).getLower())
                .collect(Collectors.joining(",")));
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
