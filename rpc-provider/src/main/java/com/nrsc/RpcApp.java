package com.nrsc;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.nrsc.service.DemoService;
import com.nrsc.service.impl.DemoServiceImpl;


import org.junit.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RpcApp {

    ExtensionLoader<ProxyFactory> proxyLoader = ExtensionLoader.getExtensionLoader(ProxyFactory.class);
    //支持的协议：dubbo、http、hessian、rmi等
    ExtensionLoader<Protocol> protocolLoader = ExtensionLoader.getExtensionLoader(Protocol.class);

    //URL protocol_url = URL.valueOf("dubbo://127.0.0.1:9300/" + DemoService.class.getName());//静态代理
    URL protocol_url = URL.valueOf("http://127.0.0.1:9300/" + DemoService.class.getName() + "?proxy=jdk"); //动态代理


    @Test
    public void serverRpc() throws IOException {
        DemoService service = new DemoServiceImpl();
        //生成代理工厂
        //  --- 由URL确定到底是动态代理工厂（JdkProxyFactory）还是静态代理工厂（JavassistProxyFactory）
        //  --- 默认情况下为静态代理工厂
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();
        //由代理工厂生成Invoker对象
        //  --- Invoker对象里包含了具体的服务（service，在dubbo源码里服务端称为ref）、服务的接口和要暴露服务的url
        //  --- 但值得注意的是这里返回的Invoker对象，是用Invoker接口进行接收的，也就是说通过下面的serviceInvoker，只能获取到service的接口
        Invoker<DemoService> serviceInvoker = proxy.getInvoker(service, DemoService.class, protocol_url);

        //获取具体的协议
        //  ---由URL确定到底使用哪个协议，默认情况下使用dubbo协议
        Protocol protocol = protocolLoader.getAdaptiveExtension();

        //利用protocol暴露服务
        //  --- 暴露服务的具体流程为
        //      --- (1) 拿到服务的【动态代理类或者静态代理类】、【接口】、以及【url】
        //      --- (2) 拿着（1）中获取到的三个内容进行真正的服务暴露
        Exporter<DemoService> exporter = protocol.export(serviceInvoker);
        System.out.println("server 启动协议：" + protocol_url.getProtocol());
        // 保证服务一直开着
        System.in.read();
        exporter.unexport();
    }


    @Test
    public void clientRpc() {
        //获取具体的协议
        //  ---由URL确定到底使用哪个协议，默认情况下使用dubbo协议
        Protocol protocol = protocolLoader.getAdaptiveExtension();

        //由代理工厂生成Invoker对象
        //  --- Invoker对象里包含了服务的接口和要暴露服务的url
        //  --- 但值得注意的是这里返回的Invoker对象，是用Invoker接口进行接收的，也就是说通过下面的serviceInvoker，只能获取到service的接口
        Invoker<DemoService> referInvoker = protocol.refer(DemoService.class, protocol_url);

        //生成代理工厂
        //  --- 由URL确定到底是动态代理工厂（JdkProxyFactory）还是静态代理工厂（JavassistProxyFactory）
        //  --- 默认情况下为静态代理工厂
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();

        //生成DemoService的代理类
        DemoService service = proxy.getProxy(referInvoker);

        Map<String, String> info = new HashMap();
        info.put("target", "orderService");
        info.put("methodName", "getOrderInfo");
        info.put("arg", "1");
        Map<String, String> result = service.getHelloInfo(info);
        System.out.println(result);
    }
}
