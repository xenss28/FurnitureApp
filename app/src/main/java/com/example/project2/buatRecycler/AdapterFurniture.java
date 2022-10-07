package com.example.project2.buatRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project2.Home;
import com.example.project2.R;
import com.example.project2.helper.FurnitureHelper;
import com.example.project2.models.Furniture;

import java.util.Vector;

public class AdapterFurniture extends RecyclerView.Adapter <viewHolder> {
    Context context;
    FurnitureHelper furnitureHelper;
    Vector<Furniture> furnitures;
    AdapterView.OnItemClickListener clickItem;

    public AdapterFurniture (Context context, Vector<Furniture> listFurniture, AdapterView.OnItemClickListener listener){
        this.furnitures = listFurniture;
        this.context = context;
        this.clickItem = listener;

        furnitureHelper = new FurnitureHelper(context);

        if (furnitures.isEmpty()){
            Home
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.furnitureviewholder, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Furniture furniture = furnitures.get(position);
        Glide.with(context)
                .load(furniture.getProductimage()).into(holder.imagev);
        holder.productName.setText(furnitures.get(position).getProductname());
        String rating = furnitures.get(position).getProductrating().toString();
        holder.productRating.setText(rating);
        String price = furnitures.get(position).getProductprice().toString();
        holder.productPrice.setText(price);

        holder.itemView.setOnClickListener(view -> {
            clickItem.onItemClick(furnitures.get(position), (position));
        });
    }


    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    public interface OnItemClickListener {
        void  onItemClick(Furniture furniture, int position);
    }


}
