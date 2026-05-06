package ${packageName};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gzz.common.base.Page;
import com.gzz.common.config.Result;
/**
 * @类说明 [${cnName}]业务逻辑
 * @author ${author}
 * @date  ${time}
 **/
@Service
public class ${ClassName}Business {

	@Autowired
	private I${ClassName}Client ${className}Client; //注入[${cnName}]客户端

	/**
	 * @方法说明  新增[${cnName}]
	 */
	public Result<Long> save(${ClassName} ${className}) {
		return ${className}Client.save(${className});
	}

	/**
	 * @方法说明  删除[${cnName}]
	 */
	public Result<Integer> delete(Object[] ids) {
		return ${className}Client.delete(ids);//物理删除
	}

	/**
	 * @方法说明  更新[${cnName}]
	 */
	public Result<Integer> update(${ClassName} ${className}) {
		return ${className}Client.update(${className});
	}

	/**
	 * @方法说明  [${cnName}]条件分页列表
	 */
	public Result<Page<${ClassName}>> page(${ClassName}Cond cond) {
		return ${className}Client.page(cond);
	}

	/**
	 * @方法说明  [${cnName}]分页列表
	 */
	public Result<List<${ClassName}>> list(${ClassName}Cond cond) {
		return ${className}Client.list(cond);
	}

}