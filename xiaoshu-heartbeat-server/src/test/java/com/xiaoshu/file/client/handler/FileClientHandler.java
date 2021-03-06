package com.xiaoshu.file.client.handler;

import cn.myzf.common.MessageFile;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.file.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@13:49
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileClientHandler extends SimpleChannelInboundHandler<MessageFile.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFile.Message msg) throws Exception {
        System.out.println("[receive data]" + msg.getGid());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[客户端] 客户端异常断开，客户端地址为：{}, cause is :{}" + ctx.channel().remoteAddress() + cause.toString());
        super.exceptionCaught(ctx, cause);
    }
}
