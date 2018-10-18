package com.dev.config;

import com.dev.pojo.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ObjectGps.class)
public class ObjectConfig {

	@Bean
	public User user(ObjectGps gps) {
		User user = new User();
		user.setName(gps.getUserConfig().getName());
		user.setAge(gps.getUserConfig().getAge());
		return user;
	}
}
