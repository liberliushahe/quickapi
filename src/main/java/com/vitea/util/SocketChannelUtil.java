package com.vitea.util;

import com.vitea.handler.ClientInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 处理socket请求工具类
 * @author liushahe
 *
 */
public class SocketChannelUtil {
	public static void Connection(String ip,int port){
		  EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
	        Bootstrap bootstrap = new Bootstrap();

	        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientInitializer());
	        ChannelFuture channelFuture=null;
	        try {
	           channelFuture = bootstrap.connect(ip, port).sync();
	            
	            if(channelFuture.isSuccess()){
	            	System.out.println("server connection success!......");
	            	
	            }
	            //channelFuture.channel().closeFuture().sync();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        
	        }finally {
	            eventLoopGroup.shutdownGracefully();
	        }
	}

}
