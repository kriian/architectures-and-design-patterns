package ru.hehnev.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile {

    private static final String filename = "./../../../server.properties";
    private static final Integer port;
    private static final String www;

    private ConfigFromFile() {
    }

    static {
        Properties properties = new Properties();
        try {
            properties.load(ConfigFromFile.class.getResourceAsStream(filename));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        port = Integer.parseInt(properties.getProperty("port"));
        www = properties.getProperty("www");
    }

    public static Integer getPort() {
        return port;
    }

    public static String getWWWFile() {
        return www;
    }
}
