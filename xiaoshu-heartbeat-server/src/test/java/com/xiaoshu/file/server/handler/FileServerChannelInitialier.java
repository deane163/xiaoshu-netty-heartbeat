package com.xiaoshu.file.server.handler;

import cn.myzf.common.MessageFile;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.file.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@11:40
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileServerChannelInitialier extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //服务器端接收的是客户端RequestUser对象，所以这边将接收对象进行解码生产实列
        pipeline.addLast(new ProtobufDecoder(MessageFile.Message.getDefaultInstance()));
        //Google Protocol Buffers编码器
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        //Google Protocol Buffers编码器
        pipeline.addLast(new ProtobufEncoder());
        // 添加自己的业务逻辑信息；
        pipeline.addLast(new FileServerHandler());

    }
}
