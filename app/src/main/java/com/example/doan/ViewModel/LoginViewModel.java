package com.example.doan.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.doan.Model.User;
import com.example.doan.Model.UserApiResponse;
import com.example.doan.Repository.UserRepository;
import com.example.doan.Utils.SaveToken;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();
    private MutableLiveData<UserApiResponse> userLiveData = new MutableLiveData<>();
    private UserRepository userRepository;
    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    public  void login(String email, String password) throws InterruptedException {
        User user = new User(email, password);
        userRepository.loginUser(user).observeForever(new Observer<UserApiResponse>() {
            @Override
            public void onChanged(UserApiResponse userApiResponse) {
                if(userApiResponse.getStatus() == 1) {
                    isLogin.setValue(true);
                    Log.i("Token", userApiResponse.getToken());
                    SaveToken.saveTokens(userApiResponse.getToken());
                } else {
                    isLogin.setValue(false);
                }
            }
        });
    }
}
