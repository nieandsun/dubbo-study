package com.nrsc.service;

/***
 * SPI 全称为 Service Provider Interface，是一种服务发现机制，目标是为接口寻找实现类。
 * Java SPI 的作法：
 * 		1.在类路径下META-INF/service下创建文件，名称为接口的全限定名。
 * 		2.将接口实现类的全限定名配置在文件中
 * 		3.服务启动时，将由服务加载器读取配置文件，并加载实现类。
 */
public interface JdbcService {
    int insert(String name);
}
