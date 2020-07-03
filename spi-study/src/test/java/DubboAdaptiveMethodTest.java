import com.nrsc.service.InfoService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * 注解在类上时，直接选此类为适配类（一个接口只允许一个类标记）
 * 注解在方法上时，只为此方法生成代理逻辑（方法必须有参数为url或者参数有返回url的方法）
 */
public class DubboAdaptiveMethodTest {

    /**
     * 各个实现类上都没有@Adaptive
     * SPI上有注解@SPI("b"),
     * url无参数
     * 则以@SPI("b")为准，选择InfoServiceBImpl 实现中的方法进行静态代理
     */
    @Test
    public void test1() {
        ExtensionLoader<InfoService> loader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test");
        System.out.println(adaptiveExtension.passInfo("yoyo", url));
    }


    /**
     * 各个实现类上都没有@Adaptive
     * SPI上有注解@SPI("b")
     * URL中有具体的值info.service=a,
     * 则以URL为准，选择InfoServiceAImpl 实现中的方法进行静态代理
     */
    @Test
    public void test2() {
        ExtensionLoader<InfoService> loader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?info.service=a");
        System.out.println(adaptiveExtension.passInfo("james", url));
    }

    /**
     * 各个实现类上面没有@Adaptive
     * 接口方法中加上的注解为@Adaptive({"NRSC"}),
     * URL中有具体的值NRSC=c,
     * 则以URL中的NRSC参数为准，选择InfoServiceCImpl 实现中的方法进行静态代理
     */
    @Test
    public void test3() {
        ExtensionLoader<InfoService> loader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?info.service=a&NRSC=c");
        System.out.println(adaptiveExtension.passInfo("james", url));
    }


    /**
     * InfoServiceCImpl实现类上有@Adaptive,URL随意传值
     * 直接会拿到InfoServiceCImpl的实现类 ---》 并未做静态代理
     */
    @Test
    public void test4() {
        ExtensionLoader<InfoService> loader = ExtensionLoader.getExtensionLoader(InfoService.class);
        InfoService adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?info.service=a");
        System.out.println(adaptiveExtension.passInfo("james", url));
    }


}