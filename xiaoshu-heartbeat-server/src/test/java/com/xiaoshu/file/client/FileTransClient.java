package com.xiaoshu.file.client;

import cn.myzf.common.MessageFile;
import cn.myzf.server.config.MessageType;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.xiaoshu.file.MessageFileProtobufTest;
import com.xiaoshu.file.client.handler.FileClientChannelInitialier;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * 功能说明： 通过Protobuf编解码，进行文件的传输操作；（客户端配置，雏形）
 *
 * @ com.xiaoshu.file.client
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/25@11:40
 * <p>
 * Copyright (C)2012-@2021 小树盛凯科技 All rights reserved.
 */
public class FileTransClient {

    public static void main(String[] args) {
        new FileTransClient().start(8888);
    }

    public static MessageFile.Message createMessage() {
        MessageFile.Message.Builder builder = MessageFile.Message.newBuilder();
        // 设置基础信息
        builder.setCid("15646481133-000000");
        builder.setGid(String.valueOf(16464813587931313L));
        builder.setCreatedAt(new Date().getTime());
        builder.setFrom("4");
        builder.setTo("6");
        builder.setType(MessageFile.Message.Type.PRIVATE);
        byte[] file = MessageFileProtobufTest.getBytesByFile("D:/temp/11zzz.png");
        // 设置body信息
        MessageFile.File.Builder fileContentBuilder = MessageFile.File.newBuilder();
        fileContentBuilder.setData(ByteString.copyFrom(file));
        fileContentBuilder.setSize(1024);
        fileContentBuilder.setPrivate(true);
        fileContentBuilder.setType("private");
        // 构造消息服务
        MessageFile.MessageContent.Builder contentBuilder = MessageFile.MessageContent.newBuilder();
        contentBuilder.setContentType(MessageType.FILE);
        contentBuilder.setContent(Any.pack(fileContentBuilder.build()));
        builder.setMessageContent(contentBuilder.build());
        MessageFile.Message message = builder.build();
        return message;
    }

    public void start(int port) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class);
            bootstrap.handler(new FileClientChannelInitialier());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", port);
            Channel channel = channelFuture.channel();
            channel.writeAndFlush(createMessage());
            channelFuture.sync();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
