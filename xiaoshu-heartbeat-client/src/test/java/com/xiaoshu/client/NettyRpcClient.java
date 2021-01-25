package com.xiaoshu.client;

import com.xiaoshu.client.handler.ClientChannelInitialer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@16:51
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class NettyRpcClient {

    public static void main(String[] args) {
        new NettyRpcClient().startUp();
    }

    public void startUp() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.handler(new ClientChannelInitialer());

        Channel channel = bootstrap.connect("127.0.0.1", 8888).channel();
        try {
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
