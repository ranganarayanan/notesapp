package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
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
    EditText editTextpassword;
    ImageView lockimage;
    ImageView eyeimg;
    boolean passwordvisible=false;

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
        eyeimg=(ImageView) findViewById(R.id.clseye);
        lockimage=(ImageView) findViewById(R.id.clslock);
        editTextpassword=(EditText) findViewById(R.id.etpassword);
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