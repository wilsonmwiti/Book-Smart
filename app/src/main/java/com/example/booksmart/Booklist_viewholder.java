package com.example.booksmart;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class Booklist_viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView bookname, bookauthor, bookrating;
    public ImageView bookimageView;
    private ItemClickListener itemClickListener;
    CardView cardView;
    public Booklist_viewholder(@NonNull View itemView) {
        super(itemView);
        bookname=(TextView)itemView.findViewById(R.id.bookname);
        bookauthor=(TextView)itemView.findViewById(R.id.bookauthor);
        bookrating=(TextView)itemView.findViewById(R.id.bookrating);
        bookimageView=(ImageView)itemView.findViewById(R.id.book_image);

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

