package com.nrsc;

import com.nrsc.service.HelloService;
import org.junit.Test;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.util.HashMap;
import java.util.Map;

public class SpringRmiConsumerApp {

    @Test
    public void rmiConsumerTest() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        //指定要调用的远程服务接口
        rmiProxyFactoryBean.setServiceInterface(HelloService.class);
        //指定远程服务地址
        rmiProxyFactoryBean.setServiceUrl(HelloService.RMI_URL);
        //定义是否在首次配置远程服务后缓存该配置
        rmiProxyFactoryBean.setCacheStub(true);
        //是否在客户端启动的时候检测服务端服务可用性
        rmiProxyFactoryBean.setLookupStubOnStartup(true);
        //如果远程调用缓存配置报错,设置为true，允许重新调用
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);

        //复写父类的afterPropertiesSet方法
        rmiProxyFactoryBean.afterPropertiesSet();

        //呼叫远程服务，并通过反射等获取到远程方法调用的结果
        HelloService helloService = (HelloService) rmiProxyFactoryBean.getObject();
        Map<String, String> info = new HashMap();
        info.put("target", "orderService");
        info.put("methodName", "getOrderInfo");
        info.put("arg", "1");
        Map<String, String> result = helloService.getHelloInfo(info);
        System.out.println("远程调用结果为:" + result);
    }
}
