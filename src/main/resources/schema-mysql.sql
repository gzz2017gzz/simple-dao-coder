-- create by: GaoZhenZhong Date: 20/02/2024 17:22:54
CREATE TABLE IF NOT EXISTS `coder_meta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `database_name` varchar(50) COMMENT '数据库名',
  `table_name` varchar(50) COMMENT '表名',
  `field_name` varchar(50) COMMENT '字段名',
  `fill_type` tinyint COMMENT '填充类型',
  `dict_key` varchar(50) COMMENT '字典关键字',
  `show_column` tinyint COMMENT '表格列是否显示',
  `create_time` datetime COMMENT '创建时间',
  `create_by` varchar(50) COMMENT '创建人IP',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT = 1 COMMENT = '元数据';

CREATE TABLE IF NOT EXISTS `coder_server`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COMMENT '名称',
  `remark` varchar(50) COMMENT '备注',
  `create_time` datetime COMMENT '创建时间',
  `create_by` varchar(50) COMMENT '创建人',
  PRIMARY KEY (`id`)
) COMMENT = '服务名';
DELETE FROM coder_server;
INSERT INTO `coder_server` VALUES (1, '', 'NONE', '2024-02-21 23:04:23', 'gzz');
INSERT INTO `coder_server` VALUES (2, '/sys_rbac', 'sys_rbac', '2024-02-21 23:02:23', 'gzz');
INSERT INTO `coder_server` VALUES (3, '/equ', 'equ', '2024-02-21 23:03:29', 'gzz');
INSERT INTO `coder_server` VALUES (4, '/keybox', 'keybox', '2024-02-21 23:05:41', 'gzz');