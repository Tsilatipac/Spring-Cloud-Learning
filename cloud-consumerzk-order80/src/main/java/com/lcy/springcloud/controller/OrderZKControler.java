package com.lcy.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderZKControler {
	public static final String INVOKE_URL = "http://cloud-provider-payment";
	
	@Resource
	public RestTemplate restTemplate;
	
	@GetMapping(value = "/consumer/payment/zk")
	public String paymentInfo() {
		String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk", String.class);
		return result;
	}
	
}
