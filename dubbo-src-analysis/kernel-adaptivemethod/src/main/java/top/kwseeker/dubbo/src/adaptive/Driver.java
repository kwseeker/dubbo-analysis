package top.kwseeker.dubbo.src.adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

import java.sql.Connection;
import java.sql.SQLException;

@SPI("Oracle")              //指定默认的Driver实现类
public interface Driver {

    @Adaptive
    Connection connect(String urlStr, java.util.Properties info, URL url)
            throws SQLException;
}
