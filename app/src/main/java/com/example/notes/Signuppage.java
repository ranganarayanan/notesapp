package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Signuppage extends AppCompatActivity {
    ImageView aroimage;
    ImageView crossname;
    ImageView crossemail;
    ImageView crossconfirmpassword;
    EditText editTextusername;
    EditText editTextemail;
    EditText editTextconpassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        aroimage=(ImageView) findViewById(R.id.bckarrow);
        aroimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editTextusername=(EditText) findViewById(R.id.etusername);
        editTextemail=(EditText) findViewById(R.id.etmail);
        editTextconpassword=(EditText) findViewById(R.id.etconpassword);
        crossname=(ImageView) findViewById(R.id.crossusername);
        crossemail=(ImageView) findViewById(R.id.crossmail);
        crossconfirmpassword=(ImageView)findViewById(R.id.crossconpassword);
        crossname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextusername.setText("");
            }
        });
        crossemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextemail.setText("");
            }
        });
        crossconfirmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextconpassword.setText("");
            }
        });

    }
}