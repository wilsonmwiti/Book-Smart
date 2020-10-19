package com.example.booksmart;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class Genre_viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textView;
    public ImageView imageView;
    private ItemClickListener itemClickListener;
    CardView cardView;
    public Genre_viewholder(@NonNull View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.genre_name);
        imageView=(ImageView)itemView.findViewById(R.id.genre_image);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
