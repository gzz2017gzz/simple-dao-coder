-- ============================================================
-- simple-dao-coder 元数据表 DDL —— SQL Server 版
-- 与 schema-mysql.sql 语义严格对齐
-- ============================================================

IF OBJECT_ID('coder_meta', 'U') IS NULL
BEGIN
    CREATE TABLE coder_meta (
      id            bigint       IDENTITY(1,1) PRIMARY KEY,
      database_name nvarchar(50),
      table_name    nvarchar(50),
      field_name    nvarchar(50),
      fill_type     tinyint,
      dict_key      nvarchar(50),
      show_column   tinyint,
      create_time   datetime,
      create_by     nvarchar(50)
    );
    EXEC sp_addextendedproperty 'MS_Description', '元数据',   'SCHEMA', 'dbo', 'TABLE', 'coder_meta';
    EXEC sp_addextendedproperty 'MS_Description', '主键',     'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'id';
    EXEC sp_addextendedproperty 'MS_Description', '数据库名', 'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'database_name';
    EXEC sp_addextendedproperty 'MS_Description', '表名',     'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'table_name';
    EXEC sp_addextendedproperty 'MS_Description', '字段名',   'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'field_name';
    EXEC sp_addextendedproperty 'MS_Description', '填充类型', 'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'fill_type';
    EXEC sp_addextendedproperty 'MS_Description', '字典关键字','SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'dict_key';
    EXEC sp_addextendedproperty 'MS_Description', '表格列是否显示','SCHEMA','dbo','TABLE','coder_meta','COLUMN','show_column';
    EXEC sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'create_time';
    EXEC sp_addextendedproperty 'MS_Description', '创建人IP', 'SCHEMA', 'dbo', 'TABLE', 'coder_meta', 'COLUMN', 'create_by';
END

IF OBJECT_ID('coder_server', 'U') IS NULL
BEGIN
    CREATE TABLE coder_server (
      id          bigint       IDENTITY(1,1) PRIMARY KEY,
      name        nvarchar(50),
      remark      nvarchar(50),
      create_time datetime,
      create_by   nvarchar(50)
    );
    EXEC sp_addextendedproperty 'MS_Description', '服务名', 'SCHEMA', 'dbo', 'TABLE', 'coder_server';
    EXEC sp_addextendedproperty 'MS_Description', '主键',   'SCHEMA', 'dbo', 'TABLE', 'coder_server', 'COLUMN', 'id';
    EXEC sp_addextendedproperty 'MS_Description', '名称',   'SCHEMA', 'dbo', 'TABLE', 'coder_server', 'COLUMN', 'name';
    EXEC sp_addextendedproperty 'MS_Description', '备注',   'SCHEMA', 'dbo', 'TABLE', 'coder_server', 'COLUMN', 'remark';
    EXEC sp_addextendedproperty 'MS_Description', '创建时间','SCHEMA', 'dbo', 'TABLE', 'coder_server', 'COLUMN', 'create_time';
    EXEC sp_addextendedproperty 'MS_Description', '创建人',  'SCHEMA', 'dbo', 'TABLE', 'coder_server', 'COLUMN', 'create_by';
END

-- 清空并插入初始数据（SQL Server 插入自增列需先开启 IDENTITY_INSERT）
DELETE FROM coder_server;
SET IDENTITY_INSERT coder_server ON;
INSERT INTO coder_server (id, name, remark, create_time, create_by) VALUES (1, '',          'NONE',    '2024-02-21 23:04:23', 'gzz');
INSERT INTO coder_server (id, name, remark, create_time, create_by) VALUES (2, '/sys_rbac', 'sys_rbac','2024-02-21 23:02:23', 'gzz');
INSERT INTO coder_server (id, name, remark, create_time, create_by) VALUES (3, '/equ',      'equ',     '2024-02-21 23:03:29', 'gzz');
INSERT INTO coder_server (id, name, remark, create_time, create_by) VALUES (4, '/keybox',   'keybox',  '2024-02-21 23:05:41', 'gzz');
SET IDENTITY_INSERT coder_server OFF;
