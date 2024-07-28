package src.services.event_handling;

import src.types.EventEnum;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public abstract class DEHM {
    protected abstract void ctrlShiftQHandler(EventEnum event);

    public DEHM() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof KeyEvent keyEvent) {
                    if (keyEvent.isControlDown() && keyEvent.isShiftDown() && keyEvent.getKeyCode() == KeyEvent.VK_Q) {
                        ctrlShiftQHandler(null);
                    }
                }

            }
        }, AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.WINDOW_EVENT_MASK | PacmanEvent.CUSTOM_EVENT_ID);
    }
}
