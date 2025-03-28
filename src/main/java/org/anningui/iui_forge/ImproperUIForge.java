package org.anningui.iui_forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.anningui.iui_forge.client.ImproperUIReload;
import org.anningui.iui_forge.kubejs.ImproperUIKJS;
import org.anningui.iui_forge.render.elements.svg.SvgRegistry;

import java.net.MalformedURLException;

@Mod(ImproperUIForge.MOD_ID)
public class ImproperUIForge {
    public static final String MOD_ID = "iui_forge";

    public static boolean kjsIsOk = ModList.get().isLoaded("kubejs");

    public static boolean rhinoIsOk = ModList.get().isLoaded("rhino");

    public static final ImproperUI UI = new ImproperUI();

    public static Object KJS_UI = null;

    public ImproperUIForge() {
        if (kjsIsOk) {
            KJS_UI = new ImproperUIKJS();
        }
        UI.onInitialize();
        MinecraftForge.EVENT_BUS.register(UI);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ImproperUIReload::onReload);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onSetup);
    }

    public void onSetup(final FMLCommonSetupEvent event) {
        ImproperUIReload.init();
    }

}

