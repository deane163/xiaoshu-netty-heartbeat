package com.myzf.heart.server.handler;

import com.myzf.heart.model.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 功能说明：
 *
 * @ com.myzf.heart.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/18@19:53
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            System.out.println(((IdleStateEvent)evt).state());
            switch (event.state()){
                case READER_IDLE:
                    ctx.writeAndFlush("hello I'm server, don't want read");
                    break;
                case WRITER_IDLE:
                    ctx.writeAndFlush("hello I'm server, don't want write");
                default:
                    break;
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.printf("[Receive msg] receive message is :{}" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.toString());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
        System.out.printf("关闭远程接口");
        super.channelInactive(ctx);
    }

    public String createMessage(){
        NettyMessage<String> msg = new NettyMessage<>();
        return null;
    }
}
