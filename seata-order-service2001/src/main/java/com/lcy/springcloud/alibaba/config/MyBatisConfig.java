package com.lcy.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lcy.springcloud.alibaba.dao"})
public class MyBatisConfig {
	
}
