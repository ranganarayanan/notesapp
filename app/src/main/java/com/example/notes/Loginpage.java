package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Loginpage extends AppCompatActivity {
    CardView cardViewsignup;
    Button signin;
    ImageView cross;
    ImageView eyeimg;
    EditText editTextmail;
    EditText editTextpassword;
    boolean passwordvisible=false;
    ImageView lockimage;
    CardView cardgoogle;
    FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=20;

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
        signin=(Button) findViewById(R.id.btnsignin);
       signin.setOnClickListener(v -> signinuser());

    }
    void signinuser(){

        String email=editTextmail.getText().toString();
        String password=editTextpassword.getText().toString();

        boolean isValidated =validateData(email,password);
        if (!isValidated){
            return;
        }
        signinAccountInFirebase(email,password);
    }
    void signinAccountInFirebase(String email,String password){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()){
                  if (firebaseAuth.getCurrentUser().isEmailVerified()){
                      startActivity(new Intent(Loginpage.this, Notespage.class));
                      finish();
                  }
                  else{
                      Utility.showToast(Loginpage.this,"E-Mail is not verified,Please verify your E=Mail.");
                  }
              }
              else{
                  Utility.showToast(Loginpage.this,task.getException().getLocalizedMessage());

              }
            }
        });
        cardgoogle=(CardView) findViewById(R.id.signinwitngoogle);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("874841605155-o94e7irl02in9fmp5e8o23vr8u66kt6j.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(Loginpage.this,gso);
        cardgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();

            }
        });

    }

    private void googleSignIn() {
        Intent intent= mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account= task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());

            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuth(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            HashMap<String,Object> map= new HashMap<>();
                            map.put("id",user.getUid());
                            map.put("name",user.getDisplayName());
                            map.put("profile",user.getPhotoUrl().toString());
                            database.getReference().child("user").child(user.getUid()).setValue(map);
                            startActivity(new Intent(Loginpage.this,Notespage.class));
                        }
                        else{
                            Toast.makeText(Loginpage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    boolean validateData(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextmail.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            editTextpassword.setError("Password length is invalid");
            return false;
        }
        return true;

    }
}