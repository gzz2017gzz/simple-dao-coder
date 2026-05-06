package ${packageName};

import static com.gzz.common.enums.LeafType.LEAF;
import static com.gzz.common.enums.LeafType.NO_LEAF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @类说明 【${cnName}】业务逻辑层
 * @author ${author}
 * @date ${time}
 **/
//@Slf4j
@Service
public class ${ClassName}Service {

	@Autowired
	private ${ClassName}Dao ${className}Dao; // 注入【${cnName}】数据访问层
	/**
	 * @方法说明 【${cnName}】树:按主键顺序查询所有记录,只要保证子结点出现在父结点的后面就可以一次遍历将list整理成树型结构
	 */
	public List<${ClassName}> tree(${ClassName}Cond cond) {
		cond.setOrders("${idName}");
		List<${ClassName}> ${className}s = ${className}Dao.list(cond);// 查询所有记录
		Map<Long, ${ClassName}> treeMap = new HashMap<>();
		List<${ClassName}> treeList =  new ArrayList<>();
		
		${className}s.forEach(${className} -> {
			treeMap.put(${className}.getId(), ${className});
			if (${className}.getParentId() == 0) { // 将根结点放入新的list
				treeList.add(${className});
			} else {// 将非根结点放入对应上级结点的children集合中(如果上级为空则放弃此结点)
				treeMap.get(${className}.getParentId()).getChildren().add(${className});
			}
		});
		
		return treeList;
	}

	/**
	 * @方法说明 【${cnName}】新增
	 */
	@Transactional
	public ${idType} save(${ClassName} ${className}) {
		${className}.setLeaf(Byte.valueOf("1"));
		if (!${className}.getParentId().equals(0L)) {
			${className}Dao.update(${ClassName}.builder().id(${className}.getParentId()).leaf(NO_LEAF.getCode()).build());
		}
		return ${className}Dao.save(${className}).get${IdName}();
	}

	/**
	 * @方法说明 【${cnName}】删除
	 */
	@Transactional
	public void delete(${ClassName} ${className}) {
		${className}Dao.delete(${className}.getId());
		${ClassName}Cond cond = ${ClassName}Cond.builder().parentId(${className}.getParentId()).dr(Byte.valueOf("0")).build();
		if (${className}Dao.count(cond) == 0 && !${className}.getParentId().equals(0L)) {
			${className}Dao.update(${ClassName}.builder().id(${className}.getParentId()).leaf(LEAF.getCode()).build());		
		}
	}

	/**
	 * @方法说明 【${cnName}】修改
	 */
	public int update(${ClassName} ${className}) {
		return ${className}Dao.update(${className});
	}

	/**
	 * @方法说明 【${cnName}】列表
	 */
	public List<${ClassName}> list(${ClassName}Cond cond) {
		return ${className}Dao.list(cond);
	}
	
	/**
	 * @方法说明 【${cnName}】个数
	 */
	public int count(${ClassName}Cond cond) {
		return ${className}Dao.count(cond);
	}
}