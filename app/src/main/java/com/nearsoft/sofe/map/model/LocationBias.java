package com.nearsoft.sofe.map.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

public class LocationBias {
    @SerializedName("radius")
    int radius = 100000;
    @SerializedName("latLon")
    private LatLng latLng;

    public LocationBias(Location location) {
        this.latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }
}
