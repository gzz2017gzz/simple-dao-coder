package com.gzz.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.gzz.common.config.Const;

/**
 * @summary 【日期时间】工具(对于新日期时间类绝大部分情况直接使用即可,没有必封装方法)
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
public final class DateUtils {
	private DateUtils() {}
	/**
	 * (LocalDateTime)-->String(yyyy-MM-dd HH:mm:ss)
	 */
	public static String formatAll(final LocalDateTime time) {
		return Const.FORMAT_ALL.format(time);
	}

	/**
	 * (LocalTime)-->String(HH:mm:ss)
	 */
	public static String formatHms(final LocalTime time) {
		return Const.FORMAT_HMS.format(time);
	}

	/**
	 * (LocalDate)-->String(yyyy-MM-dd)
	 */
	public static String formatYmd(final LocalDate date) {
		return Const.FORMAT_YMD.format(date);
	}

	/**
	 * String(yyyy-MM-dd HH:mm:ss)-->(LocalDateTime)
	 */
	public static LocalDateTime parse(final String dateTime) {
		return LocalDateTime.parse(dateTime, Const.FORMAT_ALL);
	}

}
