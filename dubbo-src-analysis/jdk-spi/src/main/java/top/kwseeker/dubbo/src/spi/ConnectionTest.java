package top.kwseeker.dubbo.src.spi;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ConnectionTest {

    public static void main(String[] args) throws SQLException {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> driverIterator = serviceLoader.iterator();
        while(driverIterator.hasNext()) {
            //根据业务选择使用哪个接口实现
            //然后调用
            Driver driver = driverIterator.next();
            driver.connect("", null);
        }
    }
}
