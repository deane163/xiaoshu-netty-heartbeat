package com.xiaoshu.client.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client.coder
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:12
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class MsgPackEncoder  extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack msgPack = new MessagePack();
//      编码，然后转为ButyBuf传递
        byte[] bytes = msgPack.write(o);
        byteBuf.writeBytes(bytes);
    }
}
