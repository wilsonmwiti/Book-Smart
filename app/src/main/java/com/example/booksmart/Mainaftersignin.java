package com.example.booksmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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

import Model.Book;

public class Mainaftersignin extends AppCompatActivity {

    FirebaseDatabase topdatabase;
    DatabaseReference topbooklist;
    RecyclerView topbookrecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Book,Booklist_viewholder> topbookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_after_sign_in);

        topdatabase=FirebaseDatabase.getInstance();
        topbooklist=topdatabase.getReference("Book");

        topbookrecyclerView=(RecyclerView)findViewById(R.id.toprecyclerview);
        topbookrecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        topbookrecyclerView.setLayoutManager(layoutManager);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);
        loadTopBooks();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_favorites:
                        startActivity(new Intent(getApplicationContext(), wishlist.class));
                        overridePendingTransition(0, 0);
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

    private void loadTopBooks() {
        topbookAdapter=new FirebaseRecyclerAdapter<Book, Booklist_viewholder>(Book.class,
                R.layout.book_card,
                Booklist_viewholder.class,
                topbooklist.orderByChild("brating").limitToLast(20)
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

                    }
                });
            }
        };
        topbookrecyclerView.setAdapter(topbookAdapter);
    }
}

