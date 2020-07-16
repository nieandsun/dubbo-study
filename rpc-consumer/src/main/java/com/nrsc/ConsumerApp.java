package com.nrsc;

import com.nrsc.service.InfoService;
import com.nrsc.utils.RpcProxyClient;

public class ConsumerApp {
    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {
            RpcProxyClient client = new RpcProxyClient();
            InfoService infoService = client.clientProxy("localhost", 8888, InfoService.class);
            String result = infoService.sayHello("Tomas");
            System.out.println(result);
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            RpcProxyClient client = new RpcProxyClient();
            InfoService infoService = client.clientProxy("localhost", 8888, InfoService.class);
            String result = infoService.sayHello("Tomas");
            System.out.println(result);
        }, "Thread-2");

        Thread t3 = new Thread(() -> {
            RpcProxyClient client = new RpcProxyClient();
            InfoService infoService = client.clientProxy("localhost", 8888, InfoService.class);
            String result = infoService.sayHello("Tomas");
            System.out.println(result);
        }, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

    }

}
