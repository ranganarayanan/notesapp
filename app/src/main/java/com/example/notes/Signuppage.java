package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    Button signupbutton;
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
        signupbutton=(Button) findViewById(R.id.btnsignup);
        signupbutton.setOnClickListener(v -> creatAccount());

    }
    void creatAccount(){
        String name=editTextusername.getText().toString();
        String email=editTextemail.getText().toString();
        String password=editTextpassword.getText().toString();
        String confirmpassword=editTextconpassword.getText().toString();
        boolean isValidated =validateData(email,password,confirmpassword);
        if (!isValidated){
            return;
        }
        createAccountInFirebase(email,password);


    }
    void createAccountInFirebase(String email,String password){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signuppage.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signuppage.this, "Succesfully create your account,Check E=Mail to verify", Toast.LENGTH_SHORT).show();
                            firebaseAuth.getCurrentUser().sendEmailVerification();


                        }
                        else{
                            Toast.makeText(Signuppage.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }

        );


    }
    boolean validateData(String email,String password,String confirmpassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            editTextpassword.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmpassword)){
            editTextconpassword.setError("Password is not equal to Confirm password");
            return false;
        }
        return true;
    }
}