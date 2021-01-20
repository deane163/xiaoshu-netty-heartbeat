package com.xiaoshu.client.handler;

import com.xiaoshu.client.coder.MsgPackDecoder;
import com.xiaoshu.client.coder.MsgPackEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

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
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,
                2,0,2));
        ch.pipeline().addLast(new MsgPackDecoder());
        ch.pipeline().addLast(new LengthFieldPrepender(2));
        ch.pipeline().addLast(new MsgPackEncoder());

        pipeline.addLast(new ServerBussinessHandler());
    }
}
