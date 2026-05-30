package com.azarenka.javafx;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.PropertiesLoader;
import com.azarenka.javafx.load.StageOptionsConsumer;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SceneChanger extends StageInitializer {

    @Autowired
    private PropertiesLoader propertiesLoader;

    private final List<Stage> stages = new ArrayList<>();

    public void setNewScene(CommonWidget widget) {
        Scene scene = widget.getScene();
        Stage stage = getStage();
        stage.setScene(scene);
        stage.show();
    }

    public Stage showModalWindow(CommonWidget widget) {
        Stage dialog = new Stage();
        if(propertiesLoader.hasResources()) {
            StageOptionsConsumer stageOptionsConsumer = new StageOptionsConsumer(dialog);
            stageOptionsConsumer.applyProperties(propertiesLoader);
        }
        stages.add(dialog);
        dialog.getIcons().addAll(getStage().getIcons());
        dialog.centerOnScreen();
        dialog.setTitle(widget.getTitle());
        dialog.initOwner(getStage());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(widget.getScene());
        dialog.show();
        return dialog;
    }

    public void closeWindow(CommonWidget widget) {
        Scene scene = widget.getScene();
        Optional<Stage> first = stages.stream().filter(s -> Objects.equals(s.getScene(), scene)).findFirst();
        first.ifPresent(Stage::close);
    }
}
