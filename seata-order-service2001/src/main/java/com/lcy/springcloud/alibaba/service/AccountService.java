package com.lcy.springcloud.alibaba.service;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcy.springcloud.alibaba.domain.CommonResult;

@FeignClient(value = "seata-account-service")
public interface  AccountService {
	@PostMapping(value = "/account/decrease")
	CommonResult decrease(@RequestParam("userId") Long productId,@RequestParam("money") BigDecimal count);
	
}
