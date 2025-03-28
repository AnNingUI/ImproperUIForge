package org.anningui.iui_forge.kubejs.event.callback;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.resources.ResourceLocation;
import org.anningui.iui_forge.script.CallbackListener;
import org.anningui.iui_forge.script.Event;
import org.anningui.iui_forge.script.events.KeyEvent;
import org.anningui.iui_forge.script.events.MouseEvent;

import static java.util.Objects.isNull;
import static org.anningui.iui_forge.ImproperUIForge.kjsIsOk;
import static org.anningui.iui_forge.ImproperUIForge.rhinoIsOk;

public class BaseCallbacks implements CallbackListener {
    private final ResourceLocation id;
    public BaseCallbacks(ResourceLocation id) {
       this.id = id;
    }
    @Override
    public <E extends Event> void runCallbacks(String methodName, E target) {
        if (target instanceof KeyEvent e && KeyEventSetList.isEquals(id, methodName)) {
            var event = KeyEventSetList.getEvent(id, methodName);
            if (!isNull(event)) {
                event.accept(e);
            }
        } else if (target instanceof MouseEvent e && MouseEventSetList.isEquals(id, methodName)) {
            var event = MouseEventSetList.getEvent(id, methodName);
            if (!isNull(event)) {
                event.accept(e);
            }
        } else {
            if (kjsIsOk && rhinoIsOk) {
                ConsoleJS.CLIENT.error("Event " + methodName + " not found in " + id.toString());
            }
        }
    }
}
