package com.springlec.base.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
/* 
 * Description  : Spring Boot data source configure 
 * Detail 		: 1. application.properties 에 선언된 DataSource pool 설정을 가져옴. 
 * 				  2. spring 내장 함수를 빌드함. => spring.datasource.* 
 * Author 		: 
 * Date 		:
 * Update 		: 
 */
@Configuration
public class DataSourceConfig {
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
			return DataSourceBuilder.create().build();
	}
}