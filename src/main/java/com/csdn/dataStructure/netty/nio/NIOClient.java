package com.csdn.dataStructure.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/24 21:31
 * @description：
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        if (!socketChannel.connect(address)) {
            while (socketChannel.finishConnect()){
                System.out.println("连接zhong");
            }
        }
        String str = "hello";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        System.out.println("ok");
        System.in.read();

    }
}
