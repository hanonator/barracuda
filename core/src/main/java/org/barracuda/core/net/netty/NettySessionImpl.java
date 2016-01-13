package org.barracuda.core.net.netty;

import org.barracuda.core.net.Channel;
import org.barracuda.core.net.Session;

import io.netty.channel.ChannelHandlerAdapter;

/**
 * 
 * 
 * @author brock
 *
 */
public class NettySessionImpl extends ChannelHandlerAdapter implements Session, Channel {

	@Override
	public void read(Object object) {
		
	}

	@Override
	public void write(Object object) {
		
	}

	@Override
	public void flush() {
		
	}

}