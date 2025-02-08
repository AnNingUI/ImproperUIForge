package org.anningui.iui_forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;

@Mod(ImproperUIForge.MOD_ID)
public class ImproperUIForge {
    public static final String MOD_ID = "iui_forge";

    public static final ImproperUI UI = new ImproperUI();

    public ImproperUIForge(IEventBus modEventBus, ModContainer modContainer) {
        UI.onInitialize();
        MinecraftForge.EVENT_BUS.register(UI);
    }
}

