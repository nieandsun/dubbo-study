package com.nrsc.utils;

import com.nrsc.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessHandler implements Runnable {

    Socket socket;
    Object service;

    public ProcessHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    //这里真正的去使用反射调用请求进来的接口的方法
    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            //获取请求进来要调用的方法
            RpcRequest request = (RpcRequest) inputStream.readObject();
            System.out.println("接收到请求");
            Object result = invoke(request);

            //返回调用结果
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert outputStream != null;
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过反射的方式找到要调用的类名，并调用方法
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Object invoke(RpcRequest request) throws Exception {
        Class<?> clazz = Class.forName(request.getServiceName());
        Method method = clazz.getDeclaredMethod(request.getMethodName(), request.getParameterTypes());
        Object result = method.invoke(service, request.getArguments());
        return result;
    }
}
