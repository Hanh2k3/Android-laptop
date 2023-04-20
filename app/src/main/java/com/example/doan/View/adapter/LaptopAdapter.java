package com.example.doan.View.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Model.Image;
import com.example.doan.Model.Laptop;
import com.example.doan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopHolder> {


    private List<Laptop> listLaptops;
    private Context context ;

    public LaptopAdapter(List<Laptop> listLaptops, Context context) {
       this.listLaptops = listLaptops;
       this.context = context;
    }

    class LaptopHolder extends RecyclerView.ViewHolder {

        ImageView laptopImage ;
        public LaptopHolder(View itemView) {
            super(itemView);
            laptopImage = itemView.findViewById(R.id.productImage_singleProduct);
        }

    }
    @Override
    public LaptopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View LaptopView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product, parent, false);
        return new LaptopHolder(LaptopView);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopHolder holder, int position) {
        Laptop laptop = listLaptops.get(position);
        List<Image> images = laptop.getImages();
        String path = images.get(0).getPath();
        Picasso.get().load(path).into(holder.laptopImage);
       // holder.laptopImage.setImageURI(Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_P6RQ5FtcCdFJKHQscsJrxfoNFqvRRByv1q8VH5Dv&s"));
//        holder.discount.setVisibility(View.VISIBLE);
      //  holder.tvDiscount.setText("New")
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToDetail(position);
            }
        });
    }

    private void goToDetail(int position) {
    }

    @Override
    public int getItemCount() {
        return listLaptops.size();
    }
}
