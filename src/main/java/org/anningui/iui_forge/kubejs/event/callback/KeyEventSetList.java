package org.anningui.iui_forge.kubejs.event.callback;

import net.minecraft.resources.ResourceLocation;
import org.anningui.iui_forge.script.events.KeyEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public class KeyEventSetList {
    private static final Map<ResourceLocation, KeyEventSetList> keyEventSetLists = new HashMap<>();

    public ResourceLocation id;
    private final Map<String, EventCallback.KeyEventCallback> keyEvents = new HashMap<>();

    private KeyEventSetList(ResourceLocation id) {
        this.id = id;
        keyEventSetLists.put(id, this);
    }

    public static KeyEventSetList create(ResourceLocation id) {
        return Optional.ofNullable(keyEventSetLists.get(id)).orElseGet(() -> new KeyEventSetList(id));
    }

    public void addKeyEvent(String methodName, Consumer<KeyEvent> event) {
        this.keyEvents.put(methodName, new EventCallback.KeyEventCallback(methodName, event));
    }


    public static Map<String, EventCallback.KeyEventCallback> getEvents(ResourceLocation id) {
        var $event = Optional.ofNullable(keyEventSetLists.get(id));
        if ($event.isEmpty()) {
            return new HashMap<>() {
                {
                    put("", new EventCallback.KeyEventCallback("", (event) -> {
                    }));
                }
            };
        } else {
            return $event.get().keyEvents;
        }
    }

    public static String getMethodName(ResourceLocation id, String methodName) {
        String name =  getEvents(id).getOrDefault(methodName, new EventCallback.KeyEventCallback(methodName, (event) -> {})).methodName();
        return isNull(name) || name.isEmpty() ? methodName : name;
    }

    public static Consumer<KeyEvent> getEvent(ResourceLocation id, String methodName) {
        Consumer<KeyEvent> eventConsumer = getEvents(id).getOrDefault(methodName, new EventCallback.KeyEventCallback(methodName, (event) -> {})).event();
        return isNull(eventConsumer) ? (event) -> {} : eventConsumer;
    }

    public static boolean isEquals(ResourceLocation id, String methodName) {
        return Optional.ofNullable(getMethodName(id, methodName)).orElse("").equals(methodName);
    }
}
