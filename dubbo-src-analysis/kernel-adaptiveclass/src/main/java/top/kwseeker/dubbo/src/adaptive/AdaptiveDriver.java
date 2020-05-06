package top.kwseeker.dubbo.src.adaptive;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Adaptive
public class AdaptiveDriver implements Driver {

    private String driverName;

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver;
        if(StringUtils.isEmpty(driverName)) {
            driver = loader.getDefaultExtension();
        } else {
            driver = loader.getExtension(driverName);
        }
        return driver.connect("", null);
    }
}
