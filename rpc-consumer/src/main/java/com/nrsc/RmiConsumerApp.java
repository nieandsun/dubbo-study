package com.nrsc;

import com.nrsc.service.OrderService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class RmiConsumerApp {

    @Test
    public void rmiConsumerTest() throws RemoteException, NotBoundException, MalformedURLException {
        //通过服务端暴露的URL,取到远程服务的实现
        OrderService orderService = (OrderService) Naming.lookup(OrderService.RMI_URL);
        //呼叫远程服务，并通过反射等获取到远程方法调用的结果
        Map<String, String> info = new HashMap();
        info.put("target", "orderService");
        info.put("methodName", "getOrderInfo");
        info.put("arg", "1");
        Map<String, String> result = orderService.getOrderInfo(info);
        System.out.println("远程调用结果为:" + result);
    }
}
