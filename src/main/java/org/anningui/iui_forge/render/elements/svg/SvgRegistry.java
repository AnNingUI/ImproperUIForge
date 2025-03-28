package org.anningui.iui_forge.render.elements.svg;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import static org.anningui.iui_forge.render.Element.decodeSvgRl;

public class SvgRegistry {
    private static final Logger logger = Logger.getLogger(SvgRegistry.class.getName());
    private static final Map<ResourceLocation, NativeImage> svgRegistry = new HashMap<>();
    private static final Minecraft client = Minecraft.getInstance();
    private static final TextureManager textureManager = client.getTextureManager();

    private static final ResourceLocation DEFAULT_SVG_ID = new ResourceLocation("iui_forge", "textures/svg/test.svg");

    static {
        register(DEFAULT_SVG_ID);
    }

    public static void register(String nameSpace, String svgId) {
        try {
            var rl = new ResourceLocation(nameSpace, svgId);
            Optional<NativeImage> image = SvgUtils.getSvgImage(decodeSvgRl(rl).toString());
            ResourceLocation resourceLocation = new ResourceLocation(nameSpace, svgId);
            if (image.isPresent() && !svgRegistry.containsKey(resourceLocation)) {
                NativeImage nativeImage = image.get();
                System.out.println("nativeImage size: " + nativeImage.getWidth() + "x" + nativeImage.getHeight());
                svgRegistry.put(resourceLocation, nativeImage);
                DynamicTexture texture = new DynamicTexture(nativeImage);
                textureManager.register(resourceLocation, texture);
            }
        } catch (Exception e) {
            logger.warning("Failed to register SVG image: " + nameSpace + ":" + svgId);
        }
    }

    public static void register(ResourceLocation resourceLocation) {
        register(resourceLocation.getNamespace(), resourceLocation.getPath());
    }

    public static NativeImage getImage(ResourceLocation resourceLocation) {
        var image = svgRegistry.get(resourceLocation);
        if (image == null) {
            image = svgRegistry.get(DEFAULT_SVG_ID);
        }
        return image;
    }

}
