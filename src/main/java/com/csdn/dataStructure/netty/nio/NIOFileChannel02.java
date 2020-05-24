package com.csdn.dataStructure.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/23 14:15
 * @description：
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(new File("d:\\file01.txt"));

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

    }
}
