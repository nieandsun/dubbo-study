import com.nrsc.service.InfoService;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class DubboSpiTest {

    /**
     * dubbo spi类加载验证
     * extensionLoader.getExtension("a") ---------- 取到对应的扩展类
     * extensionLoader.getDefaultExtension() ------ 取得SPI（"b"）指定的实现类（默认实现类）
     */
    @Test
    public void dubboSPI() {
        //获取InfoService的 Loader 实例
        ExtensionLoader<InfoService> extensionLoader = ExtensionLoader.getExtensionLoader(InfoService.class);
        //取得a拓展类
        InfoService infoServiceA = extensionLoader.getExtension("a");
        infoServiceA.sayHello("yoyo");
        //取得b拓展类
        InfoService infoServiceB = extensionLoader.getDefaultExtension();
        infoServiceB.sayHello("nrsc");
    }
}
