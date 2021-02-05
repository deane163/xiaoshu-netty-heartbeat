package cn.myzf.server.channelInitializer;


import cn.myzf.common.protobuf.Message.MessageBase;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 初始化 {@link ChannelInitializer}, 添加编解码器；
 *
 * @param
 * @Author: myzf
 * @Date: 2019/2/23 13:22
 */
@Component(value = "serverChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${server.READER_IDLE_TIME_SECONDS}")
    private int READER_IDLE_TIME_SECONDS;

    @Value("${server.WRITER_IDLE_TIME_SECONDS}")
    private int WRITER_IDLE_TIME_SECONDS;

    @Value("${server.ALL_IDLE_TIME_SECONDS}")
    private int ALL_IDLE_TIME_SECONDS;


    @Autowired
    @Qualifier("serverHeartHandler")
    private ChannelInboundHandlerAdapter serverHeartHandler;

    @Autowired
    @Qualifier("otherServerHandler")
    private ChannelInboundHandlerAdapter otherServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        //检测空闲必须放在这里 因为pipeline是分顺序加载的
        p.addLast("idleStateHandler", new IdleStateHandler(READER_IDLE_TIME_SECONDS
                , WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS, TimeUnit.SECONDS));
        //解码器必须放在前面，否则发数据收不到, 添加protobuf解码器
        p.addLast(new ProtobufVarint32FrameDecoder());
        //添加protobuf对应类解码器
        p.addLast(new ProtobufDecoder(MessageBase.getDefaultInstance()));
        //protobuf的编码器 和上面对对应
        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        //protobuf的编码器
        p.addLast(new ProtobufEncoder());

        //自定义的hanlder (处理自身的业务逻辑处理)
        p.addLast("serverHeartHandler", serverHeartHandler);
        p.addLast("otherServerHandler", otherServerHandler);
    }
}
