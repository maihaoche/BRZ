package com.maihaoche.brz.command;

/**
 * Created by alex on 2017/11/17.
 */
public class Contract {
    // 合同ID
    private final String id;
    // 合同业务类型
    private final String type;
    // 合同文件类型
    private final String mime;
    // 合同下载链接
    private final String url;

    public Contract(String id, String type, String mime, String url) {
        this.id = id;
        this.type = type;
        this.mime = mime;
        this.url = url;
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

    public String getUrl() {
        return url;
    }
}
