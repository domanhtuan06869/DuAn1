package com.cao.nang.duan.drugandhopistal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cao.nang.duan.R;

public class ScollDrug extends AppCompatActivity {
    private TextView tvNameDrug;
    private TextView tvGiathuoc;
    private TextView tvThanhphan;
    private TextView tvChidinh;
    private TextView tvChongcd;
    private TextView tvCachDung;
    private TextView tvTacdungPhu;
    private TextView tvChuY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoll_drug);
         this.init();
         this.getIntentDrug();




    }
    public void init(){

        tvNameDrug = (TextView) findViewById(R.id.tvNameDrug);
        tvGiathuoc = (TextView) findViewById(R.id.tvGiathuoc);
        tvThanhphan = (TextView) findViewById(R.id.tvThanhphan);
        tvChidinh = (TextView) findViewById(R.id.tvChidinh);
        tvChongcd = (TextView) findViewById(R.id.tvChongcd);
        tvCachDung = (TextView) findViewById(R.id.tvCachDung);
        tvTacdungPhu = (TextView) findViewById(R.id.tvTacdungPhu);
        tvChuY = (TextView) findViewById(R.id.tvChuY);
    }
    //lấy dữ liệu đối tượng drug set tv
    public void getIntentDrug(){
        Intent intent=getIntent();
        Bundle b= intent.getExtras();
        String namedrug=b.getString("Name_Drug");
        String gia=b.getString("Price");
        String thanhphan=b.getString("Ingredient");
        String chidinh=b.getString("Assign");
        String chongchidinh=b.getString("Contraindicated");
        String cachdung=b.getString("User");
        String tacdungphu=b.getString("Effect");
        String chuy=b.getString("Attention");
        tvNameDrug.setText(namedrug);
        tvGiathuoc.setText(gia);
        tvThanhphan.setText(thanhphan);
        tvChidinh.setText(chidinh);
        tvChongcd.setText(chongchidinh);
        tvCachDung.setText(cachdung);
        tvTacdungPhu.setText(tacdungphu);
        tvChuY.setText(chuy);
    }
}
