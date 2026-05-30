package com.azarenka.javafx.load;

import javafx.fxml.FXMLLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class FxmlFileLoader {

    private final Resource resource;

    public FxmlFileLoader(Resource resource) {
        this.resource = resource;
    }

    public FXMLLoader loadFxmlFile() {
        try {
            return new FXMLLoader(resource.getURL());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
