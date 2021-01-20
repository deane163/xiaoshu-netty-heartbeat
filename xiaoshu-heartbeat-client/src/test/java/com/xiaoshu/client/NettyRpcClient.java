package com.xiaoshu.client;

import com.xiaoshu.client.handler.ClientBussinessHandler;
import com.xiaoshu.client.handler.ClientChannelInitialer;
import com.xiaoshu.client.model.MyCommandType;
import com.xiaoshu.client.model.MyMessage;
import com.xiaoshu.client.util.MessageUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 功能说明：
 *
 * @ com.xiaoshu.client
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2021/1/19@16:51
 * <p>
 * Copyright (C)2012-@2021 深圳优必选科技 All rights reserved.
 */
public class NettyRpcClient {

    public void startUp(){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.handler(new ClientChannelInitialer());

        Channel channel = bootstrap.connect("127.0.0.1", 8888).channel();
        try {
            MyMessage myMessage = MessageUtils.createMyMessage("client", "Im client -" + System.currentTimeMillis());
            while (true){
                Thread.sleep(3000);
                channel.writeAndFlush(myMessage);
            }
            //channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void start() {
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(workerGroup).channel(NioServerSocketChannel.class);
//            bootstrap.handler(new LoggingHandler(LogLevel.INFO));
//            bootstrap.handler(new ClientChannelInitialer());
//
//            Channel channel = bootstrap.connect("127.0.0.1", 8888).channel();
//            while (true) {
//                Thread.sleep(3000);
//                MyMessage myMessage = MessageUtils.createMyMessage("client", "Im client -" + System.currentTimeMillis(), MyCommandType.PONG);
//                channel.writeAndFlush(myMessage);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
//    }

    public static void main(String[] args) {
        new NettyRpcClient().startUp();
    }
}
