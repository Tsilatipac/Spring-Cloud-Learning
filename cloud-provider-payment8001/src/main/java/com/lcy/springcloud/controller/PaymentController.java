package com.lcy.springcloud.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcy.springcloud.entities.CommonResult;
import com.lcy.springcloud.entities.Payment;
import com.lcy.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Resource
	private DiscoveryClient discoveryClient;

	@PostMapping(value = "/payment/create")
	public CommonResult create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		log.info("*****插入结果：" + result);

		if (result > 0) {
			return new CommonResult(200, "插入数据库成功,serverPort"+serverPort, result);
		} else {
			return new CommonResult(444, "插入数据库失败", null);
		}
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("*****获取结果：" + payment);

		if (payment != null) {
			return new CommonResult(200, "查询成功,serverPort"+serverPort, payment);
		} else {
			return new CommonResult(444, "没有对应记录，查询ID: " + id, null);
		}
	}
	@GetMapping(value = "/payment/discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();
		for (String element : services) {
			log.info("*****element: "+element);
		}
		
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		for (ServiceInstance instance : instances) {
			log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
		}
		return this.discoveryClient;
	}
	
	@GetMapping(value = "/payment/lb")
	public String getPaymentLB() {
		return serverPort;
	}
	
	@GetMapping(value = "/payment/feign/timeout")
	public String paymentFeignTimeOut() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return serverPort;
	}
	
	@GetMapping("/payment/zipkin")
	public String paymentZipkin() {
		return "Hi, I'm paymentZipkin fall back, welcome to my place😊";
	}
}
