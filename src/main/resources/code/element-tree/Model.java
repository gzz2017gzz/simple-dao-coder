package ${packageName};
${importList}
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.ArrayList;
import com.gzz.common.base.annotation.Exclude;
import com.gzz.common.base.annotation.Id;
import com.gzz.common.base.annotation.Table;
<#if swagger == 1>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>

/**
 * @类说明 【${cnName}】实体
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
@Table("${tableName}")
public class ${ClassName} {
	// 字段↓
	@Id
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
	/* 扩展(显示)属性↓ */
	@Exclude
<#if swagger == 1>
	@Schema(description = "子记录(集)")
</#if>
	@Builder.Default
	private List<${ClassName}> children = new ArrayList<>(); <#if swagger != 1> /* 子记录(集) */ </#if>
}