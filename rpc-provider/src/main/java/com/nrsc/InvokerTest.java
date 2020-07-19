package com.nrsc;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.*;
import com.nrsc.service.DemoService;
import com.nrsc.service.InvokerDemoService;
import com.nrsc.service.impl.InvokerDemoServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class InvokerTest {

    Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    //代理
    ProxyFactory proxy = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

    //注册中心服务--zk
    final URL registryUrl = URL.valueOf("registry://127.0.0.1:2181/com.alibaba.dubbo.registry.RegistryService?registry=zookeeper");

    //支持的协议：dubbo,http,hessian,rmi
    URL serviceUrl = URL.valueOf("rmi://127.0.0.1:9001/com.nrsc.service.InvokerDemoService" + "?proxy=jdk"); //动态代理

    @Test
    public void serverRpc() throws IOException {
        InvokerDemoService service = new InvokerDemoServiceImpl("yoyo");
        serviceUrl = serviceUrl.setPort(9001);
        URL newRegistryUrl = registryUrl.addParameter(Constants.EXPORT_KEY, serviceUrl.toFullString());
        //暴露服务
        Invoker<InvokerDemoService> serviceInvoker = proxy.getInvoker(service, InvokerDemoService.class, newRegistryUrl);
        Exporter<InvokerDemoService> exporter = protocol.export(serviceInvoker);
        System.out.println("server 启动协议：" + serviceUrl.getProtocol());
        // 保证服务一直开着
        System.in.read();
        exporter.unexport();
    }

    @Test
    public void clientRpc() {
        Invoker<InvokerDemoService> referInvoker = protocol.refer(InvokerDemoService.class, registryUrl);
        //获取【可以调用远程服务的对象】的代理对象，当调用此对象的方法时会走代理逻辑，调用referInvoker的invoke方法
        InvokerDemoService service = proxy.getProxy(referInvoker);
        String result = service.sayHello(registryUrl.getProtocol() + "调用");
        System.out.println(result);
    }

    @Test
    public void clientInvoker() {
        Invoker<InvokerDemoService> referInvoker = protocol.refer(InvokerDemoService.class, registryUrl);
        //直接构造RpcInvocation对象
        Invocation invocation = new RpcInvocation(
                "sayHello", new Class<?>[]{String.class}, new Object[]{"invoke直接调用"}, null);
        //拿着构造的RpcInvocation对象直接调用referInvoker的invoke方法
        Result result2 = referInvoker.invoke(invocation);
        System.out.println(result2.getValue());
    }


    private Invocation invocation = new RpcInvocation(
            "sayHello", new Class<?>[]{String.class}, new Object[]{"invoke调用"}, null);

    @Test
    public void clientInvokerWrapper() throws IOException {
        //获取包装了【可以调用远程服务的对象】的Invoker
        Invoker<InvokerDemoService> referInvoker = protocol.refer(InvokerDemoService.class, registryUrl);
        //对包装了【可以调用远程服务的对象】的Invoker再嵌套一个Invoker
        Invoker<InvokerDemoService> wrapperInvoker = new Invoker<InvokerDemoService>() {
            private Invoker<InvokerDemoService> invoker = referInvoker;

            @Override
            public Class<InvokerDemoService> getInterface() {
                return invoker.getInterface();
            }

            @Override
            public Result invoke(Invocation invocation) throws RpcException {
                System.out.println("invoker嵌套调用");
                return invoker.invoke(invocation);
            }

            @Override
            public URL getUrl() {
                return invoker.getUrl();
            }

            @Override
            public boolean isAvailable() {
                return false;
            }

            @Override
            public void destroy() {
            }
        };
        //拿着invocation调用最外层Invoker的invoke方法，由于最外层的Invoker里有包装了【可以调用远程服务的对象】的Invoker，
        //所以可以现在最外层的Invoker的invoke方法里先做些事情再调用包装了【可以调用远程服务的对象】的Invoker的invoke方法
        Result result3 = wrapperInvoker.invoke(invocation);
        System.out.println(result3.getValue());
    }
}
