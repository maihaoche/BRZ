package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/10/31.
 */
public class Photo {
    private final Integer type;
    private final String url;
    private final String reportTime;
    private final String mime;

    public Photo(Integer type, String url, String reportTime, String mime) {
        this.type = type;
        this.url = url;
        this.reportTime = reportTime;
        this.mime = mime;
    }

    public Integer getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getReportTime() {
        return reportTime;
    }

    public String getMime() {
        return mime;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "type=" + type +
                ", url='" + url + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", mime='" + mime + '\'' +
                '}';
    }
}
