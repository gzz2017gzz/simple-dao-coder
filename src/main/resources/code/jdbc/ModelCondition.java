package ${packageName};
${importList}
import com.${company}.common.base.BaseCondition;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>

/**
 * @summary 【${cnName}】查询条件
 * @author ${author}
 * @date ${time}
 **/
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
<#if swagger == 1>
@Schema(description = "【${cnName}】实体")
</#if>
public class ${ClassName}Condition extends BaseCondition {

	/**
	 *  拼加条件
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
	private ${field.type} ${field.lower}; /* ${field.comment} */
</#list>
<#if swagger == 1>
	@Schema(description = "包含主键(集)")
</#if>
	private Object[] ${idName}s; /* 包含主键(集) */
	/* 自定义条件↓ */
<#if swagger == 1>
	//@Schema(description = "排除主键(集)")
</#if>
	//private Object[] ${idName}sNot; <#if swagger != 1>/* 排除主键(集) */</#if>	
}