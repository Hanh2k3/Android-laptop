package com.example.doan.Network;

import com.example.doan.Model.GeneralResponse;
import com.example.doan.Model.Order;
import com.example.doan.Model.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiOrder {
      @GET("api/v1/order")
      Call<GeneralResponse<Order>> getAllOrder(@Header("Authorization") String token);
}
