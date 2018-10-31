package com.maihaoche.brz.command;

import java.util.List;

/**
 * Created by alex on 2018/10/31.
 */
public class Carrier {
    private final Long carrierId;
    private final String carrierName;
    private final List<Photo> photos;


    public Carrier(Long carrierId, String carrierName, List<Photo> photos) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.photos = photos;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "carrierId=" + carrierId +
                ", carrierName=" + carrierName +
                ", photos=" + photos +
                '}';
    }
}
