package com.nearsoft.sofe.map.model;

import com.google.gson.annotations.SerializedName;

public class Geometry {
    @SerializedName("location") private LatLon location;
    @SerializedName("viewport") private ViewPort viewPort;

    public Geometry(LatLon location, ViewPort viewPort) {
        this.location = location;
        this.viewPort = viewPort;
    }

    public LatLon getLocation() {
        return location;
    }

    public ViewPort getViewPort() {
        return viewPort;
    }
}
