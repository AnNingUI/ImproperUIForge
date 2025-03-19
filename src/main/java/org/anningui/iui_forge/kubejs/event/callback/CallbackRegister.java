package org.anningui.iui_forge.kubejs.event.callback;

import dev.latvian.mods.kubejs.client.ClientEventJS;
import net.minecraft.resources.ResourceLocation;
import org.anningui.iui_forge.script.events.KeyEvent;
import org.anningui.iui_forge.script.events.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class CallbackRegister extends ClientEventJS {
    public CallbackRegister() {}

    public void onKeyEvent(ResourceLocation uiId, String methodName, Consumer<KeyEvent> event) {
        KeyEventSetList list = KeyEventSetList.create(uiId);
        list.addKeyEvent(methodName, event);
    }

    public void onMouseEvent(ResourceLocation uiId, String methodName, Consumer<MouseEvent> event) {
        MouseEventSetList list = MouseEventSetList.create(uiId);
        list.addMouseEvent(methodName, event);
    }
}
