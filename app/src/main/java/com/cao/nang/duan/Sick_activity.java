package com.cao.nang.duan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sick_activity extends AppCompatActivity {
    private TextView tvNameSick;
    private TextView tvSocuu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_sick_activity);

        tvNameSick = (TextView) findViewById(R.id.tvNameSick);
        tvSocuu = (TextView) findViewById(R.id.tvSocuu);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString("name");
        String cach=bundle.getString("cachchua");
        tvNameSick.setText(name);
        tvSocuu.setText(cach);

    }
}
