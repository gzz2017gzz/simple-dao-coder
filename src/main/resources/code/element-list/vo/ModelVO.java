package ${packageName}.vo;
${importList}
import lombok.Getter;
import lombok.Setter;
import ${packageName}.${ClassName};
<#if swagger == 1>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>

/**
 * @类说明 【${cnName}】VO
 * @author ${author}
 * @date ${time}
 **/
@Setter
@Getter
<#if swagger == 1>
@Schema(description = "【${cnName}】VO")
</#if>
public class ${ClassName}VO extends ${ClassName} {
	/* 扩展(显示)属性↓*/

}