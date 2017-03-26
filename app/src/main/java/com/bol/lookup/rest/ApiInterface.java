package com.bol.lookup.rest;

import com.bol.lookup.model.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    @GET("search/")
    Call<SearchResponse> getSearchResults(@QueryMap Map<String, String> options);

    @GET("products/{id}")
    Call<SearchResponse> getProducts(@Path("id") int id, @Query("apikey") String apiKey, @Query("format") String format);

    @GET("recommendations/{id}")
    Call<SearchResponse> getRecommendations(@Path("id") int id, @Query("apikey") String apiKey, @Query("format") String format);

    @GET("relatedproducts/{id}")
    Call<SearchResponse> getRelatedProducts(@Path("id") int id, @Query("apikey") String apiKey, @Query("format") String format);

}