package com.xiaoshu.client.handler;

import com.xiaoshu.client.coder.MsgPackDecoder;
import com.xiaoshu.client.coder.MsgPackEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:30
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class ServerChannelInitialer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加编解码器；
        pipeline.addLast("Decoder", new MsgPackDecoder());
        pipeline.addLast("Encoder", new MsgPackEncoder());

        pipeline.addLast(new ServerBussinessHandler());
    }
}
