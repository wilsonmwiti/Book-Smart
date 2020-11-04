package com.example.booksmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.Book;
import Model.Category;
import Model.Wishlist;
import Model.Wishlistdb;

public class Book_Details extends AppCompatActivity {

    TextView bauth, bdesc, bgenreid, blink, bname, brating, brevno;
    ImageView bookimg;
    Button addtocart;
    Button alreadyread;

    String bookid = "";
    FirebaseDatabase bookdet;
    DatabaseReference book;
    DatabaseReference genre;
    Book currentbook;
    Category currentgenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__details);

        bookdet = FirebaseDatabase.getInstance();
        book = bookdet.getReference("Book");
        genre = bookdet.getReference("Category");

        addtocart = (Button) findViewById(R.id.addwishlist);
        alreadyread = (Button) findViewById(R.id.alreadyread);
        bauth = (TextView) findViewById(R.id.bookdesauthor);
        bdesc = (TextView) findViewById(R.id.bookdesdes);
        bgenreid = (TextView) findViewById(R.id.bookdesgenre);
        blink = (TextView) findViewById(R.id.bookdesbuylink);
        bname = (TextView) findViewById(R.id.bookdesname);
        brating = (TextView) findViewById(R.id.bookdesrating);
        brevno = (TextView) findViewById(R.id.bookdesnorating);
        bookimg = (ImageView) findViewById(R.id.bookdesimg);


        if (getIntent() != null) {
            bookid = getIntent().getStringExtra("BookID");
        }
        if (!bookid.isEmpty()) {
            getBookDetails(bookid);
        }


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Book_Details.this,"Added to wishlist",Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void getBookDetails ( final String bookid){
            book.child(bookid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    currentbook = snapshot.getValue(Book.class);
                    Picasso.with(getBaseContext()).load(currentbook.getBimg()).into(bookimg);
                    String bgid = currentbook.getBgenreid();
                    genre.child(bgid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            currentgenre = snapshot.getValue(Category.class);
                            bgenreid.setText("Genre: " + currentgenre.getCatname());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    bname.setText(currentbook.getBname());
                    bauth.setText("Author: " + currentbook.getBauth());
                    brating.setText("Average Rating: " + currentbook.getBrating());
                    bdesc.setText("Description: \n" + currentbook.getBdesc());
                    blink.setText(currentbook.getBlink());
                    brevno.setText("Number of reviews: " + currentbook.getBrevno());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
