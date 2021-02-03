package com.xiaoshu.file.server;

import com.xiaoshu.file.server.handler.FileServerChannelInitialier;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 功能说明： 通过Protobuf编解码，进行文件的传输操作；（服务器端配置，雏形）
 *
 * @ com.xiaoshu.file.server
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@11:40
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileTransServer {

    /**
     * 启动 Netty Server配置类
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("File Trans server is start up on time " + System.currentTimeMillis());
        new FileTransServer().start(8888);
    }

    // 配置  ServerBootstrap 启动配置类
    public void start(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO)).option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_BACKLOG, 100).option(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.childHandler(new FileServerChannelInitialier());

            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
