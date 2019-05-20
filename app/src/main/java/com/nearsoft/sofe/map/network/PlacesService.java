package com.nearsoft.sofe.map.network;

import com.nearsoft.sofe.map.model.LocationBias;
import com.nearsoft.sofe.map.model.PlacesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesService {
    @GET("https://maps.googleapis.com/maps/api/place/findplacefromtext/json")
    Call<PlacesResponse> getPlacesFromQueryAsync(@Query("input") String query,
                                                 @Query("inputtype") String inputType /*= "textquery"*/,
                                                 @Query("fields") String fields /*="formatted_address,name,geometry"*/,
                                                 @Query("locationbias") LocationBias locationBias,
                                                 @Query("key")String key /*="AIzaSyDKums--XeYkO-RLSHlC0MU8Sav_l2o1tA")*/);
}
