package com.example.project2.buatRecycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;

public class viewHolder extends RecyclerView.ViewHolder {

    ImageView imagev;
    TextView productName;
    TextView productPrice;
    TextView productRating;


    public viewHolder(@NonNull View itemView) {
        super(itemView);
        imagev = itemView.findViewById(R.id.imageV);
        productName = itemView.findViewById(R.id.nFurniture);
        productPrice = itemView.findViewById(R.id.pFurniture);
        productRating = itemView.findViewById(R.id.rFurniture);
    }
}
