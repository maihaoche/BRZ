package com.maihaoche.brz.result;

import java.io.InputStream;

/**
 * Created by alex on 2017/10/27.
 */
public class DownloadFile {
    private final String name;
    private final InputStream content;

    public DownloadFile(String name, InputStream content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public InputStream getContent() {
        return content;
    }
}
