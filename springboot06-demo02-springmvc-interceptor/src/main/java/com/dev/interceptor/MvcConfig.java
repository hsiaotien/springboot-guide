package com.dev.interceptor;

import org.springframework.cglib.transform.impl.AddStaticInitTransformer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//将拦截器注册到webmvc中，（"/**"）拦截路径为一切路径
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
	}

	/**
	 * 实际上可以添加其他多种处理器
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//...
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//....
	}
}
