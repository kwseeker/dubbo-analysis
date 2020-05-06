package top.kwseeker.dubbo.src.adaptive;

import org.apache.dubbo.common.URL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDriver implements Driver {

    @Override
    public Connection connect(String urlStr, Properties info, URL url) throws SQLException {
        System.out.println("Oracle Connection ...");
        return null;
    }

}
