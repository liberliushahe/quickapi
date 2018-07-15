package com.vitea.handler;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    	System.out.println("接受数据........");
    	// 接收客户端的数据
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output:" + msg);
        ctx.channel().writeAndFlush("from client:" + new Date());

    }
    @Override
	public void channelReadComplete(ChannelHandlerContext ctx)
			throws Exception{
		System.out.println("----------------handler channelReadComplete");
		ctx.flush();
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        System.out.println("----------------handler channelReaderror");
        ctx.channel().close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println("发送数据准备中。。。。。。。");
    	
    
    	
    	//给服务端发送数据
   
        System.out.println("发送数据准备完成。。。。。。。");
    }
	
}
