package com.lcy.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcy.springcloud.alibaba.domain.CommonResult;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
	@PostMapping(value = "/storage/decrease")
	CommonResult decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
	
}
