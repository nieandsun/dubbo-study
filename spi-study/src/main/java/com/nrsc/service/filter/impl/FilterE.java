package com.nrsc.service.filter.impl;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * 使用方传递了group = nrsc或yoyo，并且url中包含MMMM参数，该Filter才能被激活
 */
@Activate(group = {"nrsc", "yoyo"}, order = 1, value = "MMMM")
public class FilterE implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("你好，调通了Filer E实现！");
        return null;
    }
}
