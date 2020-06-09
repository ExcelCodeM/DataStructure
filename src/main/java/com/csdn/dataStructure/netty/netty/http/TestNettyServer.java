package com.csdn.dataStructure.netty.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
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
                    .option(ChannelOption.SO_BACKLOG, 128)      //设置线程队列得到连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)      //设置保持活动连接状态
                    .childHandler(new TestNettyInitializer());

            ChannelFuture channelFuture = bootstrap.bind(12345).sync();

            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
