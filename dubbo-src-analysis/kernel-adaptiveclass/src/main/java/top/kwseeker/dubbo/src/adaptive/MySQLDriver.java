package top.kwseeker.dubbo.src.adaptive;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDriver implements Driver {

    public Connection connect(String url, Properties info) throws SQLException {
        System.out.println("MySQL Connection ...");
        return null;
    }
}
