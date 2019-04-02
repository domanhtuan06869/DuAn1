package com.cao.nang.duan.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private ImageView imgView;
    private TextView tvmess;
    private RecyclerView rcchat;
    private List<Tomessage>messageList=new ArrayList<>();
    messageAdapter messageadt;
    private EditText edtcmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        imgView = (ImageView) findViewById(R.id.imgView);
        tvmess = (TextView) findViewById(R.id.tvmess);
        rcchat = (RecyclerView) findViewById(R.id.rcchat);
        Intent in =getIntent();
        Bundle b=in.getExtras();
        String title=b.getString("title");
        String urlimg=b.getString("img");
        Picasso.get().load(urlimg).into(imgView);
        tvmess.setText(title);
 this.nhan();
    }
    public void nhan(){
        Intent intent =getIntent();
        Bundle bd=intent.getExtras();
        String title=bd.getString("title");

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(title);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    Tomessage tomessage=dataSnapshot1.getValue(Tomessage.class);
                    messageList.add(tomessage);
                }
                messageadt=new messageAdapter(Main3Activity.this,messageList);
                rcchat.setLayoutManager(new LinearLayoutManager(Main3Activity.this));
                messageadt.notifyDataSetChanged();
                rcchat.smoothScrollToPosition(messageList.size()-1);
                rcchat.setAdapter(messageadt);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void CMT(View view) {
        messageList.clear();
        Intent intent =getIntent();
        Bundle bd=intent.getExtras();
        String title=bd.getString("title");
        edtcmt = (EditText) findViewById(R.id.edtcmt);
        String chatchit =edtcmt.getText().toString().trim();
        Tomessage message=new Tomessage(chatchit);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(title);
        String id=reference.push().getKey();
        reference.child(id).setValue(message);
        edtcmt.setText("");

    }
}
