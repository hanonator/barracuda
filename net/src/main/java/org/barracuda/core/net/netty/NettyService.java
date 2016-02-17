package org.barracuda.core.net.netty;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.ServiceException;
import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.context.Service;
import org.barracuda.horvik.inject.Inject;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * 
 * @author brock
 */
@Service
public class NettyService {
	
	/**
	 * The static logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(NettyService.class);
	
	/**
	 * The port the service is bound to
	 */
	@Resource(name = "service.net.port")
	private static final int port = 43594;

	/**
	 * The host the service is bound to  
	 */
	@Resource(name = "service.net.host")
	private static final String host = "localhost";
	
	/**
	 * The server bootstrap
	 */
	private final ServerBootstrap bootstrap = new ServerBootstrap();
	
	/**
	 * The boss event loop group
	 */
	private final EventLoopGroup boss_group = new NioEventLoopGroup();
	
	/**
	 * The worker event loop group
	 */
	private final EventLoopGroup worker_group = new NioEventLoopGroup();
	
	/**
	 * The horvik container
	 */
	@Inject
	private Horvik horvik;

	/**
	 * Starts the netty service
	 * 
	 * @throws ServiceException
	 */
	public void start() throws ServiceException {
		try {
			bootstrap.group(boss_group, worker_group).channel(NioServerSocketChannel.class).childHandler(new NettyChannelInitializer(this, horvik))
					.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
	
			/*
			 * Bind and start to accept incoming connections.
			 */
			ChannelFuture f = bootstrap.bind(host, port).sync();
			
			/*
			 * Debug information
			 */
			logger.info("Service started on port {}", port);
			
			/*
			 * Wait until the server socket is closed to shut down the server
			 */
			f.channel().closeFuture().sync();
		} catch (Exception ex) {
			throw new ServiceException("could not start Netty server", ex);
		} finally {
			worker_group.shutdownGracefully();
			boss_group.shutdownGracefully();
		}
	}

}