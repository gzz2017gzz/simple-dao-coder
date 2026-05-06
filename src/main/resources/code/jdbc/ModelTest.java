package ${packageName};
${importList}
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.${company}.common.base.Page;
import lombok.extern.slf4j.Slf4j;
/**
 * @summary 【${cnName}】测试工具，将本类移到maven测试目录中或测试完成之后删除
 * @author 高振中
 * @date 2019-01-12 22:40:08
 **/
@Slf4j
@SpringBootTest
public class ${ClassName}Test {
	@Autowired
	${ClassName}Dao ${className}Dao; // 注入【${cnName}】数据访问层
	/**
	 * 测试 新增【${cnName}】,根据数据类型修改每个字段的值
	 */
	@Test
	public void save() {
		${ClassName} vo = ${ClassName}.builder()
		<#list fields as field>
			.${field.lower}(${field.data}) // 设置【${field.comment}】的值
		</#list>
		.build();
		int save = ${className}Dao.save(vo);
		log.info("save={}", save);
	}
	/**
	 * 测试 查询【${cnName}】列表,条件可以为空,可直接运行
	 */
	@Test
	public void list() {
		${ClassName}Condition condition = ${ClassName}Condition.builder()//拼查询条件
		<#list fields as field>
			.${field.lower}(${field.data})  // 【${field.comment}】值
		</#list>	
		.build();
		List<${ClassName}> ${className}s = ${className}Dao.list(condition);
		log.info("${className}s={}", ${className}s);
	}
	/**
	 * 测试 查询【${cnName}】分页列表,条件可以为空,可直接运行
	 */
	@Test
	public void page() {
		${ClassName}Condition condition = ${ClassName}Condition.builder()//拼查询条件
		<#list fields as field>
			.${field.lower}(${field.data})  // 【${field.comment}】值
		</#list>
		.ids(new Object[]{1,2,3,4})
		.build();
		condition.setPage(1); //当前页
		condition.setSize(10); //页大小
		Page<${ClassName}> page = ${className}Dao.page(condition);
		log.info("page={}", page);
	}
}