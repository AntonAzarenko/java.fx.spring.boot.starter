package com.azarenka.javafx;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageEvent extends ApplicationEvent {

    public StageEvent(Stage primaryStage) {
        super(primaryStage);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
