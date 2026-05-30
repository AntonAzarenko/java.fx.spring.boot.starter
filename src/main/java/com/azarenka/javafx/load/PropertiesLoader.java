package com.azarenka.javafx.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Component
public class PropertiesLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);
    private final Properties properties;
    private PropertiesApp propertiesApp;
    private Resource resource;

    /**
     * Constructor.
     */
    public PropertiesLoader() {
        properties = new Properties();
        propertiesApp = new PropertiesApp(properties);
    }

    public void load() {
        try {
            properties.load(resource.getInputStream());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            if (Objects.isNull(resource)) {
                LOGGER.error("Resource shouldn't be NULL");
            }
        }
    }

    public PropertiesApp getPropertiesApp() {
        return propertiesApp;
    }

    public boolean hasResources() {
        return Objects.nonNull(resource);
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
