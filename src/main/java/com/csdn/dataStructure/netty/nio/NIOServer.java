package com.csdn.dataStructure.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/24 20:43
 * @description：
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        //创建服务器端的serverSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //创建选择器（selector）
        Selector selector = Selector.open();

        //配置serverSocketChannel通道监听端口
        serverSocketChannel.bind(new InetSocketAddress(6666));

        //配置serverSocketChannel为非阻塞，只有为非阻塞时，才可以注册到selector上
        serverSocketChannel.configureBlocking(false);

        //serverSocketChannel注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            if(selector.select(1000) == 0){
                System.out.println("已经等待1s。");
                continue;
            }

            //获取有事件发生的selectionKey集合，并遍历
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("已有一个连接事件");

                }
                if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println(new String(buffer.array()));
                }
                iterator.remove();
            }
        }

    }
}
