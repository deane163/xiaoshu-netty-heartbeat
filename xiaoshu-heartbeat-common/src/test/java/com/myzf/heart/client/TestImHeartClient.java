package com.myzf.heart.client;

import com.myzf.heart.client.handler.HeartBeatClientInitialer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 功能说明：
 *
 * @ com.myzf.heart.client
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/18@19:47
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class TestImHeartClient {

    public static void main(String[] args) {
        new TestImHeartClient().start();
    }

    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.handler(new HeartBeatClientInitialer());

        Channel channel = bootstrap.connect("127.0.0.1", 8090).channel();
        try {

            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
