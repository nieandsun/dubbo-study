import com.nrsc.service.JdbcService;
import com.nrsc.service.impl.JdbcServiceAImpl;
import org.junit.Test;

import java.util.ServiceLoader;

public class SpiTest {

    /***
     * 习以为常的开发方式
     */
    @Test
    public void demo1() {
        JdbcService jdbcService = new JdbcServiceAImpl();
        int i = jdbcService.insert("james");
        if (i == 1) {
            System.out.println("插入成功");
        }
    }


    /**
     * java spi机制验证
     */
    @Test
    public void javaSPI() {
        //服务加载器，加载实现类
        ServiceLoader<JdbcService> serviceLoader = ServiceLoader.load(JdbcService.class);

        //serviceLoader是实现了Iterable的迭代器，直接遍历实现类
        for (JdbcService service : serviceLoader) {
            int james = service.insert("james");//依次调用实现类
            System.out.println(james);
        }
    }
}
