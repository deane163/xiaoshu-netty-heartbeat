package com.xiaoshu.client;

import com.xiaoshu.client.handler.ServerChannelInitialer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@16:51
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class NettyRpcServer {

    public static void main(String[] args) {
        new NettyRpcServer().start(8888);
    }

    public void start(int port) {
        System.out.println("[Server up] server up on time :{}" + System.currentTimeMillis());
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // --
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 100);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(new ServerChannelInitialer());
            // 绑定端口服务；
            ChannelFuture future = serverBootstrap.bind("127.0.0.1", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭 NioEventLoopGroup  (事件循环组)
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
