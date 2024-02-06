package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentuser= FirebaseAuth.getInstance().getCurrentUser();
                if(currentuser==null){
                    startActivity(new Intent(MainActivity.this, Loginpage.class));
                }else{
                    startActivity(new Intent(MainActivity.this, Notespage.class));
                     }
                finish();
            }
        },2000);
    }
}