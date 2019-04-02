package com.cao.nang.duan.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cao.nang.duan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView rcview;
    private List<All_Image_Uploads_Database> imageUploadInfos=new ArrayList<>();
    ImgAdapter adapter;
    private EditText tvemail;
    private EditText tvpass;
    private Button dangnhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvemail = (EditText) findViewById(R.id.tvemail);
        tvpass = (EditText) findViewById(R.id.tvpass);
        dangnhap = (Button) findViewById(R.id.dangnhap);




    }

    private  void getdataf(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("All_Image_Uploads_Database");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    All_Image_Uploads_Database info=dataSnapshot1.getValue(All_Image_Uploads_Database.class);
                    imageUploadInfos.add(info);
                }
             //   adapter=new ImgAdapter(Main2Activity.this,imageUploadInfos);
                rcview.setLayoutManager(new LinearLayoutManager(Main2Activity.this));

                rcview.setAdapter(adapter);
                rcview.post(new Runnable() {
                    @Override
                    public void run() {
                        rcview.smoothScrollToPosition(adapter.getItemCount() - 1);
                    }
                });



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void dangnhap(View view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(tvemail.getText().toString(),tvpass.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(Main2Activity.this,"k thanh cong",Toast.LENGTH_LONG).show();
                            Log.e("ero",task.getException().toString());
                        }
                        else{
                            Toast.makeText(Main2Activity.this,"thanh cong",Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    public void dangki(View view) {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(tvemail.getText().toString(),tvpass.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Main2Activity.this,"saitk mk",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(Main2Activity.this,"thanh cong",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


    public void resest(View view) {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(tvemail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Main2Activity.this,"mk da duoc gui vao email",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(Main2Activity.this,"email ko dung",Toast.LENGTH_LONG).show();

                        }

                    }
                });
    }
}
