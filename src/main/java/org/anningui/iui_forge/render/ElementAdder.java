package org.anningui.iui_forge.render;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ElementAdder {
    @SubscribeEvent
    public static void onElementTagRegistration(ElementTagRegistrationEvent event) {
    }
}
