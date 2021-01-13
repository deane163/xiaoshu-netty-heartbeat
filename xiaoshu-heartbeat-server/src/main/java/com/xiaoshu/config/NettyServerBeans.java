package com.xiaoshu.config;

import com.xiaoshu.channel.ServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.config
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/13@13:56
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
@Configuration
public class NettyServerBeans {

    @Value("${netty.boss.thread.count}")
    private int bossCount;

    @Value("${netty.worker.thread.count}")
    private int workerCount;

    @Autowired
    private ServerChannelInitializer serverChannelInitializer;

    /**
     * Netty Server 启动辅助类
     * @param bossGroup
     * @param workerGroup
     * @return
     */
    @Bean(name = "serverBootstrap")
    public ServerBootstrap serverBootstrap(NioEventLoopGroup bossGroup, NioEventLoopGroup workerGroup){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG));
        serverBootstrap.childHandler(serverChannelInitializer);
        return serverBootstrap;
    }

    @Bean
    public NioEventLoopGroup bossGroup(){
        return new NioEventLoopGroup(bossCount);
    }

    @Bean
    public NioEventLoopGroup workerGroup(){
        return new NioEventLoopGroup(workerCount);
    }

}
