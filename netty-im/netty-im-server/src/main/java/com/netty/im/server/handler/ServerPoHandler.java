package com.netty.im.server.handler;

import com.netty.im.core.message.Message;
import com.netty.im.server.core.ConnectionPool;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerPoHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger= LoggerFactory.getLogger(ServerPoHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if (ConnectionPool.getChannel(ctx.channel().remoteAddress().toString()) == null) {
			ConnectionPool.putChannel(ctx.channel().remoteAddress().toString(), ctx);
		}
ctx.flush();
	}

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    System.out.println("channelRead");
	    logger.info("channelRead");
		//Message message = (Message) msg;

		//System.err.println("server:" + message.getId());
		ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
