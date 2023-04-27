package com.example.doan.View.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doan.View.fragment.OrderFragment;
import com.example.doan.View.fragment.OrderedFragment;
import com.example.doan.View.fragment.ReceivedFragment;
import com.example.doan.View.fragment.ShippedFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter   {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return  new OrderFragment();
            case 1 :
                return  new OrderedFragment();
            case 2 :
                return  new ShippedFragment();
            case 4 :
                return  new ReceivedFragment();
        }
        return  new OrderFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position) {
        // Return the title for the given position
        switch (position) {
            case 0:
                return "Order";
            case 1:
                return "Ordered";
            case 2:
                return "Shipped";
            case 3 :
                return "Received";
            default:
                return "Order";
        }
    }

}
