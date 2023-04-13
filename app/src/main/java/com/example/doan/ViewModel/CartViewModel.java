package com.example.doan.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.doan.Model.DataResponse;
import com.example.doan.Repository.CartRepository;
import com.example.doan.Utils.SaveToken;


public class CartViewModel extends ViewModel {

    private MutableLiveData<DataResponse> cart = new MutableLiveData<>();
    private CartRepository cartRepository;

    public CartViewModel() {
        cartRepository = new CartRepository();

    }

    public MutableLiveData<DataResponse> getCart() {
        return  cart ;
    }

    public void setCart() {
        String token = SaveToken.getToken();
        cartRepository.getAllItem(token).observeForever(new Observer<DataResponse>() {
            @Override
            public void onChanged(DataResponse dataResponse) {
                cart.setValue(dataResponse);
            }
        });
    }
}
