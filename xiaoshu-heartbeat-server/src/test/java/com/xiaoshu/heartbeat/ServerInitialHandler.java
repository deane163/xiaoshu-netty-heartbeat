package com.xiaoshu.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 功能说明： 添加编解码器和心跳机制实现；（辅助功能的实现--编解码和心跳机制）
 *
 * @ com.xiaoshu.heartbeat
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/15@13:48
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */

public class ServerInitialHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 先添加解码器， 在添加编码器；
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        pipeline.addLast(new IdleStateHandler(2, 2, 2, TimeUnit.SECONDS));
        pipeline.addLast(new HeartBeatHandler());
    }
}
