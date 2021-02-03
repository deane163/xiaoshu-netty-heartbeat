package com.xiaoshu.file.server.handler;

import cn.myzf.common.MessageFile;
import com.xiaoshu.file.client.common.ChannelServerRepository;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.file.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/26@10:35
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class IdleStateServerHandler extends SimpleChannelInboundHandler<MessageFile.Message> {

    // 定义客户端没有收到服务端的pong消息的最大次数
    private static final int MAX_UN_REC_PING_TIMES = 3;

    // 失败计数器：未收到client端发送的ping请求
    private int unRecPingTimes = 0;

    /**
     * 空闲触发器 心跳基于空闲实现
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            String type = "";
            switch (state){
                case READER_IDLE:
                    type = "read idle";
                    break;
                case WRITER_IDLE:
                    type = "write idle";
                    break;
                case ALL_IDLE:
                    type = "all idle";
                    break;
                default:
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"超时类型：" + type);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "连接成功"  );
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFile.Message message) throws Exception {
        System.out.println("Idle state server :" + message.getGid());
        Channel channel = ChannelServerRepository.get(message.getFrom());
        if(null == channel){
            channel = ctx.channel();
            ChannelServerRepository.put(message.getFrom(), ctx.channel());
        }
        if(channel.isOpen()){
            channel.writeAndFlush(message);
        }
        //触发下一个handler(如果没有的话，message不会传递到下一个handler)
        ctx.fireChannelRead(message);
        ReferenceCountUtil.release(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
