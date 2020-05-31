package com.csdn.dataStructure.netty.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/31 23:39
 * @description：
 */

/**
 * 1、SimpleChannelInboundHandler 继承 ChannelInboundHandle
 * 2、HttpRequest 参数，表示handle会将请求封装成HttpRequest类型
 */
public class TestHttpServerHandle extends SimpleChannelInboundHandler<HttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {

        if (msg instanceof HttpRequest) {

            System.out.println(msg.getClass());
            System.out.println(ctx.channel().remoteAddress());

            ByteBuf byteBuf = Unpooled.copiedBuffer("hello, 客户端, 我是服务器！", CharsetUtil.UTF_8);

            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            ctx.channel().writeAndFlush(httpResponse);
        }
    }

}
