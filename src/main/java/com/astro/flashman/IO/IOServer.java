package com.astro.flashman.IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author lyh
 * @date 2018/9/10 
 * @version v
 */

public class IOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);

        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = ss.accept();
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] bytes = new byte[1024];
                            InputStream is = socket.getInputStream();
                            while ((len = is.read(bytes)) != -1) {
                                System.out.println(new String(bytes, 0, len));
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

