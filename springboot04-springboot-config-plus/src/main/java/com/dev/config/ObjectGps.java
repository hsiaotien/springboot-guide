package com.dev.config;

import com.dev.pojo.UserConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性注入， 有对象导航特点
 */
@ConfigurationProperties(prefix = "gps")
public class ObjectGps {

	private UserConfig userConfig;

	public void setUserConfig(UserConfig userConfig){
		this.userConfig =userConfig;
	}
	public UserConfig getUserConfig() {
		return userConfig;
	}
}
