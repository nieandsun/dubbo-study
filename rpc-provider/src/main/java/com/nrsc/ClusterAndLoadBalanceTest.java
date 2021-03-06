package com.nrsc;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.nrsc.service.InvokerDemoService;
import com.nrsc.service.impl.InvokerDemoServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class ClusterAndLoadBalanceTest {

    ExtensionLoader<Protocol> protocolLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
    ExtensionLoader<ProxyFactory> proxyLoader = ExtensionLoader.getExtensionLoader(ProxyFactory.class);

    //注册中心服务--zk
    final URL registryUrl = URL.valueOf("registry://127.0.0.1:2181/com.alibaba.dubbo.registry.RegistryService?registry=zookeeper");

    //支持的协议：dubbo,http,hessian,rmi
    URL serviceUrl = URL.valueOf("dubbo://127.0.0.1:9001/com.nrsc.service.InvokerDemoService");

    @Test
    public void serverRpc() throws IOException {
        InvokerDemoService service = new InvokerDemoServiceImpl("yoyo");
        //生成代理工厂
        //  --- 由URL确定到底是动态代理工厂（JdkProxyFactory）还是静态代理工厂（JavassistProxyFactory）
        //  --- 默认情况下为静态代理工厂
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();

        //下面这两句话完全可以放在外面 ---> 如果写在外面，这里的代码就和上文讲到的RPC完整链条的代码一致了
        //这里为了测试消费端可以动态监测到服务端的发布/下线，所以写在了这里
        serviceUrl = serviceUrl.setPort(9001);
        //url中加入负载均衡和集群容错参数
        //serviceUrl = serviceUrl.addParameter("loadbalance", "consistenthash");
        //serviceUrl = serviceUrl.addParameter("cluster", "failfast");
        //启动自己扩展的loadbalance和cluster组件
        //serviceUrl = serviceUrl.addParameter("loadbalance", "first");
        //serviceUrl = serviceUrl.addParameter("cluster", "failsms");
        URL newRegistryUrl = registryUrl.addParameter(Constants.EXPORT_KEY, serviceUrl.toFullString());

        Invoker<InvokerDemoService> serviceInvoker = proxy.getInvoker(service, InvokerDemoService.class, newRegistryUrl);

        //获取具体的协议
        Protocol protocol = protocolLoader.getAdaptiveExtension();
        Exporter<InvokerDemoService> exporter = protocol.export(serviceInvoker);
        System.out.println("server 启动协议：" + serviceUrl.getProtocol());
        // 保证服务一直开着
        System.in.read();
        exporter.unexport();
    }

    /****
     * 除了接口外，其他和serverRpc1()一样，主要用来测试消费端可以动态监测到服务端的发布/下线
     * @throws IOException
     */
    @Test
    public void serverRpc2() throws IOException {
        InvokerDemoService service = new InvokerDemoServiceImpl("nrsc");
        //生成代理工厂
        //  --- 由URL确定到底是动态代理工厂（JdkProxyFactory）还是静态代理工厂（JavassistProxyFactory）
        //  --- 默认情况下为静态代理工厂
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();

        //下面这两句话完全可以放在外面 ---> 如果写在外面，这里的代码就和上文讲到的RPC完整链条的代码一致了
        //这里为了测试消费端可以动态监测到服务端的发布/下线，所以写在了这里
        serviceUrl = serviceUrl.setPort(9002);
        //url中加入负载均衡和集群容错参数
        //serviceUrl = serviceUrl.addParameter("loadbalance", "consistenthash");
        //serviceUrl = serviceUrl.addParameter("cluster", "failfast");
        //启动自己扩展的loadbalance和cluster组件
        //serviceUrl = serviceUrl.addParameter("loadbalance", "first");
        //serviceUrl = serviceUrl.addParameter("cluster", "failsms");
        URL newRegistryUrl = registryUrl.addParameter(Constants.EXPORT_KEY, serviceUrl.toFullString());

        Invoker<InvokerDemoService> serviceInvoker = proxy.getInvoker(service, InvokerDemoService.class, newRegistryUrl);

        //获取具体的协议
        Protocol protocol = protocolLoader.getAdaptiveExtension();
        Exporter<InvokerDemoService> exporter = protocol.export(serviceInvoker);
        System.out.println("server 启动协议：" + serviceUrl.getProtocol());
        // 保证服务一直开着
        System.in.read();
        exporter.unexport();
    }

    @Test
    public void clientRpc() throws IOException {
        Protocol protocol = protocolLoader.getAdaptiveExtension();
        //生成代理工厂
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();

        //由代理工厂生成Invoker对象
        Invoker<InvokerDemoService> referInvoker = protocol.refer(InvokerDemoService.class, registryUrl);

        //生成DemoService的代理类
        InvokerDemoService service = proxy.getProxy(referInvoker);

        for (int i = 0; i < 6; i++) {
            String result = service.sayHello(registryUrl.getProtocol() + "调用");
            System.out.println(result);
        }
        // 保证服务一直开着 ，测试消费端可以动态监测到服务端的发布/下线
        //System.in.read();
    }
}
