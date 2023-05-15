package com.example.doan.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.doan.Model.District;
import com.example.doan.Model.FeesResponse;
import com.example.doan.Model.Province;
import com.example.doan.Model.Ward;
import com.example.doan.Network.ApiGetFees;
import com.example.doan.Network.RetrofitShipping;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeesRepository {
    private ApiGetFees apiGetFees ;

    public FeesRepository() {
        this.apiGetFees = RetrofitShipping.create().create(ApiGetFees.class);
    }

    public MutableLiveData<List<Province>> getProvinces() {
        MutableLiveData<List<Province>> list = new MutableLiveData<>();
        apiGetFees.getLProvinces().enqueue(new Callback<FeesResponse<Province>>() {
            @Override
            public void onResponse(Call<FeesResponse<Province>> call, Response<FeesResponse<Province>> response) {
                if(response.isSuccessful()) {
                    List<Province> data = response.body().getData();
                    list.setValue(data);
                }
            }

            @Override
            public void onFailure(Call<FeesResponse<Province>> call, Throwable t) {
            }
        });
        return list ;
    }
    public MutableLiveData<List<District>> getDistricts(int idProvinces) {
        MutableLiveData<List<District>> list = new MutableLiveData<>();
        apiGetFees.getDistricts(idProvinces).enqueue(new Callback<FeesResponse<District>>() {
            @Override
            public void onResponse(Call<FeesResponse<District>> call, Response<FeesResponse<District>> response) {
                if(response.isSuccessful()){
                    List<District> data = response.body().getData();
                    list.setValue(data);
                }
            }
            @Override
            public void onFailure(Call<FeesResponse<District>> call, Throwable t) {

            }
        });

        return  list;
    }

    public  MutableLiveData<List<Ward>> getWards(int provinceId) {
        MutableLiveData<List<Ward>> list = new MutableLiveData<>();

        apiGetFees.getWards(provinceId).enqueue(new Callback<FeesResponse<Ward>>() {
            @Override
            public void onResponse(Call<FeesResponse<Ward>> call, Response<FeesResponse<Ward>> response) {
                if(response.isSuccessful()) {
                    list.setValue(response.body().getData());
                }
            }
            @Override
            public void onFailure(Call<FeesResponse<Ward>> call, Throwable t) {
            }
        });
        return list;
    }
}
