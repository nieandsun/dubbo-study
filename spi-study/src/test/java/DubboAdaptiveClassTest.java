import com.nrsc.service.InfoService;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.Set;

public class DubboAdaptiveClassTest {


    @Test
    public void test1() {
        //获取InfoService的ExtensionLoader
        ExtensionLoader<InfoService> loader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService adaptiveExtension = loader.getAdaptiveExtension(); //获取到@Adaptive注解标注的实现类
        System.out.println(adaptiveExtension.sayHello("yoyo"));

        System.out.println("===========================================================");

        InfoService defaultExtension = loader.getDefaultExtension();//获取接口上@SPI("别名")中别名指定的实现类
        System.out.println(defaultExtension.sayHello("nrsc"));

        System.out.println("===========================================================");
        //获取所有未标注@Adapter注解的实现类的别名，并通过别名获取到实现类
        Set<String> supportedExtensions = loader.getSupportedExtensions();
        for (String name : supportedExtensions) {
            System.out.println(loader.getExtension(name).sayHello("MM"));
        }
    }

}
