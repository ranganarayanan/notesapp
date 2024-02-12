package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        setContentView(R.layout.activity_notestakepage);
        aroimage=(ImageView) findViewById(R.id.bckarrow);
        aroimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tedittext=(EditText)findViewById(R.id.etitle);
        medittext=(EditText)findViewById(R.id.emessage);

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
                String title=tedittext.getText().toString();
                String message=medittext.getText().toString();

                if(title==null||title.isEmpty()){
                    tedittext.setError("Title is required");
                    return;
                }
            }
        });





    }

}