package com.nrsc.utils;

import com.nrsc.rpc.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;


    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //远程调用
    public Object send(RpcRequest request) {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            Socket socket = new Socket(host, port);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;

    }
}
