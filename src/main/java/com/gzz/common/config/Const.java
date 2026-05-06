package com.gzz.common.config;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import freemarker.template.Configuration;

/**
 * @summary 常量接口
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
public interface Const {
	String MODEL = "Model";// 模板中表名标识
	String PACK = "code/**/*.*";// 模板扫描匹配模式
	String EMPTY = "";
	String BLANK = " ";
	String PATH = "/code/";
	DateTimeFormatter FORMAT_ALL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	DateTimeFormatter FORMAT_HMS = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter FORMAT_YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String UNDER_LINE = "_";// 下划线
	int COUNT = 10;// SQL每行字段数

	interface Result {
		int OK = 200;
		int ERROR = 500;
		String SUCCESS = "SUCCESS";
		String FAILURE = "FAILURE";
	}

	Set<String> HIDES = Set.of("create_time", "create_by", "update_time", "update_by", "dr");// 前端隐藏字段
	boolean LINUX = !(System.getProperty("os.name").startsWith("Windows"));// 操作系统类型
	String ROOT = LINUX ? "/mnt/code/" : "d:/code/";// 根路径
	String COM = "com";// 包名前缀
	String CODE_DIR = ROOT + COM;// 代码路径
	String ZIP = "code.zip";// 代码压缩后的文件名
	String FILE = ROOT + ZIP;// 代码压缩后的完整文件名
	Set<String> DATABASES = Set.of("information_schema", "mysql", "performance_schema", "sys");
	Set<String> EXCLUDE_FIELD = Set.of("id", "createBy", "createTime", "updateBy", "updateTime", "delBy", "delTime", "isDel", "org");// 实体类隐藏字段

	String REGEX02 = "(.*?)_(.*)";// 取第一个[下划线]之后的字符
	String REGEX01 = "(.*?)/(.*)";// 取第一个[正斜杠]之前的字符
	Configuration FREEMARKER = new Configuration(Configuration.VERSION_2_3_32);
	int INT_0 = 0;
	Byte BYTE_0 = 0;
	Byte BYTE_1 = 1;
	Byte BYTE_4 = 4;
	int INT_1 = 1;
	int INT_2 = 2;
	int INT_3 = 3;
	int INT_10 = 10;
	int INT_40 = 40;
	String DOLLAR_2 = "$2";
	String DOLLAR_1 = "$1";
}
