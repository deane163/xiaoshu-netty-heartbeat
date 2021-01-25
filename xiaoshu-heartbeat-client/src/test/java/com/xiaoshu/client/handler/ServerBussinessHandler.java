package com.xiaoshu.client.handler;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.client.model.MyMessage;
import com.xiaoshu.client.util.MessageUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:33
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class ServerBussinessHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[client connect] client connect" + ctx.channel().toString());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        MyMessage message = JSON.parseObject(msg, MyMessage.class);
        System.out.println("[receive]" + message.getMessageId());
        MyMessage myMessage = MessageUtils.createMyMessage("admin", "Im server");
        ctx.channel().writeAndFlush(JSON.toJSONString(myMessage));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        //……
        if (channel.isActive()) ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
