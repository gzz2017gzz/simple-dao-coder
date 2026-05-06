package ${packageName};
${importList}
import com.gzz.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
/**
 * @类说明 【${cnName}】查询条件
 * @author ${author}
 * @date ${time}
 **/
@Setter
@Getter
@Builder
<#if swagger == 1>
@Schema(description = "【${cnName}】查询条件")
</#if>
public class ${ClassName}Cond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
<#list fields as field>
<#if field.type == "String">
		and("${field.name} LIKE", ${field.lower}, 3);
<#else>
		and("${field.name} =", ${field.lower});
</#if>
</#list>
		in("${id_name}", ${idName}s);
	}
	/* 默认条件↓ */
<#list fields as field>
	<#if swagger == 1>
		<#if field.fillType == 0 && field_index != 0>
	@Schema(hidden = true)
		<#else>
	@Schema(description = "${field.comment}")
		</#if>
	</#if>
	private ${field.type} ${field.lower}; <#if swagger != 1>/* ${field.comment} */</#if>
</#list>
<#if swagger == 1>
	@Schema(description = "主键数组")
</#if>
	private Object[] ${idName}s; <#if swagger != 1>/* 主键数组 */</#if>
	/* 自定义条件↓ */
}