package com.example.doan.View.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Model.Image;
import com.example.doan.Model.Laptop;
import com.example.doan.R;
import com.example.doan.View.activity.LaptopDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class CoverLaptopAdapter extends RecyclerView.Adapter<CoverLaptopAdapter.ViewHolder> {
    private Context ctx;
    private ArrayList<Laptop> coverLaptopList;

    public CoverLaptopAdapter(Context ctx, ArrayList<Laptop> coverLaptopList) {
        this.ctx = ctx;
        this.coverLaptopList = coverLaptopList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View LaptopView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_single, parent, false);
        return new ViewHolder(LaptopView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Laptop laptop = coverLaptopList.get(position);
        List<Image> images = laptop.getImages();
        String path = images.get(0).getPath();
        holder.laptopImage_coverPage.setImageURI(Uri.parse(path));


    }

    @Override
    public int getItemCount() {
        return coverLaptopList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView laptopImage_coverPage;
        TextView laptopNoteCover;
        Button laptopCheck_coverPage;

        public ViewHolder(View itemView) {
            super(itemView);
            laptopImage_coverPage = itemView.findViewById(R.id.laptopImage_coverPage);
            laptopNoteCover = itemView.findViewById(R.id.laptopNoteCover);
            laptopCheck_coverPage = itemView.findViewById(R.id.laptopCheck_coverPage);
        }
    }

    private void goDetailsPage(int position) {
        Intent intent = new Intent(ctx, LaptopDetailsActivity.class);
        intent.putExtra("LaptopIndex", position);
        intent.putExtra("LaptopFrom", "Cover");
        ctx.startActivity(intent);
    }
}
