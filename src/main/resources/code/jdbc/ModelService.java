package ${packageName};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.${company}.common.base.Page;
<#if excel == 1>
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
</#if>
/**
 * @summary 【${cnName}】业务逻辑层
 * @author ${author}
 * @date ${time}
 **/
//@Slf4j
@Service
public class ${ClassName}Service {

	@Autowired
	private ${ClassName}Dao ${className}Dao; // 注入【${cnName}】数据访问层

	/**
	 * 【${cnName}】新增并反回主键
	 */
	// @Transactional
	public int save(${ClassName} ${className}) {
		return ${className}Dao.save(${className});
	}

	/**
	 * 【${cnName}】删除
	 */
	public int delete(Object[] ids) {
		return ${className}Dao.delete(ids);
	}

	/**
	 * 【${cnName}】修改
	 */
	public int update(${ClassName} ${className}) {
		return ${className}Dao.update(${className});
	}

	/**
	 * 【${cnName}】分页列表
	 */
	public Page<${ClassName}> page(${ClassName}Condition condition) {
		return ${className}Dao.page(condition);
	}

	/**
	 * 【${cnName}】列表
	 */
	public List<${ClassName}> list(${ClassName}Condition condition) {
		return ${className}Dao.list(condition);
	}

	/**
	 * 【${cnName}】按主键查
	 */
	public ${ClassName} findById(${idType} id) {
		return ${className}Dao.findById(id);
	}

	/**
	 * 【${cnName}】个数
	 */
	public int count(${ClassName}Condition condition) {
		return ${className}Dao.count(condition);
	}
	/**
	 * 【${cnName}】批量保存与更新
	 */
	public void saveBatch(List<${ClassName}> ${className}s) {
		${className}Dao.insertBatch(${className}s);
	}	
	<#if excel == 1>
	/**
	 * @方法说明 【${cnName}】导出
	 */
	public HSSFWorkbook export(${ClassName}Condition cond){
		return ${ClassName}Excel.bookFile(${className}Dao.list(cond));
	}
	/**
	 * @方法说明 【${cnName}】导入
	 */
	public void importExcel(MultipartFile file){
		${className}Dao.insertBatch(${ClassName}Excel.list(file));
	}
	</#if>
}