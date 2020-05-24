package com.csdn.dataStructure.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/19 23:36
 * @description：
 */
public class BIO {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            final Socket socket = serverSocket.accept();
            System.out.println("成功监听到socket");
            executorService.execute(new Runnable() {
                public void run() {
                    handle(socket);
                }
            });
        }

    }

    public static void handle(Socket socket){
        try {
            System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes, 0, read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
