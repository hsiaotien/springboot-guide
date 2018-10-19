package com.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	@ResponseBody
	public String hello() {
		return "springboot的自动配置原理，" +
				"比如这里定义属性server.port=80,配置属性文件即可生效";
	}
}
