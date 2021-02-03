package com.xiaoshu.file.client.handler;


import cn.myzf.common.MessageFile;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * 功能说明： 添加pipeline (添加编解码器，和业务处理服务handler)
 *
 * @ com.xiaoshu.file.client.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@11:45
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileClientChannelInitialier extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //将接收到的二进制文件解码成具体的实例，这边接收到的是服务端的ResponseBank对象实列
        pipeline.addLast(new ProtobufDecoder(MessageFile.Message.getDefaultInstance()));
        //Google Protocol Buffers编码器
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        //Google Protocol Buffers编码器
        pipeline.addLast(new ProtobufEncoder());
        // 添加自己的业务服务
        pipeline.addLast(new FileClientHandler());
    }
}
