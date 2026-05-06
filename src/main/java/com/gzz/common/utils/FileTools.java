package com.gzz.common.utils;

import static com.gzz.common.config.Const.COM;
import static com.gzz.common.config.Const.EMPTY;
import static com.gzz.common.config.Const.EXCLUDE_FIELD;
import static com.gzz.common.config.Const.INT_40;
import static com.gzz.common.config.Const.ROOT;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.gzz.system.code.model.CodeCond;
import com.gzz.system.code.model.Field;
import com.gzz.system.code.model.Table;

import lombok.SneakyThrows;

/**
 * @summary 文件处理工具类
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
public final class FileTools {
	private FileTools() { }

	/**
	 * 压缩目录
	 */
	@SneakyThrows
	public static void createZip(final String dir, final String zipFile) {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
		Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				zos.putNextEntry(new ZipEntry(path.toString().replace("\\", "/").replace(ROOT, EMPTY)));
				zos.write(Files.readAllBytes(path));
				return FileVisitResult.CONTINUE;
			}
		});
		zos.close();
	}

	/**
	 * 组装参数
	 */
	public static Map<String, Object> params(CodeCond cond, String date, String author, Table table, List<Field> fields, String ClassName, String classname) {
		Map<String, Object> param = new HashMap<>(INT_40);
		String idLineName = fields.getFirst().getName();// 主键字段名
		String idName = SysTools.toUpperCamel(idLineName);
		String className = SysTools.firstLower(ClassName);// 类名--首字母小写
		param.put("fields", fields);// 字段
		param.put("serverName", cond.getServerName());// 服务名
		param.put("author", author);// 作者
		param.put("company", cond.getCompany());// 公司
		param.put("cnName", table.getCnName());// 表注释中文名
		param.put("ClassName", ClassName);// 类名--首字母大写
		param.put("className", className);// 类名--首字母小写
		param.put("classname", classname);// 类名--全小写
		param.put("idType", CodeTools.keyType(fields));// 主键数据类型
		param.put("id_name", idLineName); // 主键字段名
		param.put("IdName", idName); // 主键字段名--首字母大写驼峰
		param.put("idName", SysTools.firstLower(idName));// 主键变量名--首字母小写驼峰
		param.put("tableName", table.getTableName());
		param.put("time", date);
		param.put("swagger", cond.getSwagger());
		param.put("importList", CodeTools.importDate(fields));
		param.put("dollar", "$");
		param.put("well", "#");
		param.put("insertFields", CodeTools.insertFields(fields, "", ",", "\r\n\t\t"));
		param.put("insertValues", CodeTools.question(fields.toArray()));
		param.put("insertParams", CodeTools.insertProps(fields, " po.get", ","));
		param.put("updateFields", CodeTools.updateFields(fields, "", "=?,", "\r\n\t\t"));
		param.put("updateValues", CodeTools.updateProps(fields, " po.get", ","));
		param.put("selectFields", CodeTools.insertFields(fields, "t.", ",", "\r\n//\t\t"));
		param.put("insertParamsBatch", CodeTools.insertParams(fields, ":", ",", "\r\n\t\t"));
		param.put("updateParamsBatch", CodeTools.updateParams(fields, "\r\n\t\t"));
		param.put("insertFieldsMybatis", CodeTools.insertFields(fields, "", ",", "\r\n\t\t\t"));
		param.put("selectFieldsMybatis", CodeTools.insertFields(fields, "t.", ",", "\r\n\t\t\t"));
		param.put("insertValuesMybatis", CodeTools.insertParams(fields, "#{", "},", "\r\n\t\t\t"));
		param.put("header", CodeTools.header(fields));
		param.put("model", cond.getModel());
		param.put("log", cond.getLog());
		param.put("excel", cond.getExcel());
		param.put("packageName", COM + "." + cond.getCompany() + "." + cond.getItemName() + "." + cond.getModel() + "." + classname); // 包名
		param.put("columns", cond.getColumns()); // 自定义显示
		param.put("excludeField", EXCLUDE_FIELD); // 实体类隐藏字段
		param.put("package", COM + "." + cond.getCompany() + "." + cond.getItemName() + "." + classname); // 包名
		return param;
	}
}
