package com.xiaoshu.client.handler;

import com.xiaoshu.client.model.MyCommandType;
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
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class ServerBussinessHandler extends SimpleChannelInboundHandler<MyMessage> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[client connect] client connect" + ctx.channel().toString());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        System.out.println("[receive]" + msg.getMessageId());
        MyMessage myMessage = MessageUtils.createMyMessage("admin", "Im server");
        ctx.channel().writeAndFlush(myMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        //……
        if(channel.isActive())ctx.close();
    }

}
