package org.anningui.iui_forge.script.callbacks;

import net.minecraft.Util;
import org.anningui.iui_forge.ImproperUIAPI;
import org.anningui.iui_forge.script.CallbackHandler;
import org.anningui.iui_forge.script.CallbackListener;
import org.anningui.iui_forge.script.events.KeyEvent;
import org.anningui.iui_forge.script.events.MouseEvent;
import org.anningui.iui_forge.util.ChatUtils;
import org.lwjgl.glfw.GLFW;

public class BuiltInCallbacks implements CallbackListener {

    @CallbackHandler
    public void openGithub(MouseEvent e) {
        if (e.input.isDown())
            Util.getPlatform().openUri("https://github.com/AnNingUI/ImproperUIForge");
    }

    @CallbackHandler
    public void openModrinth(MouseEvent e) {
        if (e.input.isDown())
            Util.getPlatform().openUri("https://modrinth.com/mod/improperui");
    }

    @CallbackHandler
    public void openDiscord(MouseEvent e) {
        if (e.input.isDown())
            Util.getPlatform().openUri("https://discord.gg/tMaShNzNtP");
    }

    @CallbackHandler
    public void openWiki(MouseEvent e) {
        if (e.input.isDown())
            Util.getPlatform().openUri("https://github.com/itzispyder/iui_nf/wiki");
    }

    @CallbackHandler
    public void openExampleScreen(MouseEvent e) {
        if (e.input.isDown())
            ImproperUIAPI.parseAndRunFile("iui_forge", "example.ui");
    }

    @CallbackHandler
    public void sendHelloWorld(MouseEvent e) {
        if (e.input.isDown())
            ChatUtils.sendMessage("Hello World");
    }

    @CallbackHandler
    public void sendHelloWorld(KeyEvent e) {
        if (e.input.isDown())
            ChatUtils.sendFormatted("Hello World + %s", GLFW.glfwGetKeyName(e.key, e.scan));
    }

    @CallbackHandler
    public void printSelf(MouseEvent e) {
        if (e.input.isDown())
            ChatUtils.sendMessage("target: " + e.target);
    }

    @CallbackHandler
    public void printSelf(KeyEvent e) {
        if (e.input.isDown())
            ChatUtils.sendMessage("target: " + e.target);
    }

}