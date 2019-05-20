package com.nearsoft.sofe.map.model;

import com.google.gson.annotations.SerializedName;
import com.nearsoft.sofe.map.model.Place;

import java.util.List;

public class PlacesResponse {
    @SerializedName("candidates") private List<Place> candidates;
    @SerializedName("status") private String status;

    public PlacesResponse(List<Place> candidates, String status) {
        this.candidates = candidates;
        this.status = status;
    }

    public List<Place> getCandidates() {
        return candidates;
    }

    public String getStatus() {
        return status;
    }
}
