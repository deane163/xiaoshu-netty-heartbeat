package com.xiaoshu.file.server.handler;

import cn.myzf.common.MessageFile;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 功能说明： 添加其它的业务性操作
 *
 * @ com.xiaoshu.file.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/2/7@9:49
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class OtherBusinessHandler extends SimpleChannelInboundHandler<MessageFile.Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFile.Message msg) throws Exception {
        // TODO  进行其它的业务性处理；
        System.out.println("doing other Business ");
    }
}
