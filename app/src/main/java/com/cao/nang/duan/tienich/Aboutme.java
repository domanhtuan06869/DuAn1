package com.cao.nang.duan.tienich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cao.nang.duan.R;

public class Aboutme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_aboutme);
    }
}
