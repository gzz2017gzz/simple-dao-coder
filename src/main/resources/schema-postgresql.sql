-- ============================================================
-- simple-dao-coder 元数据表 DDL —— PostgreSQL 版
-- 与 schema-mysql.sql 语义严格对齐
-- ============================================================

CREATE TABLE IF NOT EXISTS coder_meta (
  id            BIGSERIAL          PRIMARY KEY,
  database_name varchar(50),
  table_name    varchar(50),
  field_name    varchar(50),
  fill_type     smallint,
  dict_key      varchar(50),
  show_column   smallint,
  create_time   timestamp,
  create_by     varchar(50)
);
COMMENT ON TABLE  coder_meta        IS '元数据';
COMMENT ON COLUMN coder_meta.id            IS '主键';
COMMENT ON COLUMN coder_meta.database_name IS '数据库名';
COMMENT ON COLUMN coder_meta.table_name    IS '表名';
COMMENT ON COLUMN coder_meta.field_name    IS '字段名';
COMMENT ON COLUMN coder_meta.fill_type     IS '填充类型';
COMMENT ON COLUMN coder_meta.dict_key      IS '字典关键字';
COMMENT ON COLUMN coder_meta.show_column   IS '表格列是否显示';
COMMENT ON COLUMN coder_meta.create_time   IS '创建时间';
COMMENT ON COLUMN coder_meta.create_by     IS '创建人IP';

CREATE TABLE IF NOT EXISTS coder_server (
  id          BIGSERIAL    PRIMARY KEY,
  name        varchar(50),
  remark      varchar(50),
  create_time timestamp,
  create_by   varchar(50)
);
COMMENT ON TABLE  coder_server IS '服务名';
COMMENT ON COLUMN coder_server.id          IS '主键';
COMMENT ON COLUMN coder_server.name        IS '名称';
COMMENT ON COLUMN coder_server.remark      IS '备注';
COMMENT ON COLUMN coder_server.create_time IS '创建时间';
COMMENT ON COLUMN coder_server.create_by   IS '创建人';

-- 重置序列后插入（确保 id 从 1 开始且与 MySQL 版数据一致）
DELETE FROM coder_server;

-- BIGSERIAL 关联的序列名默认是：表名_列名_seq
ALTER SEQUENCE coder_server_id_seq RESTART WITH 1;

INSERT INTO coder_server VALUES (1, '',          'NONE',    '2024-02-21 23:04:23', 'gzz');
INSERT INTO coder_server VALUES (2, '/sys_rbac', 'sys_rbac','2024-02-21 23:02:23', 'gzz');
INSERT INTO coder_server VALUES (3, '/equ',      'equ',     '2024-02-21 23:03:29', 'gzz');
INSERT INTO coder_server VALUES (4, '/keybox',   'keybox',  '2024-02-21 23:05:41', 'gzz');
