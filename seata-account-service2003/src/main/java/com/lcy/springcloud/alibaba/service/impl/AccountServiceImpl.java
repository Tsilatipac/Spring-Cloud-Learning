package com.lcy.springcloud.alibaba.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lcy.springcloud.alibaba.dao.AccountDao;
import com.lcy.springcloud.alibaba.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("----> account-service中扣减用户余额开始");

        accountDao.decrease(userId,money);
        LOGGER.info("----> account-service中扣减用户余额开始");
    }
}
