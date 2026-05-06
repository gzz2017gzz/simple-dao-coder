package com.gzz.system.meta;

import static com.gzz.common.config.Const.BYTE_0;
import static com.gzz.common.config.Const.BYTE_1;
import static com.gzz.common.config.Const.HIDES;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @summary 【元数据】业务逻辑层
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Service
public class MetaService {

	@Autowired
	private MetaDao metaDao; // 注入【元数据】数据访问层
	@Autowired
	private HttpServletRequest request;
 

	/**
	 * 【元数据】批量保存
	 */
	public void saveBatch(List<Meta> metas) {
		Meta meta = metas.getFirst();
		MetaCondition cond = MetaCondition.builder().databaseName(meta.getDatabaseName()).tableName(meta.getTableName()).build();
		metaDao.delete(cond);

		metaDao.insertBatch(metas.stream().filter(m -> !m.getFillType().equals(BYTE_0) && !HIDES.contains(m.getFieldName())
				&& (!m.getFillType().equals(BYTE_1) || m.getShowColumn().equals(BYTE_0))).peek(i -> {
			i.setCreateTime(LocalDateTime.now());
			i.setCreateBy(request.getRemoteAddr());
		}).toList());
	}
 
}
