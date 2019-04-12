package com.cao.nang.duan.chatgroup;

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
import com.cao.nang.duan.chatgroup.adaptergroup.messageAdapter;
import com.cao.nang.duan.chatgroup.modelgroup.Tomessage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CommentGroup_actitvity extends AppCompatActivity {
    private ImageView imgView;
    private TextView tvtitlecmt;
    private RecyclerView rcchat;
    private List<Tomessage> messageList = new ArrayList<>();
    messageAdapter messageadt;
    private EditText edtcmt;
    private String time24;
    private String chatchit;
    private String  title1;
    private TextView tvcontent;
    private TextView tvEmailcmt;
    private TextView datecomment;
    private String email;
    private static  final String title_="title";
    private static  final String urlimg_="img";
    private static  final String contents_="content";
    private static  final String date_="Date";
    private static  final String email_="Email";
    private static  final String emaill_="Emaill";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_comment_activity);
       imgView = findViewById(R.id.imgView);
        tvtitlecmt = findViewById(R.id.tvtitlecmt);
        rcchat = findViewById(R.id.rcchat);
        tvcontent = findViewById(R.id.tvcontentcmt);
        tvEmailcmt = findViewById(R.id.tvEmailcmt);
        datecomment = findViewById(R.id.datecomment);


        Intent in = getIntent();
        Bundle b = in.getExtras();
      String  title = b.getString(title_);
        String urlimg = b.getString(urlimg_);
        String contents=b.getString(contents_);
        String date =b.getString(date_);
         email =b.getString(email_);
         String emaill=b.getString(emaill_);
        Picasso.get().load(urlimg).into(imgView);
        tvtitlecmt.setText(title);
        tvcontent.setText(contents);
        tvEmailcmt.setText(emaill);
        datecomment.setText(date);
        this.nhan();
    }

    public void nhan() {
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
       try {


          title1 = bd.getString("title");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(title1);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Tomessage tomessage = dataSnapshot1.getValue(Tomessage.class);
                    messageList.add(tomessage);
                }
                messageadt = new messageAdapter(CommentGroup_actitvity.this, messageList);

                rcchat.setLayoutManager(new LinearLayoutManager(CommentGroup_actitvity.this));
                messageadt.notifyDataSetChanged();
               rcchat.smoothScrollToPosition(messageList.size() - 1);
                rcchat.setAdapter(messageadt);
                messageList.remove(0);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       }catch (Exception e){}
    }

    public void CMT(View view) {

            messageList.clear();


                Intent intent = getIntent();
                Bundle bd = intent.getExtras();

                edtcmt = (EditText) findViewById(R.id.edtcmt);

                int day, month, year;
                GregorianCalendar datetime = new GregorianCalendar();
                String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());
                SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
                SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
                try {
                    time24 = outFormat.format(inFormat.parse(now));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                day = datetime.get(Calendar.DAY_OF_MONTH);
                month = datetime.get(Calendar.MONTH);
                year = datetime.get(Calendar.YEAR);
                String ngaythang = "lúc " + time24 + " ," + day + "/" + month + "/" + year;



                    chatchit = edtcmt.getText().toString().trim();
                    Tomessage message = new Tomessage(chatchit, ngaythang,email);
                    String title = bd.getString("title");

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(title);

                    String id = reference.push().getKey();
                    reference.child(id).setValue(message);

                edtcmt.setText("");

    }

    public void back(View view) {
        finish();
    }
}
