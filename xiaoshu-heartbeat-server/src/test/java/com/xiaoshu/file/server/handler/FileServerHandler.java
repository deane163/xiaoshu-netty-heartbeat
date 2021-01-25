package com.xiaoshu.file.server.handler;

import cn.myzf.common.MessageFile;
import com.xiaoshu.file.MessageFileProtobufTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.file.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@13:42
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<MessageFile.Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFile.Message message) throws Exception {
        System.out.println("[receive message]" + message.getGid());
        MessageFile.File fileObject = message.getMessageContent().getContent().unpack(MessageFile.File.class);
        MessageFileProtobufTest.readBin2Image(fileObject.getData().toByteArray(), "D:/temp/11zzz"+ System.currentTimeMillis()+ ".png");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
