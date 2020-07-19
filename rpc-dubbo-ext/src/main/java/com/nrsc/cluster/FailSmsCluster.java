package com.nrsc.cluster;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.support.FailfastClusterInvoker;

/***
 * 当然如果想新扩展一个Cluster组件，肯定还要配套弄一个XXXClusterInvoker，这里就直接使用FailfastClusterInvoker代替了
 */
public class FailSmsCluster implements Cluster {

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        sendSms();
        return new FailfastClusterInvoker<>(directory);
    }

    private void sendSms() {
        System.out.println("send sms notify！");
    }
}

