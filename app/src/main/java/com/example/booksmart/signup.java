package com.example.booksmart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;

public class signup extends AppCompatActivity implements View.OnClickListener{
    EditText entpass,entphone,entname,entemail;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        entemail=findViewById(R.id.editTextEmail);
        entpass = findViewById(R.id.editTextTextPersonName3);
        entphone = findViewById(R.id.editTextPhone);
        entname = findViewById(R.id.editTextName);
        Button btnsignup = findViewById(R.id.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        btnsignup.setOnClickListener(this);
    }

    public void onClick(View view){
        register();
    }

    public void register(){
        final String email=entemail.getText().toString().trim();
        final String phone=entphone.getText().toString().trim();
        final String name=entname.getText().toString().trim();
        final String pass=entpass.getText().toString().trim();

        if(email.isEmpty()){
            entemail.setError("Email is required");
            entemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            entemail.setError("Enter valid email");
            entemail.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            entphone.setError("Phone number is required");
            entphone.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            entphone.setError("Enter valid phone number");
            entphone.requestFocus();
            return;
        }
        if(name.isEmpty()){
            entname.setError("Name is required");
            entname.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            entpass.setError("Email is required");
            entpass.requestFocus();
            return;
        }
        if(pass.length()<6){
            entpass.setError("Password length should be a minimum of 6");
            entpass.requestFocus();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user=new User(email,phone,name);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(signup.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(signup.this,signin.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(signup.this,"User registration failed. Try again!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(signup.this,"User registration failed. Try again!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
