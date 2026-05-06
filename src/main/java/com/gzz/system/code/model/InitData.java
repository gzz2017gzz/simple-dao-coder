package com.gzz.system.code.model;

import java.util.List;

import com.gzz.system.meta.Server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @summary 初始信息
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
@AllArgsConstructor
public class InitData {
	private List<String> databases;// 全部数据库
	private String dbName;// 当前数据库
	private List<Server> servers;// 服务列表
}
