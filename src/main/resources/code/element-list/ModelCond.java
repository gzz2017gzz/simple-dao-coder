package ${packageName};
${importList}
import com.hq.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
//@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
<#if swagger == 1>
@Schema(description = "【${cnName}】查询条件")
</#if>
public class ${ClassName}Cond extends BaseCondition {

	/**
	 * @方法说明 拼加条件
	 **/
	@Override
	protected void addCondition() {
<#list fields as field>
	<#if field.fillType != 0 || field.name == 'dr'>
    	<#if field.type == "String">
		and("${field.name} LIKE", ${field.lower}, 3);
    	<#elseif field.fillType == 4>
		and("${field.name} >=", ${field.lower}Start);
		and("${field.name} <=", ${field.lower}End);
		<#else>
		and("${field.name} =", ${field.lower});
    	</#if>
	</#if>
</#list>
		in("${id_name}", ${idName}s);
		// notIn("${id_name}", ${id_name}sNot);
	}

	/* 默认条件↓ */
<#list fields as field>
	<#if field.fillType != 0 || field.name == 'dr'>
		<#if swagger == 1>
  			<#if field.fillType == 4>
	@Schema(description = "${field.comment}开始")
	private ${field.type} ${field.lower}Start;
	
	@Schema(description = "${field.comment}结束")
	private ${field.type} ${field.lower}End;
	
  			<#elseif field.fillType != 4>
	@Schema(description = "${field.comment}")
	private ${field.type} ${field.lower};
	
  			</#if>
		<#else>
  			<#if field.fillType == 4>
	private ${field.type} ${field.lower}Start; /* ${field.comment}开始 */
	
	private ${field.type} ${field.lower}End; /* ${field.comment}结束 */
	
  			<#elseif field.fillType != 4>
	private ${field.type} ${field.lower}; /* ${field.comment} */

  			</#if>
		</#if>
	</#if>
</#list>
<#if swagger == 1>
	@Schema(description = "包含主键(集)")
</#if>
	private Object[] ${idName}s;<#if swagger != 1> /* 包含主键(集) */</#if>
	
	/* 自定义条件↓ */
<#if swagger == 1>
	// @Schema(description = "排除主键(集)")
</#if>
	// private Object[] ${idName}sNot;<#if swagger != 1> /* 排除主键(集) */</#if>
}