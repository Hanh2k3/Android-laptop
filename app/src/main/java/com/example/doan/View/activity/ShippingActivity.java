package com.example.doan.View.activity;

import static com.example.doan.Utils.SaveToken.getToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doan.Model.InforShipping;
import com.example.doan.R;
import com.example.doan.Utils.DividerItemDecoration;
import com.example.doan.View.adapter.AddressAdapter;
import com.example.doan.ViewModel.LoginViewModel;
import com.example.doan.ViewModel.ProfileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShippingActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel ;
    private RecyclerView recyclerView;

    private FloatingActionButton btnNew ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiping_address);

        recyclerView = findViewById(R.id.rcAddress);
        btnNew = findViewById(R.id.addAddress_ShippingPage);

        profileViewModel =  new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.setValueAddress(getToken());
        profileViewModel.getListInforShipping().observeForever(new Observer<List<InforShipping>>() {
            @Override
            public void onChanged(List<InforShipping> inforShippings) {
                recyclerView.setLayoutManager(new LinearLayoutManager(ShippingActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(new AddressAdapter(ShippingActivity.this, inforShippings));
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShippingActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });




    }
}