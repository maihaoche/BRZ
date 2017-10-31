package com.maihaoche.brz.result;

/**
 * Created by alex on 2017/10/27.
 */
public class DownloadFile {
    private final String name;
    private final byte[] content;

    public DownloadFile(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }
}
