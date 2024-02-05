package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Notespage extends AppCompatActivity {
    ImageView plusimg;
    ImageView crosssrch;
    EditText editTextsrch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notespage);
        plusimg=(ImageView) findViewById(R.id.btnplus);
        plusimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notespage.this,Notestakepage.class));
            }
        });

    }
}