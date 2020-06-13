package com.lcy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OpenFeignMain80.class, args);
	}
}
