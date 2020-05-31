package com.csdn.dataStructure.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/26 23:00
 * @description：
 */

/**
 * 说明：
 * 1、我们自定义的handle需要继承netty提供的HandleAdapter（规范），此时，我们的handle才可以成为一个handle
 */
public class NioServerHandle extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据方法（读取客户端发送的消息）
     *
     * @param ctx 上下文对象，可以获取 管道 channel、 通道 pipeline、 地址 等
     * @param msg 客户端发送的数据， Object对象
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                System.out.println("hello, server 01");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        System.out.println("go on ..");

//        System.out.println("server ctx = " + ctx);
//        ByteBuf byteBuf = (ByteBuf) msg;    //netty提供的buffer缓冲区
//        System.out.println("客户端发送的数据是：" + byteBuf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址是：" + ctx.channel().remoteAddress());

    }

    /**
     * 读取完客户端发送数据执行的方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.channel().close();

    }
}
