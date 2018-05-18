package com.cxy.classicProblems.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by lidongpeng on 2018/4/24.
 */
public class SelectorTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("D:\\pac.txt", "rw");

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress("192.168.1.0",3000));
            while(true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                //do something with socketChannel...
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
