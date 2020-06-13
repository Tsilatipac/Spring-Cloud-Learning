package com.lcy.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcy.springcloud.service.IMessageProvider;

@RestController
public class SendMessageController {
	
	@Resource
	private IMessageProvider messageProvider;

	@GetMapping("/sendMessage")
	public String sendMessage() {
		return messageProvider.send();
	}
	
}
