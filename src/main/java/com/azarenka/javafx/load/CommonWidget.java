package com.azarenka.javafx.load;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public abstract class CommonWidget extends AbstractWindow{

    private PropertiesLoader propertiesLoader;
    protected ApplicationContext applicationContext;

    public CommonWidget(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public abstract Scene getScene();
    public abstract void load();

    public Scene loadBean(FxmlFileLoader fxmlFileLoader){
        FXMLLoader fxmlLoader = fxmlFileLoader.loadFxmlFile();
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent mainWidgetParent = getParent(fxmlLoader);
        return new Scene(mainWidgetParent, getWidth(), getHeight());
    }

    protected Parent getParent(FXMLLoader loader) {
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }
}
