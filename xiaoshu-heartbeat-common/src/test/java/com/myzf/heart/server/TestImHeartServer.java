package com.myzf.heart.server;

import com.myzf.heart.server.handler.HeartBeatInitialer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 功能说明：
 *
 * @ com.myzf.heart
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/18@19:36
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class TestImHeartServer {

    public EventLoopGroup workerGroup(){
        return new NioEventLoopGroup();
    }

    public EventLoopGroup bossGroup(){
        return new NioEventLoopGroup();
    }

    public void start(){
        EventLoopGroup bossGroup = bossGroup();
        EventLoopGroup workerGroup = workerGroup();
        // 创建 ServerBootstrap
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HeartBeatInitialer());
            ChannelFuture future = serverBootstrap.bind("127.0.0.1", 8090).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TestImHeartServer().start();
    }

}
