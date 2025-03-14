package org.anningui.iui_forge;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.anningui.iui_forge.script.callbacks.BuiltInCallbacks;
import org.anningui.iui_forge.util.ClientRegistry;
import org.lwjgl.glfw.GLFW;
public class ImproperUI
{
	public static final KeyMapping BIND = ClientRegistry.register(new KeyMapping(
			"binds.iui_forge.menu",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_RIGHT_SHIFT,
			"binds.iui_forge"
	));

	public void onInitialize() {
		ImproperUIAPI.init("iui_forge", ImproperUI.class,
				"assets/iui_forge/iui_forge/homescreen.ui",
				"assets/iui_forge/iui_forge/example.ui"
		);
	}

	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent event){
		while (BIND.isDown()) {
			ImproperUIAPI.parseAndRunFile("iui_forge", "homescreen.ui", new BuiltInCallbacks());
		}
	}
}
