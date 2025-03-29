package org.anningui.iui_forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.anningui.iui_forge.kubejs.ImproperUIKJS;

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
    }

}

