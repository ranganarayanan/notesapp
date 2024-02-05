package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Loginpage extends AppCompatActivity {
    CardView cardViewsignup;
    Button signin;
    ImageView cross;
    ImageView eyeimg;
    EditText editTextmail;
    EditText editTextpassword;
    boolean passwordvisible=false;
    ImageView lockimage;

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
        eyeimg=(ImageView) findViewById(R.id.clseye);
        lockimage=(ImageView) findViewById(R.id.clslock);
        eyeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordvisible){
                 eyeimg.setImageDrawable(getDrawable(R.drawable.eyecloseb));
                    lockimage.setImageDrawable(getDrawable(R.drawable.lockb));
                 passwordvisible=false;
                }
                else{
                    eyeimg.setImageDrawable(getDrawable(R.drawable.eyeopenb));
                    lockimage.setImageDrawable(getDrawable(R.drawable.lockopenb));
                    passwordvisible=true;
                }
                int inputType = passwordvisible ?
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                        editTextpassword.setInputType(inputType);
            }
        });

    }
}