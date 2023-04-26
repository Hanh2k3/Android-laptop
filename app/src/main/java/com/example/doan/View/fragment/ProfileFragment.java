package com.example.doan.View.fragment;

import static com.example.doan.Utils.SaveToken.getToken;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan.Model.Category;
import com.example.doan.Model.Slider;
import com.example.doan.Model.UserProfile;
import com.example.doan.R;
import com.example.doan.View.activity.SettingActivity;
import com.example.doan.View.adapter.CategoryAdapter;
import com.example.doan.View.adapter.CoverLaptopAdapter;
import com.example.doan.ViewModel.ProfileViewModel;
import com.example.doan.ViewModel.ShopViewModel;

import java.util.List;

public class ProfileFragment extends Fragment {


    private TextView tvUserName;
    private TextView tvUserEmail;
    private TextView countOrder;
    private TextView shippingDefault;
    private ImageView avatar;

    private CardView shippingCard  ;
    private CardView orderCard  ;
    private CardView settingCard;
    private ProfileViewModel profileViewModel;






    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        tvUserName = view.findViewById(R.id.profileName_profileFrag);
        tvUserEmail = view.findViewById(R.id.profileEmail_profileFrag);
        countOrder = view.findViewById(R.id.count_order);
        shippingDefault = view.findViewById(R.id.shipping_default);

        shippingCard = view.findViewById(R.id.shippingAddressCard_ProfilePage) ;
        settingCard = view.findViewById(R.id.settingCd_profileFrag);
        orderCard = view.findViewById(R.id.orderCard);
        avatar = view.findViewById(R.id.profileImage_profileFrag);
        avatar.setImageResource(R.drawable.ic_profile);


        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        profileViewModel.getProfile(getToken());
        profileViewModel.getUserProfile().observeForever(new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                tvUserName.setText(userProfile.getName());
                tvUserEmail.setText(userProfile.getEmail());
                Integer count_orderV = userProfile.getCountOrder();
                if(count_orderV == 0) countOrder.setText("You have no order");
                else countOrder.setText(userProfile.getCountOrder().toString());
                if (userProfile.getShipping() == null )  shippingDefault.setText("No address");
                else shippingDefault.setText(userProfile.getShipping());
            }
        });

        shippingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        settingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });


        return view ;
    }
}