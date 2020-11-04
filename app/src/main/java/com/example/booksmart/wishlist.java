package com.example.booksmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import Model.Book;

public class wishlist extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference booklistdat;
    RecyclerView bookrecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Book,Booklist_viewholder> bookAdapter;
    String bid="";
    String bid2="";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_favorites);
        database=FirebaseDatabase.getInstance();
        booklistdat=database.getReference("Book");

        bookrecyclerView=(RecyclerView)findViewById(R.id.wishlistrv);
        bookrecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        bookrecyclerView.setLayoutManager(layoutManager);
        bid="Dan Brown";
        bid2="016";
        loadListBook(bid,bid2);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Mainaftersignin.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_favorites:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(), search.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    private void loadListBook(String bid,String bid2) {
        bookAdapter=new FirebaseRecyclerAdapter<Book, Booklist_viewholder>(Book.class,
                R.layout.book_card,
                Booklist_viewholder.class,
                booklistdat.orderByChild("bauth").equalTo(bid)
        ) {
            @Override
            protected void populateViewHolder(Booklist_viewholder booklist_viewholder, Book book, int i) {
                booklist_viewholder.bookname.setText(book.getBname());
                booklist_viewholder.bookrating.setText("Rating: " + book.getBrating());
                booklist_viewholder.bookauthor.setText("By " + book.getBauth());
                Picasso.with(getBaseContext()).load(book.getBimg()).into(booklist_viewholder.bookimageView);

                final Book local=book;
                booklist_viewholder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //
                    }
                });
            }
        };
        bookrecyclerView.setAdapter(bookAdapter);
    }
}