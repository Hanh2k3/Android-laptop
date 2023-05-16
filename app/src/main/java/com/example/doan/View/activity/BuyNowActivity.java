package com.example.doan.View.activity;

import static com.example.doan.Utils.SaveToken.getToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Model.BuyResponse;
import com.example.doan.Model.InforShipping;
import com.example.doan.Model.Laptop;
import com.example.doan.Model.LaptopBuy;
import com.example.doan.Model.MyRequest;
import com.example.doan.Model.ShipFeeRequest;
import com.example.doan.Model.ShippingBuy;
import com.example.doan.Model.UserProfile;
import com.example.doan.R;
import com.example.doan.View.adapter.LaptopBuyAdapter;
import com.example.doan.ViewModel.DetailViewModel;
import com.example.doan.ViewModel.FeesViewModel;
import com.example.doan.ViewModel.OrderViewModel;
import com.example.doan.ViewModel.ProfileViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuyNowActivity extends AppCompatActivity {


    private  DetailViewModel detailViewModel ;
    private FeesViewModel feesViewModel ;
    private ProfileViewModel profileViewModel ;
    private OrderViewModel orderViewModel ;
    private List<Laptop> listLaptops = new ArrayList<>();
    private RecyclerView rcListItem ;
    private Integer qty ;

    private Double totalProduct ;
    private Double feeShip ;
    private String name ;
    private TextView tvName ;
    private  TextView tvAddress ;
    private TextView tvTotalProduct ;
    private  TextView tvShippingFee ;
    private TextView tvTotalPayment ;
    private TextView tvLastTotalPayment ;

    private MyRequest serviceRequest ;
    private  Integer wardId;
    private  Integer districtId;
    private Integer service_id ;
    private Integer height ;
    private Integer length ;
    private Integer width ;
    private Integer weight ;
    private Button btnBuyNow ;
    private Integer methodPayment;
    private String address ;
    private String phone ;
    private List<LaptopBuy> listLaptopBuy ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);

        rcListItem = findViewById(R.id.rcLaptopsOrderPage);
        tvName = findViewById(R.id.tvNameBuy);
        tvAddress = findViewById(R.id.tvAddressBuy);
        tvTotalProduct = findViewById(R.id.tvTotalProducts);
        tvShippingFee = findViewById(R.id.tvShippingFee);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);
        tvLastTotalPayment = findViewById(R.id.tvLastTotalPayment);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        methodPayment = 1;

        Intent intent = getIntent();
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        feesViewModel = new ViewModelProvider(this).get(FeesViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        boolean isBuyNow = intent.getBooleanExtra("isBuyNow", false );
        listLaptopBuy = new ArrayList<>();
        if(isBuyNow) { // when come from detail page
            Integer laptopId = intent.getIntExtra("laptopId", 0 );
            qty  = intent.getIntExtra("qty", 1);
            height = 15 ;
            length = 15 ;
            weight = 1000;
            width= 15 ;

            detailViewModel.setLaptop(laptopId);
            detailViewModel.getLaptop().observeForever(new Observer<Laptop>() {
                @Override
                public void onChanged(Laptop laptop) {
                    listLaptops.add(laptop);
                    totalProduct = qty * laptop.getPrice() + 0.0;

                    rcListItem.setLayoutManager(new LinearLayoutManager(BuyNowActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    rcListItem.setHasFixedSize(true);
                    rcListItem.setAdapter(new LaptopBuyAdapter(BuyNowActivity.this, listLaptops, qty));
                    Locale locale = new Locale("vi", "VN");
                    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                    tvTotalProduct.setText(nf.format(Double.valueOf(totalProduct)));

                    // set for list laptop
                    LaptopBuy laptopBuy = new LaptopBuy(laptop.getId(), laptop.getPrice(), qty);
                    listLaptopBuy.add(laptopBuy);
                }
            });
        } else {

        }
        // set up for delivery

        profileViewModel.setAddressDefault();
        profileViewModel.getAddressDefault().observeForever(new Observer<InforShipping>() {
            @Override
            public void onChanged(InforShipping inforShipping) {
                name = inforShipping.getPhone();
                feeShip = 20000.0*qty ;
                districtId = inforShipping.getDistrictId();
                 wardId = inforShipping.getWardId();
                tvAddress.setText(inforShipping.getAddress());
                serviceRequest = new MyRequest(1529, districtId);
                profileViewModel.getProfile(getToken());
                Locale locale = new Locale("vi", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                Double total = feeShip + totalProduct ;

                tvTotalPayment.setText(nf.format(Double.valueOf(feeShip + totalProduct)));
                tvLastTotalPayment.setText(nf.format(Double.valueOf(feeShip + totalProduct)));
                tvShippingFee.setText(nf.format(Double.valueOf(feeShip)));

                address = inforShipping.getAddress();
                phone = inforShipping.getPhone();
                feesViewModel.setService(serviceRequest);

            }
        });
        profileViewModel.getUserProfile().observeForever(new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                name  = userProfile.getName() + " | " + name ;
                tvName.setText(name);
            }
        });

        // get fee ship
        feesViewModel.getService().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                service_id = integer ;
                ShipFeeRequest shipFeeRequest = new ShipFeeRequest(service_id, 500000, null,
                                                        1529, districtId, wardId+"", height, length, weight, width);
                feesViewModel.setFee(shipFeeRequest);
            }
        });
        feesViewModel.getFee().observeForever(new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
               feeShip=aDouble;
                Locale locale = new Locale("vi", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                tvTotalPayment.setText(nf.format(Double.valueOf(feeShip)));
                tvLastTotalPayment.setText(nf.format(Double.valueOf(feeShip)));
                tvShippingFee.setText(nf.format(Double.valueOf(feeShip)));

            }
        });


        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNow();
            }
        });



    }

    private void buyNow() {

        ShippingBuy shippingBuy = new ShippingBuy(address, phone, "Notes ", feeShip);
        BuyResponse buyResponse = new BuyResponse(listLaptopBuy, methodPayment, shippingBuy);

        orderViewModel.setIsOrder(buyResponse);
        orderViewModel.getIsOrder().observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(BuyNowActivity.this, "Order ok", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BuyNowActivity.this, OrderActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(BuyNowActivity.this, "Order false", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}