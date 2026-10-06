-- ============================================================
-- simple-dao-coder 元数据表 DDL —— Oracle 版
-- 与 schema-mysql.sql 语义严格对齐
-- 说明：Oracle 12c+ 可用 IDENTITY 列；此处用 NUMBER + 序列兼容更低版本
-- ============================================================

CREATE TABLE coder_meta (
  id            NUMBER       NOT NULL,
  database_name VARCHAR2(50),
  table_name    VARCHAR2(50),
  field_name    VARCHAR2(50),
  fill_type     NUMBER(3),
  dict_key      VARCHAR2(50),
  show_column   NUMBER(3),
  create_time   DATE,
  create_by     VARCHAR2(50),
  CONSTRAINT pk_coder_meta PRIMARY KEY (id)
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

CREATE TABLE coder_server (
  id          NUMBER     NOT NULL,
  name        VARCHAR2(50),
  remark      VARCHAR2(50),
  create_time DATE,
  create_by   VARCHAR2(50),
  CONSTRAINT pk_coder_server PRIMARY KEY (id)
);
COMMENT ON TABLE  coder_server IS '服务名';
COMMENT ON COLUMN coder_server.id          IS '主键';
COMMENT ON COLUMN coder_server.name        IS '名称';
COMMENT ON COLUMN coder_server.remark      IS '备注';
COMMENT ON COLUMN coder_server.create_time IS '创建时间';
COMMENT ON COLUMN coder_server.create_by   IS '创建人';

-- 序列（若使用 12c+ 可改为 id NUMBER GENERATED ALWAYS AS IDENTITY）
CREATE SEQUENCE seq_coder_meta   START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_coder_server START WITH 5 INCREMENT BY 1;

-- 清空并插入初始数据（Oracle 日期字面量用 TO_DATE）
DELETE FROM coder_server;

INSERT INTO coder_server VALUES (1, '',          'NONE',    TO_DATE('2024-02-21 23:04:23', 'yyyy-mm-dd hh24:mi:ss'), 'gzz');
INSERT INTO coder_server VALUES (2, '/sys_rbac', 'sys_rbac',TO_DATE('2024-02-21 23:02:23', 'yyyy-mm-dd hh24:mi:ss'), 'gzz');
INSERT INTO coder_server VALUES (3, '/equ',      'equ',     TO_DATE('2024-02-21 23:03:29', 'yyyy-mm-dd hh24:mi:ss'), 'gzz');
INSERT INTO coder_server VALUES (4, '/keybox',   'keybox',  TO_DATE('2024-02-21 23:05:41', 'yyyy-mm-dd hh24:mi:ss'), 'gzz');
COMMIT;
