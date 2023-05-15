package com.example.doan.Network;

import com.example.doan.Model.District;
import com.example.doan.Model.FeesResponse;
import com.example.doan.Model.Province;
import com.example.doan.Model.Ward;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiGetFees {

    // GET LIST PROVINCES
   @GET("shiip/public-api/master-data/province")
   @Headers("token:c8fd103b-c7ba-11ed-bcba-eac62dba9bd9")
    Call<FeesResponse<Province>> getLProvinces();

   @GET("shiip/public-api/master-data/district")
   @Headers("token:c8fd103b-c7ba-11ed-bcba-eac62dba9bd9")
    Call<FeesResponse<District>> getDistricts(@Query("province_id") Integer id);

    @GET("shiip/public-api/master-data/ward")
    @Headers("token:c8fd103b-c7ba-11ed-bcba-eac62dba9bd9")
    Call<FeesResponse<Ward>> getWards(@Query("district_id") Integer id);

}
