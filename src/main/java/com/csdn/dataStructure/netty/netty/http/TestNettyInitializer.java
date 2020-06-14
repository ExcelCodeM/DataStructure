package com.csdn.dataStructure.netty.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/31 23:13
 * @description：
 */
public class TestNettyInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("myHttpServerCodec", new HttpServerCodec());
        pipeline.addLast("myTestHttpServerHandle",new TestHttpServerHandle());

        System.out.println("ok");
    }
}
