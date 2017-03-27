package com.bol.lookup.rest;

import com.bol.lookup.model.DetailResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    String BASE_URL = "https://api.bol.com/catalog/v4/";

    @GET("products/{id}")
    Observable<DetailResponse> getProducts(@Path("id") String id, @Query("apikey") String apiKey, @Query("format") String format);

}