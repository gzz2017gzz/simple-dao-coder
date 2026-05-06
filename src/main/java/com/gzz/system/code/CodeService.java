package com.gzz.system.code;

import static com.gzz.common.config.Const.BYTE_0;
import static com.gzz.common.config.Const.BYTE_4;
import static com.gzz.common.config.Const.CODE_DIR;
import static com.gzz.common.config.Const.DATABASES;
import static com.gzz.common.config.Const.DOLLAR_1;
import static com.gzz.common.config.Const.DOLLAR_2;
import static com.gzz.common.config.Const.EMPTY;
import static com.gzz.common.config.Const.FREEMARKER;
import static com.gzz.common.config.Const.HIDES;
import static com.gzz.common.config.Const.MODEL;
import static com.gzz.common.config.Const.PATH;
import static com.gzz.common.config.Const.REGEX01;
import static com.gzz.common.config.Const.REGEX02;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.gzz.common.config.Config;
import com.gzz.common.config.Const;
import com.gzz.common.utils.CodeTools;
import com.gzz.common.utils.DateUtils;
import com.gzz.common.utils.FileTools;
import com.gzz.common.utils.SysTools;
import com.gzz.system.code.model.CodeCond;
import com.gzz.system.code.model.Field;
import com.gzz.system.code.model.InitData;
import com.gzz.system.code.model.Table;
import com.gzz.system.meta.Meta;
import com.gzz.system.meta.MetaCondition;
import com.gzz.system.meta.MetaDao;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;

/**
 * @summary 生成器逻辑实现
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Service
public final class CodeService {

	private static List<String> templates;// 模板文件集
	@Autowired
	private CodeDao codeDao;// 注入数据访问类
	@Autowired
	private Config config;// 注入排除表的配置
	@Autowired
	private MetaDao metaDao; // 注入【元数据】数据访问层

	/**
	 * 查询数据库名
	 */
	public InitData databases() {
		List<String> databases = codeDao.databases();
		databases.removeAll(DATABASES);// 移除系统数据库
		return new InitData(databases, codeDao.dbName(), metaDao.list());
	}

	/**
	 * 加载模板
	 */
	@SneakyThrows
	@PostConstruct
	public void loadTemplates() {
		FREEMARKER.setClassForTemplateLoading(this.getClass(), PATH);
		FREEMARKER.setDefaultEncoding("UTF-8");
		templates = Arrays.stream(new PathMatchingResourcePatternResolver().getResources(Const.PACK)).map(r -> r.getDescription().replace("\\", "/").replaceAll("(.*)code/(.*)]", DOLLAR_2)).toList();
	}

	/**
	 * 查询表名列表
	 */
	public List<Table> tables(CodeCond cond) {
		cond.setExcludes(config.getExcludes().toArray());
		return codeDao.tables(cond).stream().peek(table -> {
			table.setClassName(SysTools.toUpperCamel(table.getTableName().toLowerCase().replaceAll(REGEX02, DOLLAR_2)));
			table.setFullName(SysTools.toUpperCamel(table.getTableName().toLowerCase()));
			table.setCnName(table.getComment());
		}).toList();
	}

	/**
	 * 查询字段名列表
	 */
	public List<Field> fields(CodeCond cond) {
		List<Meta> metas = metaDao.list(MetaCondition.builder().databaseName(cond.getDbName()).tableName(cond.getTableName()).build());
		List<Field> fields = codeDao.fields(cond).stream().peek(field -> {
			field.setUpper(SysTools.toUpperCamel(field.getName().toLowerCase()));
			field.setLower(SysTools.toLowerCamel(field.getName().toLowerCase()));
			field.setData(CodeTools.data(field.getType()));

			// 处理创建人等 字段的隐藏
			if (cond.getHidden() && HIDES.contains(field.getName())) {
				field.setFillType(BYTE_0);
				field.setShowColumn(BYTE_0);
			}
			if (!cond.getHidden() && field.getType().equals("LocalDateTime")) {
				field.setFillType(BYTE_4);
			}
			// 补充元数据信息
			metas.forEach(meta -> {
				if (meta.getFieldName().equals(field.getName())) {
					field.setFillType(meta.getFillType());
					field.setDictKey(meta.getDictKey());
					field.setShowColumn(meta.getShowColumn());
				}
			});
		}).toList();

		// 处理主键字段的隐藏
		fields.getFirst().setFillType(BYTE_0);
		fields.getFirst().setShowColumn(BYTE_0);
		return fields;
	}

	/**
	 * 生成代码
	 */
	@SneakyThrows
	public void createCode(CodeCond cond) {
		String date = DateUtils.formatAll(LocalDateTime.now());// 日期
		// 过滤选中的模板文件
		List<String> selects = templates.stream().filter(i -> cond.getCodeType().contains(i.replaceAll(REGEX01, DOLLAR_1))).collect(Collectors.toList());
		for (Table table : cond.getTables()) {// 遍历选中表
			cond.setTableName(table.getTableName());// 表名
			List<Field> fields = this.fields(cond);// 字段列表
			String className = table.getClassName();// 驼峰类名(首字母大写)
			String classname = className.toLowerCase();// 类名小写(只用于包路径)
			Map<String, Object> param = FileTools.params(cond, date, cond.getAuthor(), table, fields, className, classname);// 组装参数
			for (String template : selects) {// 遍历选中的模板
				String type = template.replaceAll(REGEX01, DOLLAR_1);// 拆分原始目录->模板类型前缀
				String file = template.replace(type, EMPTY).replace(MODEL, className);// 替换掉模板类型前缀与类名->最终生成的文件名
				Path path = Paths.get(CODE_DIR, cond.getCompany(), type, cond.getModel(), classname, file);
				Files.createDirectories(path.getParent());// 创建目录
				BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
				FREEMARKER.getTemplate(template).process(param, bufferedWriter);// 生成代码文件
				bufferedWriter.close();
			}
		}
	}
}
