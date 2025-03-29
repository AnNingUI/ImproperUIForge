package org.anningui.iui_forge.render.elements.svg;


import com.github.weisj.jsvg_mc.SVGDocument;
import com.github.weisj.jsvg_mc.geometry.size.FloatSize;
import com.github.weisj.jsvg_mc.parser.LoaderContext;
import com.github.weisj.jsvg_mc.parser.SVGLoader;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.util.Optional;
import java.util.logging.Logger;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB_PRE;

public class SvgUtils {
    private static final Logger logger = Logger.getLogger(SvgUtils.class.getName());
    public static Optional<InputStream> getSvgStream(String svgId) {
        try {
            ResourceLocation id = ResourceLocation.tryParse(svgId);
            if (id != null) {
                return Optional.ofNullable(rl2Stream(id));
            }
        } catch (Exception e) {
            logger.warning("Error while parsing SVG ID: " + svgId);
            return Optional.empty();
        }
        return Optional.empty();
    }


    public static Optional<NativeImage> getSvgImage(String svgId) {
        // 获取 SVG 文件路径
        try {
            Optional<InputStream> svgPath = getSvgStream(svgId);
            if (svgPath.isEmpty()) {
                return Optional.empty();
            }
            InputStream svgStream = svgPath.get();

            SVGLoader loader = new SVGLoader();
            SVGDocument svgDocument = loader.load(svgStream, null, LoaderContext.builder().build());
            if (svgDocument != null) {
                FloatSize size = svgDocument.size();
                BufferedImage image = new BufferedImage((int) size.width,(int) size.height, TYPE_INT_ARGB_PRE);
                Graphics2D g = image.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
                g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                svgDocument.render(null, g);
                g.dispose();
                svgStream.close();
                return Optional.of(img2NativeImage(image));
            }
            svgStream.close();
        } catch (Exception e) {
            logger.warning("Error while loading SVG: " + svgId);
            return Optional.empty();
        }
        return Optional.empty();
    }

    public static int reverseColorR2b(int color) {
        int r = (color >> 16) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = color & 0xff;
        int a = (color >> 24) & 0xff;
        return (b << 16) | (g << 8) | r | (a << 24);
    }

    /**
     * Converts a BufferedImage to a NativeImage.
     *
     * @param img The BufferedImage to be converted.
     * @return The resulting NativeImage.
     */
    public static NativeImage img2NativeImage(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        // Create a new NativeImage to hold the converted image
        NativeImage nativeImage = new NativeImage(width, height, false); // false to avoid mipmaps
        // Loop through each pixel of the BufferedImage and copy to NativeImage
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = img.getRGB(x, y);
                // Set the pixel in NativeImage (RGBA format)
                nativeImage.setPixelRGBA(x, y, reverseColorR2b(pixel));
            }
        }
        return nativeImage;
    }

    @OnlyIn(Dist.CLIENT)
    public static InputStream rl2Stream(ResourceLocation rl) {
        try {
            Resource resource = Minecraft.getInstance().getResourceManager().getResource(rl).orElse(null);
            if (resource != null) {
                return resource.open();
            }
        } catch (IOException e) {
            logger.warning("Error while opening SVG resource: " + rl);
        }
        return null; // 返回 null 代表文件不存在
    }
}
