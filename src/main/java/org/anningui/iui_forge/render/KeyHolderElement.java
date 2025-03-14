package org.anningui.iui_forge.render;

import org.anningui.iui_forge.config.ConfigKey;
import org.anningui.iui_forge.config.ConfigKeyHolder;
import org.anningui.iui_forge.config.PropertyCache;
import org.anningui.iui_forge.script.ScriptParser;

public abstract class KeyHolderElement extends Element implements ConfigKeyHolder {

    public abstract void onLoadKey(PropertyCache cache, ConfigKey key);
    public abstract void onSaveKey(PropertyCache cache, ConfigKey key);

    protected KeyHolderElement(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    protected KeyHolderElement() {
        this(0, 0, 0, 0);
    }

    @Override
    public void style() {
        super.style();

        var key = getConfigKey();
        if (key != null)
            onLoadKey(ScriptParser.getCache(key.modId), key);
    }

    @Override
    public void onLeftClick(int mx, int my, boolean release) {
        super.onLeftClick(mx, my, release);

        var key = getConfigKey();
        if (key != null && release)
            onSaveKey(ScriptParser.getCache(key.modId), key);
    }

    @Override
    public void onKey(int key, int scan, boolean release) {
        super.onKey(key, scan, release);

        var configKey = getConfigKey();
        if (configKey != null && release)
            onSaveKey(ScriptParser.getCache(configKey.modId), configKey);
    }

    @Override
    public ConfigKey getConfigKey() {
        return ELEMENT_KEY_HOLDER.apply(this);
    }
}
