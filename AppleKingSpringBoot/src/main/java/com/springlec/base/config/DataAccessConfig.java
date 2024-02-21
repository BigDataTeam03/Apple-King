package com.springlec.base.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*
 * Description  : SQL session factory bean 생성 클래스  
 * Detail 		: 1. Dao 와 연결해주는 Mapper 를 스캔. 
 * Author 		: PDG
 * Date 		: 2024.02.21
 * Update 		: 
 */
@Configuration

// Dao mapper 가 있는 base pakage 를 지정 (수정필요)
@MapperScan(basePackages = "com.springlec.base.dao")
public class DataAccessConfig {
	@Bean
	SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(
				//resolver -> Try catch 를 계속(re)하는 놈.
				new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml")
				); 
		return sessionFactory.getObject();
	}
	@Bean
	SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

