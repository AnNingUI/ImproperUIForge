package org.anningui.iui_forge.client;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import org.anningui.iui_forge.ImproperUIAPI;
import org.jetbrains.annotations.NotNull;

public class ImproperUIReload  implements ResourceManagerReloadListener {
    public static ImproperUIReload INSTANCE;

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager pResourceManager) {
        ImproperUIAPI.reload();
    }

    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new ImproperUIReload();
        }
    }

    public static void onReload(AddReloadListenerEvent event) {
        if (INSTANCE != null) {
            event.addListener(INSTANCE);
        }
    }
}
