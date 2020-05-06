package top.kwseeker.dubbo.src.adaptive;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDriver implements Driver {

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        System.out.println("Oracle Connection ...");
        return null;
    }

}
