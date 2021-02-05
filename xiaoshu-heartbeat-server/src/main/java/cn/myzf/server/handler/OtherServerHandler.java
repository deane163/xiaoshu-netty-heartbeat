package cn.myzf.server.handler;

import cn.myzf.common.protobuf.Command;
import cn.myzf.common.protobuf.Command.CommandType;
import cn.myzf.common.protobuf.Message;
import cn.myzf.common.protobuf.Message.MessageBase;
import cn.myzf.server.components.ChannelRepository;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * 其他业务拓展模板参考
 *
 * @param
 * @Author: myzf
 * @Date: 2019/2/23 13:24
 */
@Component(value = "otherServerHandler")
@ChannelHandler.Sharable
public class OtherServerHandler extends ChannelInboundHandlerAdapter {
    private final AttributeKey<String> clientInfo = AttributeKey.valueOf("clientInfo");
    public Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    @Qualifier("channelRepository")
    private ChannelRepository channelRepository;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message.MessageBase msgBase = (Message.MessageBase) msg;
        log.info(msgBase.getData());
        ChannelFuture cf = ctx.writeAndFlush(
                this.createMessageBase(msgBase.getClientId(),CommandType.UPLOAD_DATA_BACK, "这是业务层的逻辑" )
        );
        /* 给添加监听事件，当事件处理成功时；上一条消息发送成功后，立马推送一条消息 */
        cf.addListener(future -> {
            if (future.isSuccess()) {
                ctx.writeAndFlush(
                    this.createMessageBase(msgBase.getClientId(),CommandType.PUSH_DATA, "开始发送业务数据了。。。" )
                );
            }
        });
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("[Channel Read Complete] read complete on time :{}", System.currentTimeMillis());
    }

    /**
     * 创建消息基本信息；
     * @param clientId
     * @param commandType
     * @param data
     * @return
     */
    private MessageBase createMessageBase(String clientId, Command.CommandType commandType, String data){
        MessageBase.Builder builder = MessageBase.newBuilder();
        builder.setClientId(clientId).setCmd(commandType).setData(data);
        return builder.build();
    }


}
