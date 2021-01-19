package com.myzf.heart.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 功能说明：
 *
 * @ com.myzf.heart.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/18@19:48
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class HeartBeatClientInitialer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加编解码器；
        pipeline.addLast("Decoder", new StringDecoder());
        pipeline.addLast("Encoder", new StringEncoder());
        // 添加心跳处理
        pipeline.addLast(new IdleStateHandler(2,0, 0, TimeUnit.SECONDS));
        // 添加业务逻辑；
        pipeline.addLast(new HeartBeatClientHandler());
    }
}
