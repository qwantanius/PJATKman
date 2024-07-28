package src.services.event_handling;

import src.types.EventEnum;

import java.awt.*;

public class PacmanEvent extends AWTEvent {
    public static final int CUSTOM_EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;

    private final EventEnum eventEnum;
    private final String message;

    public PacmanEvent(Object source, EventEnum eventEnum) {
        super(source, CUSTOM_EVENT_ID);
        this.message = switch (eventEnum) {
            case WIN -> "CONGRATS!";
            case LOSE -> "You did not won :(";
        };
        System.out.println("EVENT POSTED: " + this.message);
        this.eventEnum = eventEnum;
    }

    public EventEnum getEventEnum() {
        return eventEnum;
    }

    public String getMessage() {
        return this.message;
    }
}
