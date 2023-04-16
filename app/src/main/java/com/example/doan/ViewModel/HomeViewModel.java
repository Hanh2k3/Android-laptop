package com.example.doan.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.doan.Model.Slider;
import com.example.doan.Repository.SliderRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Slider>> listSlider = new MutableLiveData<List<Slider>>();
    private SliderRepository sliderRepository ;

    public HomeViewModel() {
        this.sliderRepository = new SliderRepository();
        setListSlider();
    }

    public MutableLiveData<List<Slider>> getListSlider() {
        return this.listSlider;
    }
    public  void setListSlider() {
        sliderRepository.getSliders().observeForever(new Observer<List<Slider>>() {
            @Override
            public void onChanged(List<Slider> sliders) {
                listSlider.setValue(sliders);
            }
        });
    }
}
