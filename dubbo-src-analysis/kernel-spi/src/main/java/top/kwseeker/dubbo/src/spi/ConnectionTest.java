package top.kwseeker.dubbo.src.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

import java.sql.SQLException;

public class ConnectionTest {

    public static void main(String[] args) throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);

        //使用key加载对应的实现类
        Driver mysqlDriver = loader.getExtension("mysql");
        mysqlDriver.connect("", null);
        Driver oracleDriver = loader.getExtension("oracle");
        oracleDriver.connect("", null);

        //使用＠SPI默认指定的实现类
        Driver driver = loader.getExtension("true");
        driver.connect("", null);
    }
}
