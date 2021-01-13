package com.xiaoshu;

import com.xiaoshu.server.TcpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
@Slf4j
public class XiaoshuHeartbeatServerApplication {

    public static void main(String[] args) throws Exception {
        log.info("[Server up] start up the heart beat server on time :{}", System.currentTimeMillis());
        ConfigurableApplicationContext context = SpringApplication.run(XiaoshuHeartbeatServerApplication.class, args);
        TcpServer tcpServer = context.getBean(TcpServer.class);
        tcpServer.start();
    }

}
