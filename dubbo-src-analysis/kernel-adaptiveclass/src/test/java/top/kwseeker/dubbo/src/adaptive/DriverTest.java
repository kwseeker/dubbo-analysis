package top.kwseeker.dubbo.src.adaptive;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Set;

public class DriverTest {

    @Test
    public void test1() throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver adaptiveDriver = loader.getAdaptiveExtension();          //获取@SPI指定的Driver实现
        System.out.println(adaptiveDriver.connect("", null));
    }

    @Test
    public void test２() throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = loader.getAdaptiveExtension();
        ((AdaptiveDriver)driver).setDriverName("mysql");
        System.out.println(driver.connect("", null));
    }

    @Test
    public void test3() throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
        Set<String> drivers = loader.getSupportedExtensions();  //获取所有拓展类前缀
        System.out.println(drivers);
    }
}