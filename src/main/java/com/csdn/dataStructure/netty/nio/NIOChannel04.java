package com.csdn.dataStructure.netty.nio;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/24 12:46
 * @description：
 */

/**
 * channel通道，复制
 */
public class NIOChannel04 {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\1\\Desktop\\1.jpg"));
        FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\1\\Desktop\\11.jpg"));

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        //inputStreamChannel.transferTo(0, inputStreamChannel.size(), outputStreamChannel);
        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        inputStream.close();
        outputStream.close();
    }
}
