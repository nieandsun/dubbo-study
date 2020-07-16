package com.nrsc.utils;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    /**
     * @param host         请求的主机
     * @param port         端口
     * @param proxyedClass 被代理的类
     * @param <T>
     * @return 返回被代理的类的实例
     */
    public <T> T clientProxy(String host, int port, Class<T> proxyedClass) {

        return (T) Proxy.newProxyInstance(proxyedClass.getClassLoader(), new Class[]{proxyedClass}, new RpcInvocationHandler(host, port));

    }
}
