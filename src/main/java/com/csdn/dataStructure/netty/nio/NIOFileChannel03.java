package com.csdn.dataStructure.netty.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/24 12:21
 * @description：
 */

/**
 * 使用一个buffer 实现文件复制
 */
public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("d:\\1.txt"));
        FileChannel inputChannel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        inputChannel.read(buffer);

        buffer.flip();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\2.txt"));
        FileChannel outputChannel = fileOutputStream.getChannel();

        outputChannel.write(buffer);

        fileInputStream.close();
        fileOutputStream.close();

    }

}
