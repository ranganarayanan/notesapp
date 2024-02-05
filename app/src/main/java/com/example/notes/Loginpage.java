package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Loginpage extends AppCompatActivity {
    CardView cardViewsignup;
    Button signin;
    ImageView cross;
    EditText editTextmail;
    EditText editTextpassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        cardViewsignup=(CardView) findViewById(R.id.cardsignup);
        cardViewsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginpage.this, Signuppage.class));
            }
        });
        signin=(Button) findViewById(R.id.btnsignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginpage.this,Notespage.class));
            }
        });
        editTextmail=(EditText)findViewById(R.id.etmail);
        editTextpassword=(EditText)findViewById(R.id.etpassword);
        cross=(ImageView) findViewById(R.id.crossimage);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextmail.setText("");
            }
        });

    }
}