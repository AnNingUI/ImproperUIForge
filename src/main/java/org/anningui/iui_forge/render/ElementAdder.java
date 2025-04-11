package org.anningui.iui_forge.render;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.anningui.iui_forge.render.elements.func.ForDiv;
import org.anningui.iui_forge.render.elements.func.TemplateDiv;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ElementAdder {
    @SubscribeEvent
    public static void onElementTagRegistration(ElementTagRegistrationEvent event) {
        event.register("for2div", ForDiv::new);
        event.register("template", TemplateDiv::new);
        event.register("t2view", TemplateDiv.TemplateView::new);
    }
}
