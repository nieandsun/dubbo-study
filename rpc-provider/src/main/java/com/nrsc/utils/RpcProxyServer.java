package com.nrsc.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * socket服务端，用于发布接口的实现类
 */
public class RpcProxyServer {

    //线程池，socket连接之后把请求丢给其他线程去处理，避免阻塞
    private ExecutorService executorService = Executors.newCachedThreadPool();

    //用于发布服务
    public void publisher(Object service, int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);

            //把调用请求丢到线程池中处理
            while (true) {
                socket = serverSocket.accept();
                System.out.println("接收到来自" + socket.getPort() + "的连接");
                executorService.execute(new ProcessHandler(socket, service));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
