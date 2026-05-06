package ${packageName};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gzz.common.base.Page;
import com.gzz.common.config.Result;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
</#if>

<#if swagger != 1>
/**
 * @类说明 [${cnName}]控制器
 * @author ${author}
 * @date  ${time}
 **/
</#if>
@RestController
@RequestMapping("api/${className}")
<#if swagger == 1>
@Tag(name = "[${cnName}]API", description = "[${cnName}]接口")
</#if>
public class ${ClassName}Action {

	@Autowired
	private ${ClassName}Business business; //注入[${cnName}]业务逻辑
	
	<#if swagger != 1>
    /**
     * @方法说明  新增[${cnName}]
     */
	</#if>
	@PostMapping("save")
	<#if swagger == 1>
	@Operation(summary = "[${cnName}]新增", description = "[${cnName}]新增")
	</#if>
	public Result<Long> save(@RequestBody ${ClassName} ${className} ) {
		return business.save(${className});
	}
	
	<#if swagger != 1>
    /**
     * @方法说明 删除[${cnName}]
     */
	</#if>
	<#if swagger == 1>
	@Operation(summary = "[${cnName}]删除", description = "参数格式:[1,2,3,4...]")
	</#if>
	@PostMapping("delete")
	public Result<Integer> delete(@RequestBody Object[] ids) {
		return business.delete(ids);
	}
	
	<#if swagger != 1>
    /**
     * @方法说明 修改[${cnName}]
     */
	</#if>	
	@PostMapping("update")
	<#if swagger == 1>
	@Operation(summary = "[${cnName}]修改", description = "[${cnName}]修改")
	</#if>	
	public Result<Integer> update(@RequestBody ${ClassName} ${className} ) {
		return business.update(${className}); 
	}
	
	<#if swagger != 1>
    /**
     * @方法说明 [${cnName}]条件分页列表
     */
	</#if>	
	@PostMapping("page")
	<#if swagger == 1>
	@Operation(summary = "[${cnName}]分页条件列表", description = "[${cnName}]分页条件列表")
	</#if>
	public Result<Page<${ClassName}>> page(@RequestBody ${ClassName}Cond cond ){
		return business.page(cond);
	}
	
	<#if swagger != 1>
    /**
     * @方法说明 [${cnName}]条件列表
     */
	</#if>	
    @PostMapping("list")
	<#if swagger == 1>
	@Operation(summary = "[${cnName}]条件列表", description = "[${cnName}]条件列表")
	</#if>
    public Result<List<${ClassName}>> list(@RequestBody ${ClassName}Cond cond ){
        return business.list(cond);
    }
}