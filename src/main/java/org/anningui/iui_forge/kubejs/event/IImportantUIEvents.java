package org.anningui.iui_forge.kubejs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import org.anningui.iui_forge.kubejs.event.callback.CallbackRegister;
import org.anningui.iui_forge.kubejs.event.ui.UIRegister;

public interface IImportantUIEvents {
    EventGroup group = EventGroup.of("ImportantUIEvents");
    EventHandler CALLBACK_REGISTER = group.startup("callback", () -> CallbackRegister.class);
    EventHandler UI_REGISTER = group.startup("uiInit", () -> UIRegister.class);
}
