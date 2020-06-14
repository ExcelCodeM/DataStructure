package com.csdn.dataStructure.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/27 21:48
 * @description：
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new NioClientHandle());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668);

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
