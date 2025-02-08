package org.anningui.iui_forge.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.resources.ResourceLocation;
import org.anningui.iui_forge.client.ImproperUIClient;
import org.anningui.iui_forge.interfaces.FontManagerAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {
    @Shadow @Final private FontManager fontManager;

    @Shadow @Final public static ResourceLocation UNIFORM_FONT;

    @Inject(method = "selectMainFont", at = @At("TAIL"))
    public void initFont(CallbackInfo ci) {
        var fonts = ((FontManagerAccessor)this.fontManager);
        ImproperUIClient.getInstance().codeRenderer = fonts.createRenderer(UNIFORM_FONT);
    }
}
