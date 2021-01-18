package cn.myzf.server.typeServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class TCPServer {

	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap serverBootstrap;

	@Autowired
	@Qualifier("tcpSocketAddress")
	private InetSocketAddress tcpIpAndPort;


	private Channel serverChannel;

	//@PostConstruct（会导致其他东西不运行）
	public void start() throws Exception {
		ChannelFuture future = serverBootstrap.bind(tcpIpAndPort).sync();
		serverChannel = future.channel();
		System.out.println("[Server start] TCP Server start , listen is :" + tcpIpAndPort.getPort());
		future.channel().closeFuture().sync();
	}

	//@PreDestroy
	public void stop() throws Exception {
		serverChannel.close();
		serverChannel.parent().close();
	}

	public ServerBootstrap getServerBootstrap() {
		return serverBootstrap;
	}

	public void setServerBootstrap(ServerBootstrap serverBootstrap) {
		this.serverBootstrap = serverBootstrap;
	}

	public InetSocketAddress getTcpPort() {
		return tcpIpAndPort;
	}

	public void setTcpPort(InetSocketAddress tcpPort) {
		this.tcpIpAndPort = tcpPort;
	}
}
