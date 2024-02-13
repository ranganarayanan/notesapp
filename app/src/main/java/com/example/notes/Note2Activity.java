package com.example.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Note2Activity extends AppCompatActivity implements ItemClickListener{
    ImageView plusimg;
    ImageView crosssrch;
    EditText editTextsrch;
    RecyclerView rView;
    ImageView logoutimg;
    LinearLayoutManager layoutManager;
    Button logout;
    FirebaseAuth mAuth;
    ImageView delete;
    List<NoteModel> noteModelList=new ArrayList<>();
    List<NoteModel> filterList=new ArrayList<>();
    NotesAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_note2);
        plusimg = (ImageView) findViewById(R.id.btnplus);
        plusimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Note2Activity.this, Notestakepage.class),2);
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
        editTextsrch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    filterList.clear();
                    for(NoteModel model: noteModelList){
                        if(model.getTitle().contains(s.toString())){
                            filterList.add(model);
                        }
                    }
                }
                else{
                    filterList.addAll(noteModelList);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        logoutimg=(ImageView) findViewById(R.id.navbtn);
        logoutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBox();
            }
        });
        initRecylerView();
    }
    private void initRecylerView() {
        rView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rView.setLayoutManager(layoutManager);
        adapter=new NotesAdapter(filterList,this);
        rView.setAdapter(adapter);
    }
    private void showDialogBox(){
        Dialog d =new Dialog(this);
        d.setContentView(R.layout.logout);
        d.setCancelable(true);
        logout=(Button)d.findViewById(R.id.logoutbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              startActivity(new Intent(Notespage.this,Loginpage.class));
                signout();
            }
        });


        d.show();
    }
    private  void signout(){
        mAuth.signOut();
        Intent intent = new Intent(Note2Activity.this, Loginpage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String ntitle = data.getStringExtra("title");
            String nmessage = data.getStringExtra("message");
            SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String date=format.format(new Date());
            String time=date.split(" ")[1];
            int hour=Integer.parseInt(time.substring(0,2));
            time=time.substring(3);
            String last=" AM";
            if(hour>12){
                last=" PM";
                time=(hour-12)+":"+time+last;
            }
            else{
                if(hour==0)
                    hour=12;
                time=hour+":"+time+last;
            }
            noteModelList.add(new NoteModel(ntitle,nmessage,date,date.split(" ")[0]+" "+time));
            filterList.clear();
            filterList.addAll(noteModelList);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void deleteItem(int position) {
        String id=filterList.get(position).getId();
        for(int i=0;i<noteModelList.size();i++)
            if(noteModelList.get(i).getId().equals(id)){
                noteModelList.remove(i);
                break;
            }
        filterList.clear();
        filterList.addAll(noteModelList);
        adapter.notifyDataSetChanged();
    }
}