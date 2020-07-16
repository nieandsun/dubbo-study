package com.nrsc.utils;

import com.nrsc.rpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RpcInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始进行代理");
        //封装请求
        RpcRequest request = new RpcRequest();
        //request.setClassName(proxy.getClass().getName());
        request.setServiceName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setArguments(args);
        request.setParameterTypes(method.getParameterTypes());

        //远程调用
        RpcNetTransport netTransport = new RpcNetTransport(host, port);
        Object result = netTransport.send(request);
        return result;
    }
}
