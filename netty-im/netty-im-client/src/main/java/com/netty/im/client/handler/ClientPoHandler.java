package com.netty.im.client.handler;

import com.netty.im.core.message.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 当编解码器为实体对象时时用来接收数据
 * @author yinjihuan
 *
 */
public class ClientPoHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Message message = (Message) msg;
		System.out.println("client:" + message.getContent() + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		ctx.writeAndFlush(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
}
