package com.csdn.dataStructure.netty.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/31 23:08
 * @description：
 */
public class TestNettyServer {

    public static void main(String[] args) throws Exception {

        //创建两个线程组，bossGroup和workerGroup，bossGroup只接受accept事件，
        //并把与客户端相关联的channel注册到workerGroup上， workerGroup只关注read/write事件
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestNettyInitializer());

            ChannelFuture channelFuture = bootstrap.bind(6668).sync();

            channelFuture.channel().closeFuture();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
