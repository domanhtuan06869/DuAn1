package com.cao.nang.duan.drugandhopistal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cao.nang.duan.R;

public class ScollDrug_activity extends AppCompatActivity {
    private TextView tvNameDrug;
    private TextView tvGiathuoc;
    private TextView tvThanhphan;
    private TextView tvChidinh;
    private TextView tvChongcd;
    private TextView tvCachDung;
    private TextView tvTacdungPhu;
    private TextView tvChuY;
    private  final static String namedrug_="Name_Drug";
    private  final static  String gia_="Price";
    private  final static  String thanhphan_="Ingredient";
    private  final static  String chidinh_="Assign";
    private  final static  String chongchidinh_="Contraindicated";
    private  final static  String cachdung_="User";
    private  final static  String tacdungphu_="Effect";
    private  final static  String chuy_="Attention";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        String namedrug=b.getString(namedrug_);
        String gia=b.getString(gia_);
        String thanhphan=b.getString(thanhphan_);
        String chidinh=b.getString(chidinh_);
        String chongchidinh=b.getString(chongchidinh_);
        String cachdung=b.getString(cachdung_);
        String tacdungphu=b.getString(tacdungphu_);
        String chuy=b.getString(chuy_);
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
