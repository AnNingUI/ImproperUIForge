package org.anningui.iui_forge.render.elements.func;

import cpw.mods.util.Lazy;
import net.minecraft.client.gui.GuiGraphics;
import org.anningui.iui_forge.ImproperUIAPI;
import org.anningui.iui_forge.config.ConfigKey;
import org.anningui.iui_forge.config.PropertyCache;
import org.anningui.iui_forge.render.Element;
import org.anningui.iui_forge.render.KeyHolderElement;
import org.anningui.iui_forge.render.math.Color;
import org.anningui.iui_forge.script.ScriptReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class TemplateDiv extends KeyHolderElement {
    private static final Map<String, TemplateDiv> allTemplates = new HashMap<>();
    public String tagName;
    public String className;
    public List<String> params;

    private TemplateView view;

    public static class TemplateView extends KeyHolderElement {
        private static final Map<TemplateDiv, TemplateView> allViews = new HashMap<>();
        public TemplateView() {
            super();
            System.out.println("Init TemplateView");
        }

        @Override
        public void init() {
            super.init();

        }

        public static TemplateView getView(TemplateDiv templateDiv) {
            return allViews.get(templateDiv);
        }

        @Override
        public void onRender(GuiGraphics context, int mx, int my, float delta) {
            // No Render
        }
        @Override
        public void onLoadKey(PropertyCache cache, ConfigKey key) {
            var property = cache.getProperty(key);
            if (property != null) fillColor = property.get(0).toColor();
        }

        @Override
        public void onSaveKey(PropertyCache cache, ConfigKey key) {
            cache.setProperty(key, fillColor, true);
        }

        @Override
        public void style() {
            super.style();
            // 设置默认颜色 为 透明
            if (getConfigKey() == null) fillColor = Color.parse("#00000000");
        }
    }

    public TemplateView getChildrenView() {
        return TemplateView.getView(this);
    }

    @Override
    public void onLoadKey(PropertyCache cache, ConfigKey key) {
        var property = cache.getProperty(key);
        if (property != null) fillColor = property.get(0).toColor();
    }

    @Override
    public void onSaveKey(PropertyCache cache, ConfigKey key) {
        cache.setProperty(key, fillColor, true);
    }

    @Override
    public void style() {
        super.style();
        // 设置默认颜色 为 透明
        if (getConfigKey() == null) fillColor = Color.parse("#00000000");
    }

    public TemplateDiv() {
        super();
        allTemplates.put(getId(), this);
        queueProperty("params: \"i\"");
        queueProperty("tag-name: \"div\"");
        queueProperty("class-name: \"_\"");
        queueProperty("t2view {}");
        view = getChildrenView();
    }

    @Override
    public void init() {
        super.init();
        registerProperty("params", args -> params = Arrays.stream(args.getAll().toString().split(" ")).toList());
        registerProperty("class-name", args -> className = args.getAll().toString());
        registerProperty("tag-name", args -> tagName = args.getAll().toString());
    }

    @Override
    public void onRender(GuiGraphics context, int mx, int my, float delta) {
        // No Render
        System.out.println(view.getId());
    }
}
