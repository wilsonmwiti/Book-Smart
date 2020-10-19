package com.example.booksmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import Model.Category;

public class search extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category,Genre_viewholder> genreAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_search);

        database=FirebaseDatabase.getInstance();
        category=database.getReference("Category");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerGenre);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadmenu();


        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Mainaftersignin.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_favorites:
                        startActivity(new Intent(getApplicationContext(), wishlist.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_search:
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

    public void loadmenu(){
        genreAdapter =
                new FirebaseRecyclerAdapter<Category,Genre_viewholder>(Category.class,R.layout.category_card,Genre_viewholder.class,category) {

                    @Override
                    protected void populateViewHolder(@NonNull Genre_viewholder holder, @NonNull Category model,  int position) {
                        holder.textView.setText(model.getCatname());
                        Picasso.with(getBaseContext()).load(model.getCardimg()).into(holder.imageView);
                        Category clickitem=model;
                        holder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {
                                Intent booklist=new Intent(search.this,Booklist.class);
                                booklist.putExtra("GenreID",genreAdapter.getRef(position).getKey());
                                startActivity(booklist);
                            }
                        });
                    }
                };
        recyclerView.setAdapter(genreAdapter);

    }

}
