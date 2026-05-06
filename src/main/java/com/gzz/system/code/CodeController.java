package com.gzz.system.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gzz.common.config.Const;
import com.gzz.common.utils.FileTools;
import com.gzz.common.utils.Result;
import com.gzz.system.code.model.CodeCond;
import com.gzz.system.code.model.InitData;
import com.gzz.system.code.model.Field;
import com.gzz.system.code.model.Table;
import lombok.SneakyThrows;

/**
 * @summary 代码生成控制器
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@RestController
@RequestMapping("code")
public final class CodeController {
	@Autowired
	private CodeService service;// 生成器业务罗辑接口

	/**
	 * 查询全部数据库名
	 */
	@PostMapping("databases")
	public Result<InitData> databases() {
		return Result.success(service.databases());
	}

	/**
	 * 查询表名列表
	 */
	@PostMapping("tables")
	public Result<List<Table>> tables(@RequestBody CodeCond cond) {
		return Result.success(service.tables(cond));
	}

	/**
	 * 查询字段列表
	 */
	@PostMapping("fields")
	public Result<List<Field>> fields(@RequestBody CodeCond cond) {
		return Result.success(service.fields(cond));
	}

	/**
	 * 生成代码
	 */
	@PostMapping("create")
	public Result<String> createCode(@RequestBody CodeCond cond) {
		FileSystemUtils.deleteRecursively(new File(Const.CODE_DIR));// 删除上次生成的文件夹
		service.createCode(cond);
		return Result.success();
	}

	/**
	 * 下载代码压缩包
	 */
	@SneakyThrows
	@GetMapping("downCode")
	public HttpEntity<byte[]> downCode() {
		FileTools.createZip(Const.CODE_DIR, Const.FILE);// 压缩成code.zip
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-disposition", "attachment; filename=\"" + Const.ZIP + "\"");
		return new HttpEntity<>(Files.readAllBytes(Paths.get(Const.FILE)), headers);
	}
}
