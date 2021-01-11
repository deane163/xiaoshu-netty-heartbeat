package com.xiaoshu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能说明： 心跳服务的客户端，跟服务器端保持心跳连接，如果心跳异常，则上报信息；
 *
 * @ com.xiaoshu
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/01/11@09:59
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
@SpringBootApplication
public class XiaoshuHeartbeatClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoshuHeartbeatClientApplication.class, args);
    }

}
