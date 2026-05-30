package com.azarenka.javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Represents class to connect Java FX and Spring Boot
 */
public class ApplicationStarter {

    /**
     * Variable of spring boot start class.
     */
    public static Class clazz;

    public static void startApplication(String[] args, Class startTypeClass) {
        clazz = startTypeClass;
        Application.launch(FxApplication.class, args);
    }
}
