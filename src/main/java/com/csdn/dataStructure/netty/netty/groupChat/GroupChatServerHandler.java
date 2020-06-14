package com.csdn.dataStructure.netty.netty.groupChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/11 23:23
 * @description：
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("[用户]" + ctx.channel().remoteAddress() + "上线了\n");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[用户]" + ctx.channel().remoteAddress() + "上线了\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("[用户]" + ctx.channel().remoteAddress() + "离线了\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[用户]" + ctx.channel().remoteAddress() + "离线了\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(ch != channel){
                ch.writeAndFlush("[用户]" + ctx.channel().remoteAddress()+"::"+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
