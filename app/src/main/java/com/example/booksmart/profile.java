package com.example.booksmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import Model.User;

public class profile extends AppCompatActivity {
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_profile);

        TextView name=(TextView)findViewById(R.id.editTextName);
        logout=(Button)findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(profile.this,"Logout successful",Toast.LENGTH_SHORT).show();
                Intent signout=new Intent(profile.this,MainActivity.class);
                signout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signout);
            }
        });

        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userid=user.getUid();

        final TextView profname=(TextView)findViewById(R.id.name);
        final TextView profphone=(TextView)findViewById(R.id.phone);
        final TextView profemail=(TextView)findViewById(R.id.email);

        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile=snapshot.getValue(User.class);
                if(userprofile!=null){
                    String name=userprofile.name;
                    String phone=userprofile.phone;
                    String mail=userprofile.email;

                    profname.setText(name);
                    profphone.setText("  "+phone);
                    profemail.setText("  "+mail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

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
                        startActivity(new Intent(getApplicationContext(), search.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_profile:
                        return true;
                }
                return false;
            }
        });

    }
}