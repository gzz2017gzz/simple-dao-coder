package ${packageName};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gzz.common.config.Result;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
</#if>
/**
 * @类说明 【${cnName}】控制器
 * @author ${author}
 * @date ${time}
 **/
//@Slf4j
@RestController
@RequestMapping("${classname}")
<#if swagger == 1>
@Tag(name = "【${cnName}】API", description = "【${cnName}】接口")
</#if>
public class ${ClassName}Controller {

	@Autowired
	private ${ClassName}Service ${className}Service; // 注入【${cnName}】业务逻辑层
	
	<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】树
	 */
	</#if>
	@PostMapping("tree")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】树", description = "【${cnName}】树")
	</#if>
	public Result<List<${ClassName}>> tree(@RequestBody ${ClassName}Cond cond) {
		return Result.success(${className}Service.tree(cond));
	}
	
	<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】新增
	 */
	</#if>
	@PostMapping("save")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】新增", description = "【${cnName}】新增")
	</#if>
	public Result<${idType}> save(@RequestBody @Validated ${ClassName} ${className}) {
		//if (${className}Service.count(${ClassName}Cond.builder().nameEq(${className}.getName()).build()) > 0)
			//return Result.error(1, "【${cnName}名称】不能重复！");
		return Result.success(${className}Service.save(${className}));
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
	public Result<Integer> delete(@RequestBody ${ClassName} ${className}) {
		${className}Service.delete(${className});
		return Result.success();
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
	public Result<Integer> update(@RequestBody @Validated ${ClassName} ${className}) {
		//if (${className}Service.count(${ClassName}Cond.builder().nameEq(${className}.getName()).idNe(${className}.getId()).build()) > 0)
		//return Result.error(1, "【${cnName}名称】不能重复！");
		return Result.success(${className}Service.update(${className}));
	}
	
	<#if swagger != 1>
	/**
	 * @方法说明 【${cnName}】列表
	 */
	</#if>
	@PostMapping("list")
	<#if swagger == 1>
	@Operation(summary = "【${cnName}】列表", description = "【${cnName}】列表")
	</#if>
	public Result<List<${ClassName}>> list(@RequestBody ${ClassName}Cond cond) {
		return Result.success(${className}Service.list(cond));
	}
}