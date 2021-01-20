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
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:31
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class ClientChannelInitialer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加编解码器

        pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(65535,0,
                2,0,2));
        pipeline.addLast("msgpack译码器",new MsgPackDecoder());
        pipeline.addLast("frameEncoder",new LengthFieldPrepender(2));
        pipeline.addLast("msgpack编码器",new MsgPackEncoder());

        pipeline.addLast(new ClientBussinessHandler());
    }
}
