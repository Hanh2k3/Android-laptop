package com.example.doan.Network;

import com.example.doan.Model.DataResponse;
import com.example.doan.Model.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiUser {

    @GET("api/v1/user")
    Call<UserProfile> getProfile(@Header("Authorization") String token);


}
