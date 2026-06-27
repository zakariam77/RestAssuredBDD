package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
        static Properties properties;
   static{
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/global.properties");

            properties = new Properties();
            properties.load(fileInputStream);

        }catch (IOException e){
            throw new RuntimeException("Config could not load", e);
        }
    }
    public static String getProperty(String key){
       return properties.getProperty(key);
    }
}

