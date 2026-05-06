package com.gzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * @summary 代码生成器启动程序
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Slf4j
@SpringBootApplication
public class CreateCode {
	public static void main(String[] args) {
		SpringApplication.run(CreateCode.class, args);
		log.info("程序启动完成!");
	}
}
