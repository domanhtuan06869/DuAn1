package com.cao.nang.duan.drugandhopistal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cao.nang.duan.R;

public class Sick_activity extends AppCompatActivity {
    private TextView tvNameSick;
    private TextView tvSocuu;
    private static  final String name_="name";
    private static  final String cach_="cachchua";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_sick_activity);

        tvNameSick = (TextView) findViewById(R.id.tvNameSick);
        tvSocuu = (TextView) findViewById(R.id.tvSocuu);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString(name_);
        String cach=bundle.getString(cach_);
        tvNameSick.setText(name);
        tvSocuu.setText(cach);

    }
}
