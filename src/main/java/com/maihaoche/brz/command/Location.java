package com.maihaoche.brz.command;

import java.io.Serializable;

/**
 * <p> 对接中原银行 </p>
 *
 * @author: 文轩（wenxuan@maihaoche.com）
 * @date: 2018/10/31 4:16 PM
 */
public class Location implements Serializable {

    /**
     * 位置
     */
    private String location;

    /**
     * 上报时间
     */
    private String reportTime;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
}