package com.xiaoshu.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * 功能说明： 作为Netty Server的服务类；
 *
 * @ com.xiaoshu.server
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/13@14:06
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
@Component
@Slf4j
public class TcpServer {

    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap serverBootstrap;

    public void start() throws Exception {
        log.info("[Netty Server] start up the netty server on time :{}",  System.currentTimeMillis());
        ChannelFuture future = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 8090)).sync();
        Channel channel = future.channel();
        channel.closeFuture().sync();
    }
}
