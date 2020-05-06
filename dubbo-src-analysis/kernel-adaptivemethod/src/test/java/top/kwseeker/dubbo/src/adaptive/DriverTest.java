package top.kwseeker.dubbo.src.adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.sql.SQLException;

public class DriverTest {

    @Test
    public void test1() throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = loader.getAdaptiveExtension();
        URL url = URL.valueOf("xxx://localhost/xxx");
        System.out.println(driver.connect("", null, url));
    }

    @Test
    public void test2() throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = loader.getAdaptiveExtension();
        URL url = URL.valueOf("xxx://localhost/xxx?driver=mysql");
        System.out.println(driver.connect("", null, url));
    }
}