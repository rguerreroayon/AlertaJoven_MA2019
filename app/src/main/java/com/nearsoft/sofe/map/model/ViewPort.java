package com.nearsoft.sofe.map.model;

import com.google.gson.annotations.SerializedName;
import com.nearsoft.sofe.map.model.LatLon;

public class ViewPort {
    @SerializedName("northeast") private LatLon northeast;
    @SerializedName("southwest") private LatLon southwest;

    public ViewPort(LatLon northeast, LatLon southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public LatLon getNortheast() {
        return northeast;
    }

    public LatLon getSouthwest() {
        return southwest;
    }
}
