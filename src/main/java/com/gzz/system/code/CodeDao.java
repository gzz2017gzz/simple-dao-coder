package com.gzz.system.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.gzz.system.code.model.CodeCond;
import com.gzz.system.code.model.Field;
import com.gzz.system.code.model.Table;
import com.gzz.system.meta.Meta;
import com.simple.common.base.BaseDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 数据访问类（支持 MySQL / MariaDB / PostgreSQL / Oracle / SQL Server）
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Slf4j
@Repository
public class CodeDao extends BaseDao<Meta> {

    /** 当前数据库类型，由配置项 custom.meta-database 指定，默认 mysql */
    @Value("${custom.meta-database:mysql}")
    private String metaDatabase;

    /**
     * 查询表名列表
     */
    public List<Table> tables(CodeCond cond) {
        String sql = switch (metaDatabase.toLowerCase()) {
            case "postgresql" -> "SELECT table_name, CAST(obj_description((quote_ident(table_schema)||'.'||quote_ident(table_name))::regclass, 'pg_class') AS varchar) AS comment FROM information_schema.tables"
                    + cond.where().replaceFirst("(?i)(AND table_schema = \\?)", "AND table_schema = ?");
            case "oracle" -> "SELECT table_name, CAST(comments AS VARCHAR2(4000)) AS comment FROM user_tab_comments"
                    + cond.where().replaceFirst("(?i)(AND table_schema = \\?)", "").replaceFirst("(?i)(AND table_name LIKE \\?)", "AND table_name LIKE ?");
            case "sqlserver" -> "SELECT table_name, CAST(ISNULL(ep.value, '') AS NVARCHAR(4000)) AS comment FROM information_schema.tables t LEFT JOIN sys.extended_properties ep ON ep.major_id = OBJECT_ID(t.table_name) AND ep.name = 'MS_Description' AND ep.minor_id = 0"
                    + cond.where();
            default -> "SELECT table_name, IF(table_comment='', table_name, table_comment) comment FROM information_schema.tables"
                    + cond.where();
        };
        return list(sql, Table.class, cond.array());
    }

    /**
     * 查询字段名列表
     */
    public List<Field> fields(CodeCond cond) {
        StringBuilder sb = new StringBuilder();
        String sql = switch (metaDatabase.toLowerCase()) {
            case "postgresql" -> {
                sb.append("SELECT column_name AS name, CASE WHEN col_description((quote_ident(table_schema)||'.'||quote_ident(table_name))::regclass, ordinal_position) IS NULL THEN column_name ELSE col_description((quote_ident(table_schema)||'.'||quote_ident(table_name))::regclass, ordinal_position) END AS comment, CASE WHEN data_type IN ('character varying', 'text', 'char', 'varchar') THEN 'String'");
                sb.append(" WHEN data_type = 'smallint' THEN 'Short'");
                sb.append(" WHEN data_type = 'integer' THEN 'Integer'");
                sb.append(" WHEN data_type = 'time without time zone' THEN 'LocalTime'");
                sb.append(" WHEN data_type = 'date' THEN 'LocalDate'");
                sb.append(" WHEN data_type IN ('timestamp without time zone', 'timestamp with time zone') THEN 'LocalDateTime'");
                sb.append(" WHEN data_type = 'bigint' THEN 'Long'");
                sb.append(" WHEN data_type = 'real' THEN 'Float'");
                sb.append(" WHEN data_type = 'bytea' THEN 'Byte[]'");
                sb.append(" WHEN data_type = 'double precision' THEN 'Double'");
                sb.append(" WHEN data_type = 'numeric' THEN 'BigDecimal'");
                sb.append(" WHEN data_type = 'boolean' THEN 'Boolean'");
                sb.append(" ELSE CONCAT('DATA TYPE IS ERROR', data_type) END AS type, character_maximum_length AS length FROM information_schema.columns");
                yield sb.toString();
            }
            case "oracle" -> {
                sb.append("SELECT column_name AS name, CASE WHEN comments IS NULL THEN column_name ELSE comments END AS comment, CASE WHEN data_type IN ('VARCHAR2', 'CHAR', 'NVARCHAR2', 'CLOB') THEN 'String'");
                sb.append(" WHEN data_type = 'NUMBER' AND data_scale = 0 AND data_precision <= 2 THEN 'Byte'");
                sb.append(" WHEN data_type = 'NUMBER' AND data_scale = 0 AND data_precision <= 4 THEN 'Short'");
                sb.append(" WHEN data_type = 'NUMBER' AND data_scale = 0 AND data_precision <= 9 THEN 'Integer'");
                sb.append(" WHEN data_type = 'NUMBER' AND data_scale = 0 THEN 'Long'");
                sb.append(" WHEN data_type = 'NUMBER' THEN 'BigDecimal'");
                sb.append(" WHEN data_type = 'DATE' THEN 'LocalDateTime'");
                sb.append(" WHEN data_type = 'TIMESTAMP(6)' THEN 'LocalDateTime'");
                sb.append(" WHEN data_type = 'FLOAT' THEN 'Float'");
                sb.append(" WHEN data_type = 'BLOB' THEN 'Byte[]'");
                sb.append(" ELSE CONCAT('DATA TYPE IS ERROR', data_type) END AS type, data_length AS length FROM user_tab_columns");
                yield sb.toString();
            }
            case "sqlserver" -> {
                sb.append("SELECT column_name AS name, CASE WHEN CAST(ISNULL(ep.value, '') AS NVARCHAR(4000)) = '' THEN column_name ELSE CAST(ISNULL(ep.value, '') AS NVARCHAR(4000)) END AS comment, CASE WHEN data_type IN ('varchar', 'text', 'char', 'nvarchar', 'ntext') THEN 'String'");
                sb.append(" WHEN data_type = 'tinyint' THEN 'Byte'");
                sb.append(" WHEN data_type = 'smallint' THEN 'Short'");
                sb.append(" WHEN data_type = 'int' THEN 'Integer'");
                sb.append(" WHEN data_type = 'time' THEN 'LocalTime'");
                sb.append(" WHEN data_type = 'date' THEN 'LocalDate'");
                sb.append(" WHEN data_type IN ('datetime', 'datetime2', 'smalldatetime') THEN 'LocalDateTime'");
                sb.append(" WHEN data_type = 'bigint' THEN 'Long'");
                sb.append(" WHEN data_type = 'real' THEN 'Float'");
                sb.append(" WHEN data_type = 'varbinary' THEN 'Byte[]'");
                sb.append(" WHEN data_type = 'float' THEN 'Double'");
                sb.append(" WHEN data_type IN ('decimal', 'numeric', 'money', 'smallmoney') THEN 'BigDecimal'");
                sb.append(" WHEN data_type = 'bit' THEN 'Boolean'");
                sb.append(" ELSE CONCAT('DATA TYPE IS ERROR', data_type) END AS type, character_maximum_length AS length FROM information_schema.columns c LEFT JOIN sys.extended_properties ep ON ep.major_id = OBJECT_ID(c.table_name) AND ep.minor_id = c.ordinal_position AND ep.name = 'MS_Description'");
                yield sb.toString();
            }
            default -> {
                sb.append("SELECT column_name name, CASE WHEN column_comment = '' THEN column_name ELSE column_comment END comment, CASE WHEN data_type='varchar' OR data_type='text' OR data_type='char' OR data_type='longtext' OR data_type='mediumtext' THEN 'String'");
                sb.append(" WHEN data_type = 'tinyint' THEN 'Byte'");
                sb.append(" WHEN data_type = 'smallint' THEN 'Short'");
                sb.append(" WHEN data_type = 'int' OR data_type = 'mediumint' THEN 'Integer'");
                sb.append(" WHEN data_type = 'time' THEN 'LocalTime'");
                sb.append(" WHEN data_type = 'date' THEN 'LocalDate'");
                sb.append(" WHEN data_type = 'datetime' OR data_type = 'timestamp' THEN 'LocalDateTime'");
                sb.append(" WHEN data_type = 'bigint' THEN 'Long'");
                sb.append(" WHEN data_type = 'float' THEN 'Float'");
                sb.append(" WHEN data_type = 'longblob' OR data_type = 'blob' OR data_type = 'tinyblob' OR data_type = 'mediumblob' THEN 'Byte[]'");
                sb.append(" WHEN data_type = 'double' THEN 'Double'");
                sb.append(" WHEN data_type = 'decimal' THEN 'BigDecimal'");
                sb.append(" WHEN data_type = 'boolean' OR data_type = 'bit' THEN 'Boolean'");
                sb.append(" ELSE CONCAT ('DATA TYPE IS ERROR', data_type) END type,character_maximum_length length FROM information_schema.columns");
                yield sb.toString();
            }
        };
        sb.setLength(0);
        sb.append(sql);
        sb.append(cond.where());
        sb.append(" ORDER BY ordinal_position");
        return list(sb.toString(), Field.class, cond.array());
    }

    /**
     * 查询当前数据库名
     */
    public String dbName() {
        return switch (metaDatabase.toLowerCase()) {
            case "postgresql" -> field("SELECT current_database()", String.class);
            case "oracle" -> field("SELECT ORA_DATABASE_NAME FROM DUAL", String.class);
            case "sqlserver" -> field("SELECT DB_NAME()", String.class);
            default -> field("SELECT DATABASE()", String.class);
        };
    }

    /**
     * 查询全部数据库名
     */
    public List<String> databases() {
        return switch (metaDatabase.toLowerCase()) {
            case "postgresql" -> {
                List<String> dbs = columns("SELECT datname FROM pg_catalog.pg_database WHERE datistemplate = false", String.class);
                dbs.removeIf(db -> db.equals("postgres"));
                yield dbs;
            }
            case "oracle" -> columns("SELECT username FROM all_users", String.class);
            case "sqlserver" -> columns("SELECT name FROM sys.databases", String.class);
            default -> columns("SHOW DATABASES", String.class);
        };
    }
}