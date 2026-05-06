package com.gzz.common.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * @summary 配置文件注入
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "custom")
public final class Config {
	private List<String> excludes;// 排除表集
}
