package com.nrsc.service;

import org.apache.dubbo.common.extension.SPI;

/***
 *Dubbo SPI的作法：
 *		1.Dubbo 增强原生的SPI机制来更好的满足拓展要求，其以键值对的方式对接口的实现进行配置管理。
 *		2.Dubbo引入三个注解： SPI、Adaptive和Activate。
 *
 *		只有标注了SPI注解的接口，才是Dubbo的菜
 */
@SPI("b") //指定缺省的实现类
public interface InfoService {
    Object sayHello(String name);
}
