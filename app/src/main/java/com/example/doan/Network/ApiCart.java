package com.example.doan.Network;



import com.example.doan.Model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiCart {
    @GET("/api/v1/cart")
    Call<DataResponse> getCarts(@Header("Authorization") String token);
}
