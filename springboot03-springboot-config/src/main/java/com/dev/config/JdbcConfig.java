package com.dev.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {

	/*
	还有多种注入jdbcProperties的方式， 这是第一种--形参注入
	 */
	@Bean
	public DataSource dataSource(JdbcProperties prop) {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(prop.getClassName());
		druidDataSource.setUrl(prop.getUrl());
		druidDataSource.setUsername(prop.getUsername());
		druidDataSource.setPassword(prop.getPassword());
		return druidDataSource;
	}

	/*
	第二种方式--autowired注入.  注意这种方式还是必须要EnableConfigurationProperties注解！！
	 */
	/*@Autowired
	private JdbcProperties prop;

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(prop.getClassName());
		druidDataSource.setUrl(prop.getUrl());
		druidDataSource.setUsername(prop.getUsername());
		druidDataSource.setPassword(prop.getPassword());
		return druidDataSource;
	}*/


	/*
	第三种方式--构造函数注入
	 */
	/*private JdbcProperties prop;

	public JdbcConfig (JdbcProperties prop) {
		this.prop = prop;
	}

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(prop.getClassName());
		druidDataSource.setUrl(prop.getUrl());
		druidDataSource.setUsername(prop.getUsername());
		druidDataSource.setPassword(prop.getPassword());
		return druidDataSource;
	}*/
}
