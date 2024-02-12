package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Notespage extends AppCompatActivity {
    ImageView plusimg;
    ImageView crosssrch;
    EditText editTextsrch;
    RecyclerView rView;
    ImageView logoutimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notespage);
        plusimg = (ImageView) findViewById(R.id.btnplus);
        plusimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notespage.this, Notestakepage.class));
            }
        });
        crosssrch = (ImageView) findViewById(R.id.crosssearch);
        editTextsrch = (EditText) findViewById(R.id.etsearch);
        crosssrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextsrch.setText("");
            }
        });
        rView=(RecyclerView) findViewById(R.id.recyclerview);
        rView.setLayoutManager(new LinearLayoutManager(this));





    }

}

