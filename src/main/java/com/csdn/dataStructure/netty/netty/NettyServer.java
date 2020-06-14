package com.csdn.dataStructure.netty.netty;

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
 * @date ：Created in 2020/5/26 22:09
 * @description：
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组，bossGroup和workerGroup，bossGroup只接受accept事件，
        //并把与客户端相关联的channel注册到workerGroup上， workerGroup只关注read/write事件
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(bossGroup, workerGroup)     //设置两个线程组
                    .channel(NioServerSocketChannel.class)      //使用NioServerSocketChannel作为服务器的通道类型
                    .option(ChannelOption.SO_BACKLOG, 128)      //设置线程队列得到连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)      //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {     //创建一个通道测试对象

                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new NioServerHandle());
                        }

                    });     //给我们的workerGroup设置handle处理器

            //绑定监听端口并同步，返回ChannelFuture对象
            //执行即启动服务器
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
