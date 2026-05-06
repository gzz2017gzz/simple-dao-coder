package ${packageName};
${importList}
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
/**
 * @summary 【${cnName}】实体
 * @author ${author}
 * @date ${time}
 **/
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
<#if swagger == 1>
@Schema(name = "${ClassName}", description = "【${cnName}】实体")
</#if>
public class ${ClassName} {
	// 字段↓
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
}