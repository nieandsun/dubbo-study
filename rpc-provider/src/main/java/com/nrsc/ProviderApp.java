package com.nrsc;

import com.nrsc.service.InfoService;
import com.nrsc.service.impl.InfoServiceImpl;
import com.nrsc.utils.RpcProxyServer;

public class ProviderApp {
    public static void main(String[] args) {
        /**
         * 对象实例的发布,这里只有一个接口需要发布
         * 当有多个接口实例需要发布的时候，可以先把所有的实例都注册到容器中
         * 然后遍历容器，依次发布
         * 端口号8888可以看作是rpc框架通讯的默认端口号
         */
        InfoService sayHello = new InfoServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(sayHello, 8888);
    }


}
