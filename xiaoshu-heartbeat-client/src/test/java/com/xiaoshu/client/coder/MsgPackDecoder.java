package com.xiaoshu.client.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * 功能说明：解码器
 *
 * @ com.xiaoshu.client.coder
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@17:08
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//      获取要解码的byte数组
        final byte[] bytes;
        final int length = byteBuf.readableBytes();
        bytes = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);
//      调用MessagePack 的read方法将其反序列化为Object对象
        MessagePack msgPack = new MessagePack();
        list.add(msgPack.read(bytes));
    }
}
