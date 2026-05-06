package com.gzz.system.meta;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
/**
 * @summary 【服务名】实体
 * @author 高振中
 * @date 2026-05-05 11:25:52
 **/
@Setter
@Getter
public class Server {
	// 字段↓
	private Long id; /* 主键 */
	private String name; /* 名称 */
	private String remark; /* 备注 */
	private LocalDateTime createTime; /* 创建时间 */
	private String createBy; /* 创建人 */
}
