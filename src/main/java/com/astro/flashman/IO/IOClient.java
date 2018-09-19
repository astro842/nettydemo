package com.astro.flashman.IO;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author lyh
 * @date 2018/9/10 
 * @version v
 */


public class IOClient {

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8888);
                while (true) {
                    String s = "client send:" + new Date().toString() + " hello world";
                    socket.getOutputStream().write(s.getBytes());
                    Thread.sleep(2000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}

