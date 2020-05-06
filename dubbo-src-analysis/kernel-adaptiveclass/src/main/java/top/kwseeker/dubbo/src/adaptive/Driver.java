package top.kwseeker.dubbo.src.adaptive;

import org.apache.dubbo.common.extension.SPI;

import java.sql.Connection;
import java.sql.SQLException;

@SPI("Oracle")              //指定默认的Driver实现类
public interface Driver {

    Connection connect(String url, java.util.Properties info)
            throws SQLException;
}
