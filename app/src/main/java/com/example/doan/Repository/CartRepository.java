package com.example.doan.Repository;

import androidx.lifecycle.MutableLiveData;


import com.example.doan.Model.DataResponse;
import com.example.doan.Network.ApiCart;
import com.example.doan.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {

    private ApiCart cartService ;
    private DataResponse dataResponse ;

    public CartRepository() {
        cartService= RetrofitClient.create().create(ApiCart.class);
    }

    public MutableLiveData<DataResponse> getAllItem(String token) {
        MutableLiveData<DataResponse> liveData = new MutableLiveData<>();

        cartService.getCarts(token).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if(response.isSuccessful()) {
                    liveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
            }
        });

        return  liveData;

    }
}
