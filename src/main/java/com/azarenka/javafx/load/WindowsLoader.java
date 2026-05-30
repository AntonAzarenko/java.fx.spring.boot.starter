package com.azarenka.javafx.load;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WindowsLoader {

    private final List<IFxmlWindow> windows;

    public WindowsLoader(List<IFxmlWindow> windows) {
        this.windows = windows;
    }

    @EventListener(ContextRefreshedEvent.class) // вызывается, когда контекст уже поднят
    public void initializeWindows() {
        for (IFxmlWindow w : windows) {
            w.load();
        }
    }
}
