package com.cao.nang.duan.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Base extends AppCompatActivity {
    public void classintent(Class taget){

        Intent intent= new Intent(this,taget);
        startActivity(intent);

    }
    public void showToast(String show){
        Toast.makeText(this,show,Toast.LENGTH_LONG).show();
    }
}
