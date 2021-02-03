package com.xiaoshu.file.server.handler;

import cn.myzf.common.MessageFile;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 功能说明： 添加pipeline (添加编解码器，和业务处理服务handler)
 *
 * @ com.xiaoshu.file.server.handler
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@11:40
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileServerChannelInitialier extends ChannelInitializer<SocketChannel> {

    /**
     * 读空闲，10秒
     */
    private static final int READER_IDLE_TIME_SECONDS = 10;

    /**
     * 写空闲，不启用
     */
    private static final int WRITER_IDLE_TIME_SECONDS = 0;

    /**
     * 读写空闲， 不启用
     */
    private static final int ALL_IDLE_TIME_SECONDS = 0;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //检测空闲必须放在这里 因为pipeline是分顺序加载的
        pipeline.addLast("idleStateHandler", new IdleStateHandler(READER_IDLE_TIME_SECONDS, WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS));
        //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //服务器端接收的是客户端RequestUser对象，所以这边将接收对象进行解码生产实列
        pipeline.addLast(new ProtobufDecoder(MessageFile.Message.getDefaultInstance()));
        //Google Protocol Buffers编码器
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        //Google Protocol Buffers编码器
        pipeline.addLast(new ProtobufEncoder());
        // 添加自己的业务代码-1
        pipeline.addLast(new IdleStateServerHandler());
        // 添加自己的业务逻辑信息；
        pipeline.addLast(new FileServerHandler());

    }
}
