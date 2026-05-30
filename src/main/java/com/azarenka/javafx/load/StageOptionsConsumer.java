package com.azarenka.javafx.load;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/09/2023
 *
 * @author Anton Azarenka
 */
public class StageOptionsConsumer {

    private Stage stage;
    private PropertiesApp propertiesApp;
    private boolean isNotApplied = true;

    /**
     * Constructor.
     *
     * @param stage main stage.
     */
    public StageOptionsConsumer(Stage stage) {
        this.stage = stage;
    }

    public void applyProperties(PropertiesLoader propertiesLoader) {
        if (isNotApplied) {
            this.propertiesApp = propertiesLoader.getPropertiesApp();
            new ApplicationPropertiesApplier().apply();
            isNotApplied = false;
        }
    }

    /**
     *
     */
    private class ApplicationPropertiesApplier {

        private final String RESIZABLE = "resizable";
        private final String STAGE_STYLE = "stage_style";
        private final List<String> probProp = List.of(RESIZABLE, STAGE_STYLE);
        private final Map<String, Consumer<String>> consumerMap =
            Map.of(
                "resizable", this::setResizable,
                "stage_style", this::setUndecorated);

        public void apply() {
            probProp.forEach(key -> {
                String prop = propertiesApp.get(key);
                if (Objects.nonNull(prop)) {
                    Consumer<String> consumer = consumerMap.get(key);
                    if (Objects.nonNull(consumer)) {
                        consumer.accept(prop);
                    }
                }
            });
        }

        public void setResizable(String prop) {
            stage.setResizable(Boolean.parseBoolean(prop));
        }

        public void setUndecorated(String prop) {
            stage.initStyle(StageStyle.valueOf(prop.toUpperCase()));
        }
    }
}
