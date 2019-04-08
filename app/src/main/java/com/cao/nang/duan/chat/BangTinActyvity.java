package com.cao.nang.duan.chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.base.Base;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BangTinActyvity extends Base {

    private List<ImageUploadInfo> imageUploadInfos=new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;

    ImgAdapter adapter;
    private RecyclerView rclist;

    public  String email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantin);
        rclist = (RecyclerView) findViewById(R.id.rcStatus);
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        email=b.getString("Email");
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        adapter=new ImgAdapter(BangTinActyvity.this,imageUploadInfos,email);
        rclist.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(BangTinActyvity.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rclist.setLayoutManager(mLayoutManager);
        rclist.setAdapter(adapter);
        rclist.setItemAnimator(new DefaultItemAnimator());

        this.  getdata();

    }


    private  void getdata(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("All_Image_Uploads_Database");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageUploadInfos.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ImageUploadInfo info=dataSnapshot1.getValue(ImageUploadInfo.class);
                    imageUploadInfos.add(info);
                    adapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);


                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onStop() {
        super.onStop();
        mShimmerViewContainer.stopShimmerAnimation();

    }


    public void DangBai(View view) {

      openDetailActivity(email);
    }
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(this,DangBaiActivity.class);
        i.putExtra("Email",details[0]);
        this.startActivity(i);
    }

}

