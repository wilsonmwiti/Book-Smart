package com.example.booksmart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class signup extends AppCompatActivity {
    EditText entpass,entphone,entname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        entpass=findViewById (R.id.editTextTextPersonName3) ;
        entphone =findViewById (R.id.editTextPhone);
        entname=findViewById(R.id.editTextName);
        Button btnsignup =findViewById(R.id.button4);

        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_user = database.getReference ("User");
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(entphone.getText().toString()).exists()) {
                            Toast.makeText(signup.this, "User Already Registered", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            User user = new User(entname.getText().toString(),entpass.getText().toString());
                            table_user.child(entphone.getText().toString()).setValue(user);
                            Toast.makeText(signup.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
