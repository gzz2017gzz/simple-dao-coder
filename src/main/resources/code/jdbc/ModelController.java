package ${packageName};

import java.util.List;
<#if excel == 1>
import com.${company}.common.tools.SysTools;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
<#if log == 1>
import com.${company}.common.aop.AutoLog;
</#if>
import com.${company}.common.base.Page;
import com.${company}.common.utils.Result;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
</#if>

/**
 * @summary  【${cnName}】控制器
 * @author ${author}
 * @date ${time}
 **/
<#if swagger == 1>
@Tag(name = "【${cnName}】API", description = "【${cnName}】接口")
</#if>
//@Slf4j
@RestController
@RequestMapping("${classname}")
public class ${ClassName}Controller {

	@Autowired
	private ${ClassName}Service ${className}Service; // 注入【${cnName}】业务逻辑层

	/**
	 * 【${cnName}】新增
	 */
	@PostMapping("save")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】新增", description = "【${cnName}】新增")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】新增")
	</#if>
	public Result<Integer> save(@RequestBody @Validated ${ClassName} ${className}) {
		//if (${className}Service.count(${ClassName}Condition.builder().nameEq(${className}.getName()).build()) > 0)
			//return Result.error(1, "【${cnName}名称】不能重复！");
		return Result.success(${className}Service.save(${className}));
	}

	/**
	 * 【${cnName}】删除
	 */
	@PostMapping("delete")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】删除", description = "参数格式:[1,2,3,4...]")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】删除")
	</#if>
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(${className}Service.delete(ids));
	}

	/**
	 * 【${cnName}】修改
	 */
	@PostMapping("update")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】修改", description = "【${cnName}】修改")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】修改")
	</#if>
	public Result<Integer> update(@RequestBody @Validated ${ClassName} ${className}) {
		//if (${className}Service.count(${ClassName}Condition.builder().nameEq(${className}.getName()).idNe(${className}.getId()).build()) > 0)
			//return Result.error(1, "【${cnName}名称】不能重复！");
		return Result.success(${className}Service.update(${className}));
	}

	/**
	 * 【${cnName}】分页列表
	 */
	@PostMapping("page")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】分页列表", description = "【${cnName}】分页列表")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】分页列表")
	</#if>
	public Result<Page<${ClassName}>> page(@RequestBody ${ClassName}Condition condition) {
		return Result.success(${className}Service.page(condition));
	}

	/**
	 * 【${cnName}】列表
	 */
	@PostMapping("list")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】列表", description = "【${cnName}】列表")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】列表")
	</#if>
	public Result<List<${ClassName}>> list(@RequestBody ${ClassName}Condition condition) {
		return Result.success(${className}Service.list(condition));
	}

	/**
	 * 【${cnName}】按主键查
	 */
	@PostMapping("findById")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】按主键查", description = "【${cnName}】按主键查")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】按主键查")
	</#if>
	public Result<${ClassName}> findById(@RequestParam("id") ${idType} id) {
		return Result.success(${className}Service.findById(id));
	}

	/**
	 * 【${cnName}】个数
	 */
	@PostMapping("count")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】个数", description = "【${cnName}】个数")
	</#if>
	<#if log == 1>
	@AutoLog("【${cnName}】按主键查")
	</#if>
	public Result<Integer> count(@RequestBody ${ClassName}Condition condition) {
		return Result.success(${className}Service.count(condition));
	}
	<#if excel == 1>
	/**
	 * @方法说明 【${cnName}】导出
	 */
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】导出", description = "【${cnName}】导出")
	</#if>
	@PostMapping("export")
	public HttpEntity<byte[]> export(@RequestBody ${ClassName}Condition cond ){
		return SysTools.write(${className}Service.export(cond));
	}
	
	/**
	 * @方法说明 【${cnName}】导入
	 */
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】导入", description = "【${cnName}】导入")
	</#if>
	@PostMapping("import")
	public Result<?> importExcel(@RequestPart("file") MultipartFile file){
		${className}Service.importExcel(file);
		return Result.success();
	}
	</#if>
}