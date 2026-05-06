package com.gzz.system.meta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.common.utils.Result;

/**
 * @summary 【元数据】控制器
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@RestController
@RequestMapping("meta")
public class MetaController {

	@Autowired
	private MetaService metaService; // 注入【元数据】业务逻辑层

	/**
	 * 【元数据】批新增
	 */
	@PostMapping("saveBatch")
	public Result<Integer> saveBatch(@RequestBody List<Meta> metas) {
		metaService.saveBatch(metas);
		return Result.success();
	}
}
