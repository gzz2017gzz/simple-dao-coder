package ${packageName};

<#if excel == 1>
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;
import static com.hq.common.tools.ExcelTools.export;
import org.springframework.http.HttpEntity;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.hq.common.config.Result.error;
import static com.hq.common.config.Result.success;
<#if log == 1>
import com.hq.common.aop.AutoLog;
</#if>
import com.hq.common.base.Page;
import com.hq.common.config.Result;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
</#if>
<#if swagger != 1>

/**
 * @类说明 【${cnName}】控制器
 * @author ${author}
 * @date ${time}
 **/
</#if>
//@Slf4j
@RestController
@RequestMapping("${className}")
<#if swagger == 1>
@Tag(name = "【${cnName}】API", description = "【${cnName}】接口")
</#if>
public class ${ClassName}Controller {

	@Autowired
	private ${ClassName}Service ${className}Service; // 注入【${cnName}】业务逻辑层

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】新增
	 */
</#if>
	@PostMapping("save")
<#if swagger == 1>
	@Operation(summary = "【${cnName}】新增", description = "【${cnName}】新增")
</#if>
<#if log == 1>
	@AutoLog("【${cnName}】新增")
</#if>
	public Result<${idType}> save(@RequestBody @Validated ${ClassName} ${className}) {
		// if (${className}Service.count(${ClassName}Cond.builder().nameEq(${className}.getName()).build()) > 0)
		// return error(1, "【${cnName}名称】不能重复！");
		return success(${className}Service.save(${className}));
	}

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】删除
	 */
</#if>
	@PostMapping("delete")
<#if swagger == 1>
	@Operation(summary = "【${cnName}】删除", description = "参数格式:[1,2,3,4...]")
</#if>
<#if log == 1>
	@AutoLog("【${cnName}】删除")
</#if>
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return success(${className}Service.delete(ids));
	}

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】修改
	 */
</#if>
	@PostMapping("update")
<#if swagger == 1>
	@Operation(summary = "【${cnName}】修改", description = "【${cnName}】修改")
</#if>
<#if log == 1>
	@AutoLog("【${cnName}】修改")
</#if>
	public Result<Integer> update(@RequestBody @Validated ${ClassName} ${className}) {
		// if (${className}Service.count(${ClassName}Cond.builder().nameEq(${className}.getName()).idNe(${className}.getId()).build()) > 0)
		// return error(1, "【${cnName}名称】不能重复！");
		return success(${className}Service.update(${className}));
	}

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】分页列表
	 */
</#if>
	@PostMapping("page")
<#if swagger == 1>
	@Operation(summary = "【${cnName}】分页列表", description = "【${cnName}】分页列表")
</#if>
<#if log == 1>
	@AutoLog("【${cnName}】分页列表")
</#if>	
	public Result<Page<${ClassName}>> page(@RequestBody ${ClassName}Cond cond) {
		return success(${className}Service.page(cond));
	}

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】列表
	 */
</#if>
<#if swagger == 1>
//	@Operation(summary = "【${cnName}】列表", description = "【${cnName}】列表")
</#if>
<#if log == 1>
//  @AutoLog("【${cnName}】列表")
</#if>	
//	@PostMapping("list")
//	public Result<List<${ClassName}>> list(@RequestBody ${ClassName}Cond cond) {
//		return success(${className}Service.list(cond));
//	}

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】按主键查
	 */
</#if>
<#if swagger == 1>
//	@Operation(summary = "【${cnName}】按主键查", description = "【${cnName}】按主键查")
</#if>
<#if log == 1>
//@AutoLog("【${cnName}】按主键查")
</#if>	
//	@PostMapping("findOne")
//	public Result<${ClassName}> findOne(${idType} id) {
//		return success(${className}Service.findOne(id));
//	}

<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】个数
	 */
</#if>
<#if swagger == 1>
//	@Operation(summary = "【${cnName}】个数", description = "【${cnName}】个数")
</#if>
<#if log == 1>
//@AutoLog("【${cnName}】个数")
</#if>	
//	@PostMapping("count")
//	public Result<Integer> count(@RequestBody ${ClassName}Cond cond) {
//		return success(${className}Service.count(cond));
//	}
<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】批新增
	 */
</#if>
<#if swagger == 1>
//	@Operation(summary = "【${cnName}】批新增", description = "【${cnName}】批新增")
</#if>
<#if log == 1>
//	@AutoLog("【${cnName}】批新增")
</#if>
//	@PostMapping("saveBatch")
//	public Result<Void> saveBatch(@RequestBody List<${ClassName}> ${className}s) {
//		${className}Service.saveBatch(${className}s);
//		return success();
//	}

<#if excel == 1>
	<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】导出
	 */
	</#if>
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】导出", description = "【${cnName}】导出")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】导出")
	</#if>	
	@PostMapping("exportExcel")
	public HttpEntity<byte[]> exportExcel(@RequestBody ${ClassName}Cond cond){
		return export(${className}Service.list(cond), ${ClassName}.class, "${cnName}", null);
	}

	<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】导入
	 */
	</#if>
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】导入", description = "【${cnName}】导入")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】导入")
	</#if>		
	@PostMapping("importExcel")
	public Result<Void> importExcel(@RequestPart("file") MultipartFile file){
		${className}Service.importExcel(file);
		return success();
	}
</#if>
}