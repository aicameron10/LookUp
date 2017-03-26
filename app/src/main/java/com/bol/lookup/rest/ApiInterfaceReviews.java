package com.bol.lookup.rest;

import com.bol.lookup.model.ReviewsResponse;
import com.bol.lookup.model.SendReview;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterfaceReviews {
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("review")
    Call<SendReview> createReview(@Body SendReview review);

    @GET("reviews/{id}")
    Call<ReviewsResponse> getReviews(@Path("id") String id);

}