package org.anningui.iui_forge.kubejs.event.callback;

import net.minecraft.resources.ResourceLocation;
import org.anningui.iui_forge.script.events.KeyEvent;
import org.anningui.iui_forge.script.events.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public class MouseEventSetList {
    private static final Map<ResourceLocation, MouseEventSetList> mouseEventSetLists = new HashMap<>();

    public ResourceLocation id;
    private final Map<String, EventCallback.MouseEventCallback> mouseEvents = new HashMap<>();

    private MouseEventSetList(ResourceLocation id) {
        this.id = id;
        mouseEventSetLists.put(id, this);
    }

    public static MouseEventSetList create(ResourceLocation id) {
        return Optional.ofNullable(mouseEventSetLists.get(id)).orElseGet(() -> new MouseEventSetList(id));
    }

    public void addMouseEvent(String methodName, Consumer<MouseEvent> event) {
        this.mouseEvents.put(methodName, new EventCallback.MouseEventCallback(methodName, event));
    }

    public static Map<String, EventCallback.MouseEventCallback> getEvents(ResourceLocation id) {
        var $event = Optional.ofNullable(mouseEventSetLists.get(id));
        if ($event.isEmpty()) {
            return new HashMap<>() {
                {
                    put("", new EventCallback.MouseEventCallback("", (event) -> {}));
                }
            };
        } else {
            return $event.get().mouseEvents;
        }
    }

    public static String getMethodName(ResourceLocation id, String methodName) {
        String name = getEvents(id).getOrDefault(methodName, new EventCallback.MouseEventCallback(methodName, (event) -> {})).methodName();
        return isNull(name) || name.isEmpty() ? methodName : name;
    }

    public static Consumer<MouseEvent> getEvent(ResourceLocation id, String methodName) {
        Consumer<MouseEvent> eventConsumer = getEvents(id).getOrDefault(methodName, new EventCallback.MouseEventCallback(methodName, (event) -> {})).event();
        return isNull(eventConsumer) ? (event) -> {} : eventConsumer;
    }

    public static boolean isEquals(ResourceLocation id, String methodName) {
        return Optional.ofNullable(getMethodName(id, methodName)).orElse("").equals(methodName);
    }
}
