package com.example.doan.View.adapter;



import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Model.CartUpdate;
import com.example.doan.Model.Image;
import com.example.doan.Model.Laptop;
import com.example.doan.R;
import com.example.doan.ViewModel.CartViewModel;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context ;
    private List<Laptop> listLaptop ;
    private CartViewModel cartViewModel ;

    public OrderAdapter(Context context, List<Laptop> listLaptop ) {
        this.context = context;
        this.listLaptop = listLaptop;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }



    @Override
    public int getItemCount() {
        return listLaptop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage ;
        TextView orderCode;
        TextView orderQty;
        TextView orderTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.imageOrder);
            orderCode = itemView.findViewById(R.id.tvCodeOrder);
            orderQty = itemView.findViewById(R.id.tvQty);
            orderTotal = itemView.findViewById(R.id.tvTotal);

        }
    }
    private void delItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Laptop laptop = listLaptop.get(position);
        Integer laptopId = laptop.getId();

// Thiết lập tiêu đề
        builder.setTitle("Notification");

// Thiết lập nội dung
        builder.setMessage("Do you really want to update?");

// Thiết lập nút đồng ý
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CartViewModel.removeItem(laptopId);
            }
        });

// Thiết lập nút từ chối
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý sự kiện khi người dùng nhấn nút Từ chối
                dialog.dismiss(); // Đóng dialog
            }
        });

// Tạo và hiển thị AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void updateItem(int position, int qty) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);



// Thiết lập tiêu đề
        builder.setTitle("Notification");

// Thiết lập nội dung
        builder.setMessage("Do you really want to update ?");

// Thiết lập nút đồng ý
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Laptop laptop = listLaptop.get(position);
                Integer laptopId = laptop.getId();
                CartViewModel.updateItem(laptop.getId(), qty);
            }
        });

// Thiết lập nút từ chối
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý sự kiện khi người dùng nhấn nút Từ chối
                dialog.dismiss(); // Đóng dialog
            }
        });

// Tạo và hiển thị AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}

