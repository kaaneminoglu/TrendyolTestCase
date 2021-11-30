package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private  static final Properties properties = new Properties();

    static {
        try {
            FileInputStream in = new FileInputStream("config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the value of key according to the key provided
     * @param key the key you want to get the value from
     * @return the value of the key
     */
    public static String read(String key){
        return properties.getProperty(key) ;
    }
}
