package com.xiaoshu.client.handler;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.client.model.MyMessage;
import com.xiaoshu.client.util.MessageUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 功能说明： {@link io.netty.channel.ChannelInboundHandler}
 *
 * @ com.xiaoshu.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:38
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class ClientBussinessHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        MyMessage myMessage = MessageUtils.createMyMessage("client", "Im client -" + System.currentTimeMillis());
        Thread.sleep(3000);
        ctx.channel().writeAndFlush(JSON.toJSONString(myMessage));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        MyMessage message = JSON.parseObject(msg, MyMessage.class);
        System.out.println("[Client Receive Msg]" + message.getMessageId());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;
        switch (event.state()) {
            case WRITER_IDLE:
                System.out.println("[心跳触发] 每5秒触发...");
                MyMessage myMessage = MessageUtils.createMyMessage("client", "[Event]Im client -" + System.currentTimeMillis());
                Thread.sleep(3000);
                ctx.channel().writeAndFlush(JSON.toJSONString(myMessage));
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        if(channel.isActive()){
            // ... if channel still active , close the channel;
            channel.close();
        }
    }
}
