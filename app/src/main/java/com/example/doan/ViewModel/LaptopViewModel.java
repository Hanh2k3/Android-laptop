package com.example.doan.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.doan.Model.DataResponse;
import com.example.doan.Model.Laptop;
import com.example.doan.Model.LaptopResponse;
import com.example.doan.Repository.LaptopRepository;


public class LaptopViewModel extends ViewModel {
    private MutableLiveData<DataResponse> listLaptop ;
    private LaptopRepository laptopRepository ;
    private MutableLiveData<Boolean> isNotExit ;
    private MutableLiveData<Laptop> laptop ;

    public LaptopViewModel() {
        laptopRepository = new LaptopRepository();
    }

    public MutableLiveData<DataResponse> getListLaptop() {
        return this.listLaptop;
    }

    public MutableLiveData<Laptop> getLaptop() {
        return this.laptop;
    }

    public MutableLiveData<Boolean> getIsNotExit() {
        return this.isNotExit;
    }

    public void getAll() {
        laptopRepository.getLaptops().observeForever(new Observer<DataResponse>() {
            @Override
            public void onChanged(DataResponse dataResponse) {
                listLaptop.setValue(dataResponse);
            }
        });
    }

    public void getLaptop(Integer id) {
        laptopRepository.getLaptop(id).observeForever(new Observer<LaptopResponse>() {
            @Override
            public void onChanged(LaptopResponse laptopResponse) {
                if(laptopResponse.getStatus() == 0) {
                    isNotExit.setValue(true);
                } else {
                    laptop.setValue(laptopResponse.getLaptop());
                }
            }
        });
    }
}
