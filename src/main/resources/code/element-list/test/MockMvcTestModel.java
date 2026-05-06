package ${packageName};

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
/**
 * @类说明 【${cnName}】测试工具，将本类移到maven测试目录中或测试完成之后删除
 * @author ${author}
 * @date ${time}
 **/
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest${ClassName} {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;
	/**
	 * @方法说明 测试 新增【${cnName}】,根据数据类型修改每个字段的值
	 */
	//@Test
	public void save() throws Exception {
		${ClassName} vo = ${ClassName}.builder()
		<#list fields as field>
			.${field.name}(${field.data}) // 设置【${field.comment}】的值
		</#list>
		.build();
		log.info(doRequest("/${className}/save", vo));
	}
	/**
	 * @方法说明 测试 查询【${cnName}】列表,条件可以为空,可直接运行
	 */
	@Test
	public void queryList() throws Exception {
		${ClassName}Cond cond = ${ClassName}Cond.builder()//拼查询条件
		<#list fields as field>
			.${field.name}(${field.data})  // 【${field.comment}】值
		</#list>	
		.build();
		log.info(doRequest("/${className}/list", cond));
	}
	/**
	 * @方法说明 测试 查询【${cnName}】分页列表,条件可以为空,可直接运行
	 */
	@Test
	public void queryPage() throws Exception {
		${ClassName}Cond cond = ${ClassName}Cond.builder()//拼查询条件
		<#list fields as field>
			.${field.name}(${field.data})  // 【${field.comment}】值
		</#list>
		.ids(new Object[]{1,2,3,4})
		.build();
		cond.setPage(0); //当前页
		cond.setSize(10); //页大小
		log.info(doRequest("/${className}/page", cond));
	}
	
	private <T> String doRequest(String url, T t) throws Exception {// restController专用测试方法
		return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(t))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
	}
}