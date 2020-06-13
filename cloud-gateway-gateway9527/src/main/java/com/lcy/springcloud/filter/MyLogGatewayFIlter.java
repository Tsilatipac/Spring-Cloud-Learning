package com.lcy.springcloud.filter;

import java.util.Date;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyLogGatewayFIlter implements GlobalFilter,Ordered{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("*****come in MyLogGateWayFilter: "+new Date());
		String uname = exchange.getRequest().getQueryParams().getFirst("uname");
		if(uname == null) {
			log.info("*****用户名为null，非法用户");
			exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	
	@Override
	public int getOrder() {
		// 优先级，数字越小优先级越高
		return 0;
	}
}
