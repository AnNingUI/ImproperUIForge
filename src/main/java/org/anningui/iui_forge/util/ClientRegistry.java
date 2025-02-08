package org.anningui.iui_forge.util;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;

public class ClientRegistry {
    public static KeyMapping register(KeyMapping keyMapping){
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings,keyMapping);
        return keyMapping;
    }
}