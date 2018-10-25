package com.xinchao;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Auther: 杜威
 * @Date: 2018/10/25 11:17
 * @Description:
 */
public class DataTest {
    @Test
    public void test() throws Exception {

        Selector sel = Selector.open();

        final SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        socketChannel.configureBlocking(false);
        socketChannel.register(sel, SelectionKey.OP_READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
                int i = 0;
                while (true) {
                    byte[] sendBytes = ("Tom" + (i++)).getBytes();
                    sendBuffer.put(sendBytes);
                    sendBuffer.flip();
                    try {
                        socketChannel.write(sendBuffer);
                        Thread.sleep(500);
                        sendBuffer.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        while (true) {
            sel.select();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (socketChannel.read(buffer) > 0) {
                buffer.flip();
                bos.write(buffer.array(),0,buffer.limit());
                buffer.clear();
            }
            sel.selectedKeys().clear();
            System.out.println(new String(bos.toByteArray()));
        }
    }
}

