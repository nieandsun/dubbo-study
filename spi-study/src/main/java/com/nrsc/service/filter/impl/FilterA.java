package com.nrsc.service.filter.impl;


import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;


/***
 * @Activate表示为一个SPI扩展点
 *使用方如果传递了
 * group = CommonConstants.PROVIDER(其实就是字符串”provider“,在dubbo里指提供者)
 *     或 CommonConstants.CONSUMER(其实就是字符串”consumer“,在dubbo里指消费者)
 *     或 字符传”yoyo“
 *     则该Filter被激活
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER, "yoyo"})
public class FilterA implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("你好，调通了Filer  A实现！");
        return null;
    }
}
