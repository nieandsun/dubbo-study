package com.nrsc;

import com.nrsc.service.HelloService;
import com.nrsc.service.impl.HelloServiceImpl;
import org.junit.Test;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.io.IOException;


public class SpringRmiServerApp {


    @Test
    public void springRmiServerTest() throws IOException {

        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        //设置暴露的主机名,127.0.0.1或localhost会报错 --- 这里我不细究了
        //rmiServiceExporter.setRegistryHost("localhost");
        //设置暴露服务的端口号
        rmiServiceExporter.setRegistryPort(8001);
        //设置暴露的服务名 远程调用地址应为 127.0.0.1:8001/HelloService
        rmiServiceExporter.setServiceName("HelloService");

        //设置暴露的接口
        rmiServiceExporter.setServiceInterface(HelloService.class);

        //指定暴露的具体服务
        HelloService helloService = new HelloServiceImpl();
        rmiServiceExporter.setService(helloService);

        //复写父类的afterPropertiesSet
        rmiServiceExporter.afterPropertiesSet();

        System.out.println("服务暴露完成!!!");
        System.in.read();
    }
}
