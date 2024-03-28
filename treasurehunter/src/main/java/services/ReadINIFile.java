package services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadINIFile {
    private Properties properties;

    public ReadINIFile() {
        loadFile();
    }

    void loadFile() {
        properties = new Properties();
        try {
            InputStream inputStream = getClass().getResourceAsStream("resources/config.ini");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Unable tto load data");
        }
    }

    public String getData(String key) {
        return properties.getProperty(key);
    }

}
