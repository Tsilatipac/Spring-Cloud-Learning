package com.lcy.springcloud.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;


@Service
public class PaymentService {
	/**
	 * 正常访问，肯定OK
	 * @param id
	 * @return
	 */
	public String paymentInfo_OK(Integer id) {
		return "线程池"+Thread.currentThread().getName()+"  paymentInfo_OK, id:  "+ id;
	}
	
	// 只要当前服务不可用，就做服务降级
	@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
	})
	public String paymentInfo_TimeOut(Integer id) {
		int timeNumber = 3;
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		int i=10/0;
		return "线程池"+Thread.currentThread().getName()+"  paymentInfo_TimeOut, id:  "+ id+"\t耗时"+timeNumber+"秒";
	}
	
	public String paymentInfo_TimeOutHandler(Integer id) {
		return "线程池"+Thread.currentThread().getName()+"  系统繁忙，请稍后再试, id:  "+ id;
	}
	
	// 服务熔断
	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  // 是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  // 请求次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  // 时间窗口期
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),  // 失败率达到多少后跳闸
	})
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		if(id<0) {
			throw new RuntimeException("*****id 不能为负数");
		}
		String serialNumber = IdUtil.simpleUUID();
		return Thread.currentThread().getName()+"\t"+"调用成功，流水号"+serialNumber ;
	}
	
	public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
		return "id 不能为负数，请稍后再试... id : " +id;
	}
}