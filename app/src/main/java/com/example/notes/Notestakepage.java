package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Notestakepage extends AppCompatActivity {
    ImageView aroimage;
    ImageView tcross;
    ImageView mcross;
    EditText tedittext;
    EditText medittext;
    Button savebutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_notestakepage);
        aroimage=(ImageView) findViewById(R.id.bckarrow);
        aroimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tcross=(ImageView) findViewById(R.id.ettcross);
        mcross=(ImageView) findViewById(R.id.etmcross);
        tcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tedittext.setText("");
            }
        });
        mcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medittext.setText("");
            }
        });
        savebutton=(Button) findViewById(R.id.btnsave);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tedittext=(EditText)findViewById(R.id.etitle);
                medittext=(EditText)findViewById(R.id.emessage);

                String title=tedittext.getText().toString();
                String message=medittext.getText().toString();
                Intent intent= new Intent();
                intent.putExtra("title",title);
                intent.putExtra("message",message);
                setResult(2,intent);
                if(title==null||title.isEmpty()){
                    tedittext.setError("Title is required");
                }
                else{
                    finish();
                }
            }
        });





    }

}