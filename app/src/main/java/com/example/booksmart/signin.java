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

public class signin extends AppCompatActivity implements View.OnClickListener{
    private EditText entpass;
    private EditText entemail;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        entpass=findViewById (R.id.entpass) ;
        entemail =findViewById (R.id.email);
        Button btnlogin =findViewById(R.id.button3);
        firebaseAuth=FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(this);

    }
    public void onClick(View view){
        signin();
    }
    public void signin(){
        final String email=entemail.getText().toString().trim();
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
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(signin.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(signin.this,Mainaftersignin.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(signin.this,"Failed to log in. Please check your credentials.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
