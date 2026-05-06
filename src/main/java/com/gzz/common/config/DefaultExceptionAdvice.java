package com.gzz.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzz.common.utils.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【全局异常】处理
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Slf4j
@ResponseBody
@ControllerAdvice
public final class DefaultExceptionAdvice {
	@ExceptionHandler(Exception.class)
	public Result<String> ex(Exception ex) {
		log.error(ex.getMessage(), ex);
		return Result.error(ex.getMessage());
	}
}
