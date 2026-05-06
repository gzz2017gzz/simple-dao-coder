package com.gzz.common.utils;

import com.gzz.common.config.Const;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @summary 【反回结果】包装
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Result<T> {
	private Integer code;
	private String msg;
	private T data;

	private Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 【状态码500-默认失败消息】
	 **/
	public static <T> Result<T> error() {
		return new Result<>(Const.Result.ERROR, Const.Result.FAILURE);
	}

	/**
	 * 【状态码500-自定义失败消息】
	 **/
	public static <T> Result<T> error(String msg) {
		return new Result<>(Const.Result.ERROR, msg);
	}

	/**
	 * 【自定义编码-自定义失败消息】
	 **/
	public static <T> Result<T> error(Integer code, String msg) {
		return new Result<>(code, msg);
	}

	/**
	 * 【自定义编码-自定义失败消息-有数据】
	 **/
	public static <T> Result<T> error(Integer code, String msg, T data) {
		return new Result<>(code, msg, data);
	}

	/**
	 * 【成功-默认消息-没数据】
	 **/
	public static <T> Result<T> success() {
		return new Result<>(Const.Result.OK, Const.Result.SUCCESS);
	}

	/**
	 * 【成功-默认消息-带数据】
	 **/
	public static <T> Result<T> success(T data) {
		return new Result<>(Const.Result.OK, Const.Result.SUCCESS, data);
	}

	/**
	 * 【成功-自定议消息-带数据】
	 **/
	public static <T> Result<T> success(T data, String msg) {
		return new Result<>(Const.Result.OK, msg, data);
	}

}
