package com.nearsoft.sofe.map.model;

import com.google.gson.annotations.SerializedName;
import com.nearsoft.sofe.map.model.Geometry;

public class Place {
    @SerializedName("formatted_address") private String formattedAddress;
    @SerializedName("geometry") private Geometry geometry;
    @SerializedName("name") private String name;

    public Place(String formattedAddress, Geometry geometry, String name) {
        this.formattedAddress = formattedAddress;
        this.geometry = geometry;
        this.name = name;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }
}
