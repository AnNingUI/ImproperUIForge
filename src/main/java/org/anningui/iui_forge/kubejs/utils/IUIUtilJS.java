package org.anningui.iui_forge.kubejs.utils;

import net.minecraft.resources.ResourceLocation;
import org.anningui.iui_forge.ImproperUIAPI;
import org.anningui.iui_forge.kubejs.event.callback.BaseCallbacks;
import org.anningui.iui_forge.render.Element;
import org.anningui.iui_forge.render.ImproperUIPanel;
import org.anningui.iui_forge.script.ScriptParser;
import org.anningui.iui_forge.util.FileValidationUtils;

import java.io.File;
import java.util.List;

public class IUIUtilJS {
    public static List<Element> parse(String script) {
        return ScriptParser.parse(script);
    }

    public static List<Element> parse(File file) {
        return ScriptParser.parseFile(file);
    }

    public static void parseAndRunFile(ResourceLocation id) {
        ImproperUIAPI.parseAndRunFile(id.getNamespace(), id.getPath() + ".ui");
        File script = FileValidationUtils.rl2File(new ResourceLocation(id.getNamespace(), id.getPath() + ".ui"));
        new ImproperUIPanel(script, new BaseCallbacks(id)).open();
    }
}
