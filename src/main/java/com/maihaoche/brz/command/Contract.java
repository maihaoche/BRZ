package com.maihaoche.brz.command;

/**
 * Created by alex on 2017/11/17.
 */
public class Contract {
    private final String id;
    private final String type;
    private final String mime;

    public Contract(String id, String type, String mime) {
        this.id = id;
        this.type = type;
        this.mime = mime;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMime() {
        return mime;
    }
}
