package com.xiaoshu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能说明： 心跳服务公共服务类定义；
 *  *
 * @ com.xiaoshu
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/01/11@20:42
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
@SpringBootApplication
@Slf4j
public class XiaoshuHeartbeatCommonApplication {

    public static void main(String[] args) {
        log.info("[Server up] start up the heart server on time :{}", System.currentTimeMillis());
        SpringApplication.run(XiaoshuHeartbeatCommonApplication.class, args);
    }

}
