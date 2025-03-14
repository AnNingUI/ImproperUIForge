package org.anningui.iui_forge.script.events;

import org.anningui.iui_forge.render.Element;
import org.anningui.iui_forge.render.constants.InputType;
import org.anningui.iui_forge.script.Event;

public class KeyEvent extends Event {

    public final int key, scan;
    public final InputType input;
    public final Element target;

    public KeyEvent(int key, int scan, InputType input, Element target) {
        this.key = key;
        this.scan = scan;
        this.input = input;
        this.target = target;
    }
}