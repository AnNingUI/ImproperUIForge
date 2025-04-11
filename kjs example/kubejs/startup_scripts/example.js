const $Util = Java.loadClass("net.minecraft.Util");
const $ChatUtils = Java.loadClass("org.anningui.iui_forge.util.ChatUtils");
const $GLFW = Java.loadClass("org.lwjgl.glfw.GLFW");

// LINK ./improper-ui/scripts/kubejs/homescreen.ui
let homescreen = new ResourceLocation("kubejs:homescreen")

ImportantUIEvents.uiInit(e => {
    e.register(homescreen)
    e.register(new ResourceLocation("kubejs:example"))
})


ImportantUIEvents.callback(ev => {
    console.log("homescreen loaded")
    ev.onMouseEvent(homescreen, "openGithub", (e) => {
        if (e.input.isDown())
            $Util.getPlatform().openUri("https://github.com/AnNingUI/ImproperUIForge");
    })

    ev.onMouseEvent(homescreen, "openModrinth", (e) => {
        if (e.input.isDown())
            $Util.getPlatform().openUri("https://modrinth.com/mod/improperui");
    })

    ev.onMouseEvent(homescreen, "openDiscord", (e) => {
        if (e.input.isDown())
            $Util.getPlatform().openUri("https://discord.gg/tMaShNzNtP");
    })

    ev.onMouseEvent(homescreen, "openWiki", (e) => {
        if (e.input.isDown())
            $Util.getPlatform().openUri("https://github.com/itzispyder/iui_nf/wiki");
    })

    ev.onMouseEvent(homescreen, "openExampleScreen", (e) => {
        if (e.input.isDown())
            IUIUtils.parseAndRunFile(new ResourceLocation("kubejs:example"))
    })

    ev.onKeyEvent(homescreen, "sendHelloWorld", (e) => {
        if (e.input.isDown())
            $ChatUtils.sendFormatted("Hello World + %s", $GLFW.glfwGetKeyName(e.key, e.scan));
    })

    ev.onMouseEvent(homescreen, "sendHelloWorld", (e) => {
        if (e.input.isDown())
            $ChatUtils.sendFormatted("Hello World + %s", $GLFW.glfwGetKeyName(e.key, e.scan));
    })

    ev.onMouseEvent(homescreen, "printSelf", (e) => {
        if (e.input.isDown())
            $ChatUtils.sendMessage("target: " + e.target);
    })

    ev.onKeyEvent(homescreen, "printSelf", (e) => {
        if (e.input.isDown())
            $ChatUtils.sendMessage("target: " + e.target);
    })
})
