package com.example.doan.Network;


import com.example.doan.Model.DataResponse;
import com.example.doan.Model.LaptopResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiLaptop {

    @GET("/api/v1/laptop")
    Call<DataResponse> getLaptops();

    @GET("api/v1/laptop/{id}")
    Call<LaptopResponse> getLaptop(@Path("id") Integer id);

}
