package com.example.booksmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        final EditText entpass=findViewById (R.id.entpass) ;
        final EditText entphone =findViewById (R.id.entphone);
        Button btnlogin =findViewById(R.id.button3);
        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_user = database.getReference ("User");
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(entphone.getText().toString()).exists()) {
                            User user = dataSnapshot.child(entphone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(entpass.getText().toString())){
                                Toast.makeText(signin.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                                Intent loginsuccess=new Intent(signin.this,Mainaftersignin.class);
                                startActivity(loginsuccess);}
                            else
                                Toast.makeText(signin.this, "Log In Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(signin.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
            });

            }
        });
    }
}
