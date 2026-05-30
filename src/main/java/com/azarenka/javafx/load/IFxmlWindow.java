package com.azarenka.javafx.load;

import javafx.scene.Scene;

/**
 * Represents interface for any windows loaded from FXML file.
 */
public interface IFxmlWindow {

    /**
     * Returns Scene of concrete window.
     *
     * @return instance of {@link Scene}
     */
    Scene getScene();

    /**
     * Loads window.
     */
    void load();
}
