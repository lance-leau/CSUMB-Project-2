package com.example.mapwithmarker.PixelBay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApiService {
    @GET("api/")
    Call<PixabayResponse> searchPhotos(
            @Query("key") String apiKey,
            @Query("q") String query
    );
}
