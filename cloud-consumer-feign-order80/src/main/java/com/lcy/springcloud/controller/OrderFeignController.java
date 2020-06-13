package com.lcy.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lcy.springcloud.entities.CommonResult;
import com.lcy.springcloud.entities.Payment;
import com.lcy.springcloud.service.PaymentFeignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderFeignController {
	@Resource
	private PaymentFeignService paymentFeignService;
	
	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
		return paymentFeignService.getPaymentById(id);
	}
	@GetMapping(value = "/consumer/payment/feign/timeout")
	public String paymentFeignTimeOut() {
		return paymentFeignService.paymentFeignTimeOut();
	}
}
