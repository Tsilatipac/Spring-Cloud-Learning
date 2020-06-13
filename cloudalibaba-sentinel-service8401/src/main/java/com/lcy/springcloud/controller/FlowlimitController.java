package com.lcy.springcloud.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FlowlimitController {
	@GetMapping("/testA")
	public String testA() {
		return "---testA";
	}

	@GetMapping("/testB")
	public String testB() {
		return "---testB";
	}

	@GetMapping("/testC")
	public String testC() {
		try {
			// 暂停几秒钟线程
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
		}
		log.info("testC 测试RT");
		return "----testC";
	}

	@GetMapping("/testD")
	public String testD() {
		log.info("testD 测试RT");
		int age = 10 / 0;
		return "----testD";

	}

	@GetMapping("/testE")
	public String testE() {
		log.info("testE 测试异常数");
		int age = 10 / 0;
		return "-----testE 测试异常数";
	}

	@GetMapping("/testHotKey")
	@SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
	public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
			@RequestParam(value = "p2", required = false) String p2) {
		return "-----testE 测试异常参数";
	}

	public String deal_testHotKey(String p1,String p2,BlockException exception) {
		return "-----deal_testHotKey,😭"; //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
	}
	
}
