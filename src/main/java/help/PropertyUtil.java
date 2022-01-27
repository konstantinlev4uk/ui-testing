package help;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public Properties getProperties(String fileName) {
        Properties propertiesDriver;
        InputStream inputStreamDriver = getClass().getClassLoader().getResourceAsStream(fileName);
        propertiesDriver = new Properties();
        try {
            propertiesDriver.load(inputStreamDriver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesDriver;
    }
}
