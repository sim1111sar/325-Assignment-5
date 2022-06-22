package com.example.myyelp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpAPI {

    @GET("search")
    Call<BodyResponse> getRestaurants(@Query("term") String term, @Query("location") String location, @Query("limit") int limit);

}
