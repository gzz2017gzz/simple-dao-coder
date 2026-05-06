package ${packageName};

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.gzz.common.base.Page;
import com.gzz.common.config.Result;
import com.gzz.common.config.FeignConfigure;

/**
 * @类说明 [${cnName}]客户端
 * @author ${author}
 * @date  ${time}
 **/
@FeignClient(value = "${serverName}/${className}", configuration = FeignConfigure.class)
public interface I${ClassName}Client {

    /**
     * @方法说明  新增[${cnName}]
     */
	@PostMapping("save")
	Result<Long> save(@RequestBody ${ClassName} ${className});

    /**
     * @方法说明  删除[${cnName}]
     */
	@PostMapping("delete")
	Result<Integer> delete(@RequestBody Object[] ids);

    /**
     * @方法说明  修改[${cnName}]
     */
	@PostMapping("update")
	Result<Integer> update(@RequestBody ${ClassName} ${className});

    /**
     * @方法说明  [${cnName}]条件分页列表
     */
	@PostMapping("page")
	Result<Page<${ClassName}>> page(@RequestBody ${ClassName}Cond cond );

    /**
     * @方法说明  [${cnName}]条件列表
     */
	@PostMapping("list")
	Result<List<${ClassName}>> list(@RequestBody ${ClassName}Cond cond );
 
}