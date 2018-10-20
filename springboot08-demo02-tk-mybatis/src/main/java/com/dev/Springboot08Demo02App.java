package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dev.mapper") //注意这里的@MapperScan是通用mapper的！
public class Springboot08Demo02App {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08Demo02App.class,args);
	}
}
