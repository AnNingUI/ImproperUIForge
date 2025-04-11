package org.anningui.iui_forge.render;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.event.IModBusEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.function.Supplier;

public class ElementTagRegistrationEvent extends Event implements IModBusEvent {
    private final Map<String, Supplier<? extends Element>>  tagRegistrationMap;
    private static ElementTagRegistrationEvent INSTANCE;
    @ApiStatus.Internal
    private ElementTagRegistrationEvent(Map<String, Supplier<? extends Element>> tagRegistrationMap) {
        this.tagRegistrationMap = tagRegistrationMap;
    }

    public static class Target {
        public static void init(Map<String, Supplier<? extends Element>> tagRegistrationMap) {
            if (INSTANCE == null) {
                INSTANCE = new ElementTagRegistrationEvent(tagRegistrationMap);
            }
            ModLoader.get().postEvent(INSTANCE);
        }
    }

    public void register(String tag, Supplier<? extends Element> supplier) {
        if (tagRegistrationMap.containsKey(tag)) {
            throw new IllegalArgumentException("Tag " + tag + " is already registered!");
        }
        tagRegistrationMap.put(tag, supplier);
    }
}
