package com.gzz.system.code.model;

import java.util.List;
import java.util.Set;

import com.simple.common.base.BaseCondition;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 高振中
 * @summary 查询条件
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
public final class CodeCond extends BaseCondition {
    @Override
    public void addCondition() {
        add("AND table_schema = ?", dbName);
        add("AND table_name = ?", tableName);
        add("AND table_name LIKE ?", tableNameLike, 3);
        add("AND table_name NOT IN", excludes);
        add("AND table_name not like 'sys_%'", !sys);
    }

    private String dbName;// 数据库名
    private String tableName;// 表名等于
    private String tableNameLike;// 表名等于
    private Object[] excludes;// 排除表集
    private String company;// 公司名
    private String itemName;// 项目名
    private String model;// 模块名
    private String author;// 作者名
    private String serverName;// 服务名
    private List<Table> tables;// 生成代码用的表集合数据
    private int swagger;// 使用
    private Boolean hidden;// 隐藏字段
    private Boolean columns;// 自定义列显示
    private int log;// 生成日志注解
    private int excel;// 生成导出excel
    private Set<String> codeType;// 模板类型
    private Boolean sys = false;
}
