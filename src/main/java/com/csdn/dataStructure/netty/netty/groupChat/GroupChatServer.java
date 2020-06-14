package com.csdn.dataStructure.netty.netty.groupChat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/11 23:15
 * @description：
 */
public class GroupChatServer {

    public static void main(String[] ar) throws InterruptedException {

        EventLoopGroup bootGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bootGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast("myHandler", null);
                        }

                    });

            ChannelFuture channelFuture = serverBootstrap.bind(10000).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            bootGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
