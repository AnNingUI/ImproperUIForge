package org.anningui.iui_forge.kubejs;

import org.anningui.iui_forge.ImproperUIAPI;
import org.anningui.iui_forge.kubejs.event.IImportantUIEvents;
import org.anningui.iui_forge.kubejs.event.ui.UIRegister;
import org.anningui.iui_forge.kubejs.event.callback.CallbackRegister;

import static org.anningui.iui_forge.kubejs.event.ui.UIRegister.uiLocations;

public class ImproperUIKJS {
    public void init() {
        IImportantUIEvents.UI_REGISTER.post(new UIRegister());
        IImportantUIEvents.CALLBACK_REGISTER.post(new CallbackRegister());
        uiLocations.forEach((modId, locations) -> {
            locations.forEach(location -> {
                ImproperUIAPI.init(modId, ImproperUIKJS.class, "scripts/" + location + ".ui");
            });
        });
    }
}
