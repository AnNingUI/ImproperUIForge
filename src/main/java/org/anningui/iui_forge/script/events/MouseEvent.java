package org.anningui.iui_forge.script.events;

import org.anningui.iui_forge.render.Element;
import org.anningui.iui_forge.render.constants.InputType;
import org.anningui.iui_forge.script.Event;

public class MouseEvent extends Event {

    public final int button;
    public final int delta;
    public final InputType input;
    public final Element target;

    public MouseEvent(int button, int delta, InputType input, Element target) {
        this.button = button;
        this.delta = delta;
        this.input = input;
        this.target = target;
    }
}