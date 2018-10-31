package com.maihaoche.brz.command;

/**
 * Created by alex on 2018/10/31.
 */
public class Photo {
    private final Integer type;
    private final String url;
    private final String reportTime;

    public Photo(Integer type, String url, String reportTime) {
        this.type = type;
        this.url = url;
        this.reportTime = reportTime;
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

    @Override
    public String toString() {
        return "Photo{" +
                "type=" + type +
                ", url='" + url + '\'' +
                ", reportTime='" + reportTime + '\'' +
                '}';
    }
}
