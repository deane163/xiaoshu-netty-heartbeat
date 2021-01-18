package com.myzf.heart.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 功能说明：
 *
 * @ com.myzf.heart.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/18@20:02
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class HeartBeatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("[receive msg]:" + msg);
    }
}
