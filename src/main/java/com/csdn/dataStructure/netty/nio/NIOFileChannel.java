package com.csdn.dataStructure.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/23 13:04
 * @description：
 */

/**
 * FileChannel 示例（操作文件,写入文件）
 */
public class NIOFileChannel {

    public static void main(String[] args) throws Exception {

        //要写入文件的字符串
        String string = "hello world";

        //实际操作的io流，channel只是对io的封装
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //获取channel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //要写入文件的字符串，推入缓存
        byteBuffer.put(string.getBytes());

        //转换buffer状态，从写改读
        byteBuffer.flip();

        //读取buffer中数据
        fileChannel.write(byteBuffer);

        fileOutputStream.close();

    }

}
