package com.maihaoche.brz.command;

/**
 * Created by alex on 2017/10/26.
 */
public class SendMessageCommand<TPayload> {

    private final Long timestamp = System.currentTimeMillis();
    private final String eventId;
    private final String eventType;
    private final TPayload payload;


    public SendMessageCommand(String eventId, String eventType, TPayload payload) {
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

    public TPayload getPayload() {
        return payload;
    }
}
