package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2017/10/26.
 */
public class SendMessageCommand extends AbstractCommand {

    private final Long timestamp = System.currentTimeMillis();
    private final String eventId;
    private final String eventType;
    private final List<Long> payload;


    public SendMessageCommand(  String eventId, String eventType, List<Long> payload) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public List<Long> getPayload() {
        return payload;
    }
}
