package com.azarenka.javafx.load;

import java.util.Properties;

public class PropertiesApp {

    private final Properties properties;

    PropertiesApp(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String alias) {
        return properties.getProperty(alias);
    }

    public String get(String alias) {
        return (String) properties.getOrDefault(alias, true);
    }
}
