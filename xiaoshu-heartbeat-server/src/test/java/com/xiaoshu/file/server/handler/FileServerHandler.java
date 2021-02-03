package com.xiaoshu.file.server.handler;

import cn.myzf.common.MessageFile;
import com.xiaoshu.file.MessageFileProtobufTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * 功能说明： 业务处理业务逻辑；
 *
 * @ com.xiaoshu.file.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@13:42
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<MessageFile.Message> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFile.Message message) throws Exception {
        System.out.println("[receive message]" + message.getGid());
        MessageFile.File fileObject = message.getMessageContent().getContent().unpack(MessageFile.File.class);
        MessageFileProtobufTest.readBin2Image(fileObject.getData().toByteArray(), "D:/temp/11zzz"+ System.currentTimeMillis()+ ".png");

        ReferenceCountUtil.release(message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        //TODO 通道数据读取完成后，进行处理的业务逻辑操作；
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[客户端] 客户端异常断开，客户端地址为：{}, cause is :{}" + ctx.channel().remoteAddress() + cause.toString());
        super.exceptionCaught(ctx, cause);
    }
}
