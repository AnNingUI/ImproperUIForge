package org.anningui.iui_forge.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import org.anningui.iui_forge.kubejs.utils.IUIUtilJS;


import static org.anningui.iui_forge.ImproperUIForge.*;
import static org.anningui.iui_forge.kubejs.event.IImportantUIEvents.group;

public class ImportantUIKubeJSPlugin extends KubeJSPlugin {
    @Override
    public void registerEvents() {
        if (kjsIsOk && rhinoIsOk) {
            group.register();
        }
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        if (kjsIsOk && rhinoIsOk) {
            event.add("IUIUtils", IUIUtilJS.class);
        }
    }

    @Override
    public void initStartup() {
        if (kjsIsOk && rhinoIsOk) {
            ((ImproperUIKJS) KJS_UI).init();
        }
    }
}
