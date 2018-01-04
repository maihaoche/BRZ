package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/1/4.
 */
public class CheckPhoto {
    private final String front;
    private final String behind;
    private final String inner;
    private final String odometer;
    private final String nameplate;

    public CheckPhoto(String front, String behind, String inner, String odometer, String nameplate) {
        this.front = front;
        this.behind = behind;
        this.inner = inner;
        this.odometer = odometer;
        this.nameplate = nameplate;
    }

    public String getFront() {
        return front;
    }

    public String getBehind() {
        return behind;
    }

    public String getInner() {
        return inner;
    }

    public String getOdometer() {
        return odometer;
    }

    public String getNameplate() {
        return nameplate;
    }
}
