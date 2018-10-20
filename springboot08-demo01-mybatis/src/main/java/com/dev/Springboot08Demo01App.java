package com.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dev.mapper")
public class Springboot08Demo01App {
	public static void main(String[] args) {
		SpringApplication.run(Springboot08Demo01App.class,args);
	}
}
