package ${packageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hq.common.base.Page;
<#if excel == 1>
import com.alibaba.excel.EasyExcel;
import org.springframework.web.multipart.MultipartFile;
import lombok.SneakyThrows;
</#if>
import java.util.List;
/**
 * @类说明 【${cnName}】业务逻辑层
 * @author ${author}
 * @date ${time}
 **/
//@Slf4j
@Service
public class ${ClassName}Service {

	@Autowired
	private ${ClassName}Dao ${className}Dao; // 注入【${cnName}】数据访问层

	/**
	 * @方法说明 【${cnName}】新增并反回主键
	 */
	// @Transactional
	public ${idType} save(${ClassName} ${className}) {
		return ${className}Dao.save(${className}).get${IdName}();
	}

	/**
	 * @方法说明 【${cnName}】删除
	 */
	public int delete(Object[] ids) {
		return ${className}Dao.delete(ids);
	}

	/**
	 * @方法说明 【${cnName}】修改
	 */
	public int update(${ClassName} ${className}) {
		return ${className}Dao.update(${className});
	}

	/**
	 * @方法说明 【${cnName}】分页列表
	 */
	public Page<${ClassName}> page(${ClassName}Cond cond) {
		return ${className}Dao.page(cond);
	}

	/**
	 * @方法说明 【${cnName}】列表
	 */
	public List<${ClassName}> list(${ClassName}Cond cond) {
		return ${className}Dao.list(cond);
	}

	/**
	 * @方法说明 【${cnName}】按主键查
	 */
//	public ${ClassName} findOne(${idType} id) {
//		return ${className}Dao.findById(id);
//	}

	/**
	 * @方法说明 【${cnName}】个数
	 */
//	public int count(${ClassName}Cond cond) {
//		return ${className}Dao.count(cond);
//	}
	
	/**
	 * @方法说明 【${cnName}】是否存在
	 */
//	public boolean exists(${ClassName}Cond cond) {
//		return ${className}Dao.exists(cond);
//	}
	/**
	 * @方法说明 【${cnName}】批量保存与更新
	 */
//	public void saveBatch(List<${ClassName}> ${className}s) {
//		${className}Dao.saveBatch(${className}s);
//	}	
<#if excel == 1>
	/**
	 * @方法说明 【${cnName}】导入
	 */
	@SneakyThrows
	public void importExcel(MultipartFile file){
		List<${ClassName}> list = EasyExcel.read(file.getInputStream()).head(${ClassName}.class).sheet().doReadSync();
		${className}Dao.saveBatch(list);
	}
</#if>
}