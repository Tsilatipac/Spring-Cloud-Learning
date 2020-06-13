package com.lcy.springcloud.service;

import org.springframework.stereotype.Component;

import com.lcy.springcloud.entities.CommonResult;
import com.lcy.springcloud.entities.Payment;

@Component
public class PaymentFallbackService implements PaymentService{

	@Override
	public CommonResult<Payment> paymentSQL(Long id) {
		return new CommonResult<Payment>(444,"服务降级返回---PaymentFallbackService",new Payment(id,"errorSerial"));
	}

}
