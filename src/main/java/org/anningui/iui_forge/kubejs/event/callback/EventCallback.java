package org.anningui.iui_forge.kubejs.event.callback;

import org.anningui.iui_forge.script.events.KeyEvent;
import org.anningui.iui_forge.script.events.MouseEvent;

import java.util.function.Consumer;

public class EventCallback {
    public record KeyEventCallback(String methodName, Consumer<KeyEvent> event) {}
    public record MouseEventCallback(String methodName, Consumer<MouseEvent> event) {}
}
