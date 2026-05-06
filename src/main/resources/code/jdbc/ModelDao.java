package ${packageName};

import java.util.List;

import org.springframework.stereotype.Repository;

import com.${company}.common.base.BaseDao;
import com.${company}.common.base.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【${cnName}】数据访问层
 * @author ${author}
 * @date ${time}
 **/
@Slf4j
@Repository
public class ${ClassName}Dao extends BaseDao {

	/**
	 * 【${cnName}】新增
	 */
	public int save(${ClassName} po) {
		String sql ="""
		INSERT INTO ${tableName} (${insertFields})
		VALUES (${insertValues})
		""";
		Object[] param = {${insertParams} };
		log.info(sql(sql, param));// 显示SQL语句
		return update(sql, param);
	}

	/**
	 * 【${cnName}】删除
	 */
	public int delete(Object[] ids) {
		String sql = "DELETE FROM ${tableName} WHERE ${id_name} IN" + toIn(ids);
		log.info(sql(sql, ids));// 显示SQL语句
		return update(sql, ids);
	}

	/**
	 * 【${cnName}】修改
	 */
	public int update(${ClassName} po) {
		String sql ="""
		UPDATE ${tableName} SET ${updateFields}
		WHERE ${id_name}=?
		""";
		Object[] param = {${updateValues} };
		log.info(sql(sql, param));// 显示SQL语句
		return update(sql, param);
	}

	/**
	 * 【${cnName}】分页列表
	 */
	public Page<${ClassName}> page(${ClassName}Condition cond) {
		String sql ="""
		SELECT ${selectFields}
		FROM ${tableName} t
		""";
		log.info(sql(sql + cond.where(), cond.array()));// 显示SQL语句
		return page(sql + cond.where(), cond, ${ClassName}.class);
	}

	/**
	 * 【${cnName}】列表
	 */
	public List<${ClassName}> list(${ClassName}Condition cond) {
		String sql ="""
		SELECT ${selectFields}
		FROM ${tableName} t
		""";
		log.info(sql(sql + cond.where(),cond.array()));//显示SQL语句
		return list(sql + cond.where(), ${ClassName}.class, cond.array());
	}

	/**
	 * 【${cnName}】按主键查
	 */
	public ${ClassName} findById(${idType} id) {
		String sql ="""
		SELECT ${selectFields}
		FROM ${tableName} t WHERE t.${id_name}=?
		""";
		log.info(sql(sql,id));//显示SQL语句
		return findById(sql, ${ClassName}.class, id);
	}

	/**
	 * 【${cnName}】个数
	 */
	public int count(${ClassName}Condition cond) {
		String sql = "SELECT COUNT(1) FROM ${tableName} t " + cond.where();
		log.info(sql(sql,cond.array()));//显示SQL语句
		return count(sql, cond);
	}

	/**
	 * 【${cnName}】新增并返回自增涨主键
	 */
	public int saveReturnPK(${ClassName} ${className}) {
		String sql ="""
		INSERT INTO ${tableName} (${insertFields})
		VALUES (${insertParamsBatch})
		""";
		log.info(sql(sql, ${className}));// 显示SQL语句
		return saveKey(${className}, sql, "${id_name}");
	}

	/**
	 * 【${cnName}】批量插入
	 */
	public int[] insertBatch(List<${ClassName}> ${className}s) {
		String sql="""
		INSERT INTO ${tableName} (${insertFields})
		VALUES (${insertParamsBatch})
		""";
		log.info(sqlp(sql, ${className}s));// 显示SQL语句
		return batchOperate(${className}s, sql);
	}
	/**
	 * 【${cnName}】批量更新
	 */
	public int[] updateBatch(List<${ClassName}> ${className}s) {
		String sql ="""
		UPDATE ${tableName} SET ${updateParamsBatch}
		WHERE ${id_name}=:${idName}
		""";
		log.info(sqlp(sql, ${className}s));// 显示SQL语句
		return batchOperate(${className}s, sql);
	}
	/**
	 * 【${cnName}】逻辑删除
	 */
	public int deleteDr(Object... ids) {
		String sql = "UPDATE ${tableName} SET dr=1 WHERE ${id_name} IN " + toIn(ids);
		return update(sql,ids);
	}	

}