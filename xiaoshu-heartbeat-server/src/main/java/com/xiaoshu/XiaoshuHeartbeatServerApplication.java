package com.xiaoshu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能说明： 使用Netty 进行 心跳传递操作；（通过集成netty框架实现心跳的统计）
 * 监控功能：如果有客户端的心跳异常，可以及时通知到该服务端；
 *
 * @ com.xiaoshu
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/01/11@09:32
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
@SpringBootApplication
public class XiaoshuHeartbeatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoshuHeartbeatServerApplication.class, args);
    }

}
