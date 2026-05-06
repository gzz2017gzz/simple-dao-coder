package com.gzz.common.utils;

import com.gzz.common.config.Const;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.gzz.common.config.Const.INT_0;
import static com.gzz.common.config.Const.INT_1;

/**
 * @summary 系统工具
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
public final class SysTools {
	private SysTools() { }

	/**
	 * 下划线-->首字母大写驼峰
	 */
	public static String toUpperCamel(final String word) {
		char[] chars = toLowerCamel(word).toCharArray();
		chars[INT_0] = Character.toUpperCase(chars[INT_0]);
		return new String(chars);
	}

	/**
	 * 首字母转小写
	 */
	public static String firstLower(final String word) {
		char[] chars = word.toCharArray();
		chars[INT_0] = Character.toLowerCase(chars[INT_0]);
		return new String(chars);
	}

	/**
	 * 下划线-->首字母小写驼峰
	 */
	public static String toLowerCamel(final String word) {
		StringBuilder sb = new StringBuilder(word);
		Matcher mc = Pattern.compile(Const.UNDER_LINE).matcher(word);
		for (int i = INT_0; mc.find(); i++) {// 每次替换_x为X,同时因为长度减少1偏移量调整1
			sb.replace(mc.end() - i - INT_1, mc.end() - i + INT_1, sb.substring(mc.end() - i, mc.end() - i + INT_1).toUpperCase());
		}
		return sb.toString();
	}
}
