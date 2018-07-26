package com.vitea.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
/**
 * 客户端初始化
 * @author liushahe
 *
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ClientHandler());
		ch.pipeline().addLast(
				new ObjectDecoder(2048, ClassResolvers
						.cacheDisabled(this.getClass().getClassLoader())));
		
		ch.pipeline().addLast(new ObjectEncoder());
         System.out.println("channel");
		
	}

}
