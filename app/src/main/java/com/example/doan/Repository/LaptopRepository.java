package com.example.doan.Repository;

import androidx.lifecycle.MutableLiveData;


import com.example.doan.Model.DataResponse;
import com.example.doan.Network.ApiLaptop;
import com.example.doan.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopRepository {

    private ApiLaptop laptopService;

    public LaptopRepository() {
        laptopService = RetrofitClient.create().create(ApiLaptop.class);
    }

    public MutableLiveData<DataResponse> getLaptops() {
        MutableLiveData<DataResponse> liveData = new MutableLiveData<>();
        laptopService.getLaptops().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
            }
        });
        return  liveData;
    }

    public  MutableLiveData<DataResponse> getLaptop(Integer id) {
        MutableLiveData<DataResponse> liveData = new MutableLiveData<>();
        laptopService.getLaptop(id).enqueue(new Callback<DataResponse>() {
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
    public  MutableLiveData<DataResponse> getCategoryLaptop(Integer id) {
        MutableLiveData<DataResponse> liveData = new MutableLiveData<>();
        laptopService.getCategoryLaptop(id).enqueue(new Callback<DataResponse>() {
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
