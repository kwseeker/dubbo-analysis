package top.kwseeker.dubbo.src.spi;

import org.apache.dubbo.common.extension.SPI;

import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@SPI("Oracle")              //指定默认的Driver实现类
public interface Driver {

    Connection connect(String url, java.util.Properties info)
            throws SQLException;

    boolean acceptsURL(String url) throws SQLException;

    DriverPropertyInfo[] getPropertyInfo(String url, java.util.Properties info)
            throws SQLException;

    int getMajorVersion();

    int getMinorVersion();

    boolean jdbcCompliant();

    public Logger getParentLogger() throws SQLFeatureNotSupportedException;
}
