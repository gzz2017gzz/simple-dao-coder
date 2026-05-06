package com.gzz.system.meta;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.common.base.BaseDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【元数据】数据访问层
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Slf4j
@Repository
public class MetaDao extends BaseDao<Meta> {

	/**
	 * 【元数据】列表
	 */
	public List<Meta> list(MetaCondition cond) {
		String sql = "SELECT t.id,t.database_name,t.table_name,t.field_name,t.fill_type,t.dict_key,t.show_column,t.create_time,t.create_by FROM coder_meta t";
		return list(sql + cond.where(), Meta.class, cond.array());
	}

	/**
	 * 【元数据】批量插入
	 */
	public void insertBatch(List<Meta> metas) {
        String sql = "INSERT INTO coder_meta (database_name,table_name,field_name,fill_type,dict_key,show_column,create_time,create_by)" +
                " VALUES (:databaseName,:tableName,:fieldName,:fillType,:dictKey,:showColumn,:createTime,:createBy)";
		batchOperate(metas, sql);
	}

	/**
	 * 【元数据】逻辑删除
	 */
	public int delete(MetaCondition cond) {
		String sql = "DELETE FROM coder_meta t" + cond.where();
		return update(sql, cond.array());
	}

	/**
	 * 【服务名】列表
	 */
	public List<Server> list() {
		String sql = "SELECT t.id,t.name,t.remark,t.create_time,t.create_by FROM coder_server t";
		log.info(sql);// 显示SQL语句
		return list(sql, Server.class);
	}
}
