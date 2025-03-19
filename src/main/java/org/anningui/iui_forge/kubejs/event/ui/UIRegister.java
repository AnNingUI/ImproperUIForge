package org.anningui.iui_forge.kubejs.event.ui;

import dev.latvian.mods.kubejs.client.ClientEventJS;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UIRegister extends ClientEventJS {
    public static Map<String, Set<String>> uiLocations = new HashMap<>();
    public UIRegister() {}


    public void register(ResourceLocation location) {
        String namespace = location.getNamespace();
        String id = location.getPath();
        Set<String> list = uiLocations.getOrDefault(namespace, new HashSet<>());
        list.add(id);
        uiLocations.put(namespace, list);
    }
}
