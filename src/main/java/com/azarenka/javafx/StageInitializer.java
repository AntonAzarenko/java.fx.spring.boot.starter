package com.azarenka.javafx;


import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.PropertiesLoader;
import com.azarenka.javafx.load.StageOptionsConsumer;
import com.azarenka.javafx.load.WindowsLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageEvent> {

    @Autowired
    private WindowsLoader windowsLoader;
    @Autowired
    private PropertiesLoader propertiesLoader;
    private StageOptionsConsumer stageOptionsConsumer;
    private CommonWidget commonWidget;
    private static Stage stage;
    private String icon;

    @Override
    public void onApplicationEvent(StageEvent stageEvent) {
        initStage(stageEvent);
        if (Objects.nonNull(commonWidget)) {
            Scene scene = commonWidget.getScene();
            setUpScene(scene);
        }
        stage.setIconified(false);
        stage.show();
    }

    private void initStage(StageEvent stageEvent) {
        if (Objects.isNull(stage)) {
            stage = stageEvent.getStage();
            if (propertiesLoader.hasResources()) {
                propertiesLoader.load();
                stageOptionsConsumer = new StageOptionsConsumer(stage);
                stageOptionsConsumer.applyProperties(propertiesLoader);
            }
        }
    }

    public void setCommonWidget(CommonWidget commonWidget) {
        this.commonWidget = commonWidget;
        //windowsLoader.initializeWindows();
    }

    public void setupPropertiesUrl(Resource url) {
        propertiesLoader.setResource(url);
    }

    protected Stage getStage() {
        return stage;
    }

    protected void setIcon(String icon) {
        this.icon = icon;
    }

    protected String getIcon() {
        return icon;
    }

    private void setUpScene(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
        if (Objects.nonNull(icon)) {
            stage.getIcons().add(new Image(icon));
        }
        stage.setTitle(commonWidget.getTitle());
    }
}