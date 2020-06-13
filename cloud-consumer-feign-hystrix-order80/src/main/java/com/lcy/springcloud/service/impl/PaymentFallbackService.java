package com.lcy.springcloud.service.impl;

import org.springframework.stereotype.Component;

import com.lcy.springcloud.service.PaymentHystrixService;

@Component
public class PaymentFallbackService implements PaymentHystrixService{

	@Override
	public String paymentInfo_OK(Integer id) {
		return "----------PaymentFallbackService.paymentInfo_OK() fallback";
	}

	@Override
	public String paymentInfo_TimeOut(Integer id) {
		return "----------PaymentFallbackService.paymentInfo_TimeOut() fallback";
	}
	
}
