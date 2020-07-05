import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;
import org.junit.Test;

import java.util.List;

/***
 *某些情况下，同一个接口的多个实现需要同时发挥作用，比如filter链。此时需要按条件选择一批实现类来工作
 *			Activate：可以被框架中自动激活加载扩展
 *			用户通过group和value配置激活条件
 *			group 分组(筛选条件)
 *			value url中包含的key名(筛选条件)
 *			order 排序
 *
 *下面以filter过滤器为例讲解
 *			1.如果需要在服务暴露时装载，那么group="provider"
 *			2.如果需要在服务引用（消费）的时候装载，那么group="consumer"
 *			3.如果想被暴露和引用时同时被装载，那么group={"consumer", "provider"}
 *			4.如果需要url中有某个特定的值才被加载，那么value={"test5", "test"}
 */
public class DubboActivateTest {

    /**
     * 调用分组为yoyo过滤器
     */
    @Test
    public void testActivate1() {
        ExtensionLoader<Filter> extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);

        URL url = URL.valueOf("test://localhost/test");
        //第一个参数为url,第二个参数稍后讲，第三个参数为group
        List<Filter> list = extensionLoader.getActivateExtension(url, "", "yoyo");//group
        for (Filter filter : list) {
            filter.invoke(null, null);
        }
    }

    /**
     * 分组为nrsc
     * url中指定参数MMMM
     */
    @Test
    public void testActivate2() {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);

        URL url = URL.valueOf("test://localhost/test");
        url = url.addParameter("MMMM", "66666");
        List<Filter> list = extensionLoader.getActivateExtension(url, "", "nrsc");
        for (Filter filter : list) {
            filter.invoke(null, null);
        }
    }

    /**
     * 分组为nrsc
     * url中有参数MMMM
     * url中指定要使用a,去除c实现
     */
    @Test
    public void testActivate3() {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);

        URL url = URL.valueOf("test://localhost/test");
        url = url.addParameter("MMMM", "7777");
        //url = url.addParameter("myfilter", "+b,-a,-d"); 和下面的含义一样
        url = url.addParameter("myfilter", "b,-a,-d");

        //中间的参数用来指定额外去除或增加哪个实现类
        List<Filter> list = extensionLoader.getActivateExtension(url, "myfilter", "yoyo");
        for (Filter filter : list) {
            filter.invoke(null, null);
        }
    }
}
