package com.example.doan.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.doan.Model.User;
import com.example.doan.Model.UserApiResponse;
import com.example.doan.Network.ApiLogin;
import com.example.doan.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {

    private UserApiResponse userApiResponse;
    private ApiLogin loginService;
    private Integer status ;
    public UserRepository() {
        Retrofit retrofit = RetrofitClient.create();
        loginService = retrofit.create(ApiLogin.class);
    }
    public MutableLiveData<UserApiResponse> loginUser(User user) {
        MutableLiveData<UserApiResponse> userLiveData = new MutableLiveData<>();
        loginService.login(user).enqueue(new Callback<UserApiResponse>() {
            @Override
            public void onResponse(Call<UserApiResponse> call, Response<UserApiResponse> response) {
                if (response.isSuccessful()) {
                    Integer status = response.body().getStatus();
                    String message = response.body().getMessage();
                    if(status ==1) {
                        String token = response.headers().get("Authorization");
                        userLiveData.setValue(new UserApiResponse(status,message, token));
                    } else {
                        userLiveData.setValue(new UserApiResponse(status,message));
                    }
                }
            }
            @Override
            public void onFailure(Call<UserApiResponse> call, Throwable t) {
            }
        });
        return userLiveData;
    }
    public Integer getStatus() {
        return status;
    }
}
