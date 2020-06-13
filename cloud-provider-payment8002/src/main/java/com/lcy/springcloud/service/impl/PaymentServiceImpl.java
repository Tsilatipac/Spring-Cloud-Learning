package com.lcy.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lcy.springcloud.dao.PaymentDao;
import com.lcy.springcloud.entities.Payment;
import com.lcy.springcloud.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService{

	@Resource
	private PaymentDao paymentDao;

	@Override
	public int create(Payment payment) {
		return paymentDao.create(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}

}
