package com.nrsc;

import com.nrsc.service.OrderService;
import com.nrsc.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiServerApp {

    @Test
    public void rmiServerTest() throws IOException, AlreadyBoundException {

        OrderService orderService = new OrderServiceImpl();
        //注冊通讯端口
        LocateRegistry.createRegistry(OrderService.port);
        //注冊通讯路径,其实就是暴露服务
        //消费端就可以通过该暴露的URL，找到暴露的OrderService的具体实现OrderServiceImpl
        //并且拿到结果返回给消费方 ---> 是不是很神奇？？？
        Naming.bind(OrderService.RMI_URL, orderService);

        System.out.println("初始化rmi绑定");
        System.in.read();
    }
}
