package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/1/5.
 */
public class AlterSignerCommand {
    private final String mobile;
    private final String name;

    public AlterSignerCommand(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }
}
