package org.anningui.iui_forge.client;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.anningui.iui_forge.ImproperUIAPI;
import org.anningui.iui_forge.ImproperUIForge;
import org.anningui.iui_forge.render.elements.svg.SvgRegistry;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = ImproperUIForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ImproperUIReload implements ResourceManagerReloadListener {
    private static final Minecraft client = Minecraft.getInstance();
    // 单例实例
    private static ImproperUIReload INSTANCE;
    @Override
    public void onResourceManagerReload(@NotNull ResourceManager pResourceManager) {
        SvgRegistry.reload();
    }
    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new ImproperUIReload();
        }
    }
    @SubscribeEvent
    public static void onClientReload(RegisterClientReloadListenersEvent event) {
        if (INSTANCE == null) {
            init();
        }
        event.registerReloadListener(INSTANCE);
    }

    @Mod.EventBusSubscriber(modid = ImproperUIForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DataReload implements ResourceManagerReloadListener {
        private static DataReload INSTANCE;
        @Override
        public void onResourceManagerReload(@NotNull ResourceManager pResourceManager) {
            if (client.level != null) {
                ImproperUIAPI.reload();
            }
        }
        public static void init() {
            if (INSTANCE == null) {
                INSTANCE = new DataReload();
            }
        }
        @SubscribeEvent
        public static void onAddReloadListener(AddReloadListenerEvent event) {
            if (INSTANCE == null) {
                init();
            }
            event.addListener(new DataReload());
        }
    }
}
