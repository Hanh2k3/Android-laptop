package com.example.doan.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.doan.Model.Category;
import com.example.doan.Model.DataResponse;
import com.example.doan.Model.Laptop;
import com.example.doan.Model.Slider;
import com.example.doan.Model.UserProfile;
import com.example.doan.Repository.CategoryRepository;
import com.example.doan.Repository.LaptopRepository;
import com.example.doan.Repository.ProfileRepository;
import com.example.doan.Repository.SliderRepository;

import java.util.List;
import java.util.Vector;


public class ProfileViewModel extends ViewModel {

    private ProfileRepository profileRepository;
    private MutableLiveData<UserProfile> userProfile = new MutableLiveData<>();
    public ProfileViewModel() {
        this.profileRepository = new ProfileRepository();
    }

    public MutableLiveData<UserProfile> getUserProfile() {
        return userProfile;
    }

    public void getProfile(String token) {
        profileRepository.getProfile(token).observeForever(new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile rs) {
                userProfile.setValue(rs);
            }
        });

    }
}
