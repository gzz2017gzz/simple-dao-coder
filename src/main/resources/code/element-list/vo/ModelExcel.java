package ${packageName}.vo;
${importList}
import lombok.Getter;
import lombok.Setter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
/**
 * @类说明 【${cnName}】excelVO
 * @author ${author}
 * @date ${time}
 **/
@Setter
@Getter
public class ${ClassName}Excel {
<#list fields as field>
	@ColumnWidth(15)
	@ExcelProperty(value = "${field.comment}")
	private ${field.type} ${field.lower};
</#list>
}