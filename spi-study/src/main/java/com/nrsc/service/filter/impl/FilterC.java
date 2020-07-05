package com.nrsc.service.filter.impl;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * 使用方传递了group = yoyo 则该Filter被激活 ，order表示激活顺序，激活顺序为 0->1->2...
 */
@Activate(group = "yoyo", order = 3)
public class FilterC implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("你好，调通了Filer C实现！");
        return null;
    }
}
