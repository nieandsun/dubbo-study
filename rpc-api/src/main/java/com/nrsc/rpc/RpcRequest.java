package com.nrsc.rpc;

import java.io.Serializable;

/***
 * 封装service的 名称，方法的名称，方法的参数列表，方法参数列表的类型
 * 对应于dubbo框架中的
 *  @see org.apache.dubbo.rpc.RpcInvocation
 */
public class RpcRequest implements Serializable {

    private String serviceName;
    private String methodName;
    private Object[] arguments;
    private Class<?>[] parameterTypes;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
