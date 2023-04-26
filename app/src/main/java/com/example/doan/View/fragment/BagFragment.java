package com.example.doan.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doan.Model.Laptop;
import com.example.doan.R;
import com.example.doan.View.adapter.CartAdapter;
import com.example.doan.ViewModel.CartViewModel;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class BagFragment extends Fragment {
    private CartViewModel cartViewModel ;
    private TextView total ;
    private RecyclerView recyclerViewCart ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bag, container, false);
        total = view.findViewById(R.id.totalPriceBagFrag);
        recyclerViewCart = view.findViewById(R.id.cartRecView);

        cartViewModel= new ViewModelProvider(getActivity()).get(CartViewModel.class);

        cartViewModel.setCart();
        cartViewModel.getCart().observeForever(new Observer<List<Laptop>>() {
            @Override
            public void onChanged(List<Laptop> laptops) {
                recyclerViewCart.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
                recyclerViewCart.setAdapter(new CartAdapter(getActivity(),laptops));
            }
        });
        cartViewModel.getTotal().observeForever(new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {

                Locale locale = new Locale("vi", "VN");
                NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                total.setText(nf.format(aDouble));
            }
        });
        return view;
    }
}