package com.xiaoshu.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 功能说明：
 *
 * @ com.xiaoshu.channel
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/13@14:20
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
@Component(value = "serverChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("idleStateHandler", new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));

    }
}
