package com.cao.nang.duan.tienich;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.base.Base;

public class Tinhbeogay_activity extends Base {

    private EditText edtChieucao;
    private EditText edtCannang;
    private TextView tvketQuaDo;
    private TextView binhthuong;
    private TextView binhthuong1;
    private TextView thuacan;
    private TextView thuacan1;
    private TextView beophi;
    private TextView beophi1;
    private TextView gay;
    private TextView gay1;

    Float kq,kq2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tinhbeogay);
        initview();

tinh();

    }
    public void tinh(){
        edtChieucao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    kq = Float.parseFloat(String.valueOf(s));

                    float cannang = Float.parseFloat(edtCannang.getText().toString());
                    kq =cannang/(kq*kq);
                    tvketQuaDo.setText(String.valueOf(kq));

                }catch (NumberFormatException e ){}
                if(kq<18.5){
                    gay.setTextColor(Color.GREEN);
                    gay1.setTextColor(Color.GREEN);
                    binhthuong.setTextColor(Color.BLACK);
                    binhthuong1.setTextColor(Color.BLACK);
                    thuacan.setTextColor(Color.BLACK);
                    thuacan1.setTextColor(Color.BLACK);
                    beophi.setTextColor(Color.BLACK);
                    beophi1.setTextColor(Color.BLACK);

                }else if(kq>18.4&&kq<25){
                    binhthuong.setTextColor(Color.GREEN);
                    binhthuong1.setTextColor(Color.GREEN);
                    thuacan.setTextColor(Color.BLACK);
                    thuacan1.setTextColor(Color.BLACK);
                    beophi.setTextColor(Color.BLACK);
                    beophi1.setTextColor(Color.BLACK);
                    gay.setTextColor(Color.BLACK);
                    gay1.setTextColor(Color.BLACK);

                }
                else if(kq>24.9&&kq<30){
                    thuacan.setTextColor(Color.GREEN);
                    thuacan1.setTextColor(Color.GREEN);
                    beophi.setTextColor(Color.BLACK);
                    beophi1.setTextColor(Color.BLACK);
                    gay.setTextColor(Color.BLACK);
                    gay1.setTextColor(Color.BLACK);
                    binhthuong.setTextColor(Color.BLACK);
                    binhthuong1.setTextColor(Color.BLACK);

                }
                else if(kq>30) {
                    beophi.setTextColor(Color.GREEN);
                    beophi1.setTextColor(Color.GREEN);
                    gay.setTextColor(Color.BLACK);
                    gay1.setTextColor(Color.BLACK);
                    binhthuong.setTextColor(Color.BLACK);
                    binhthuong1.setTextColor(Color.BLACK);
                    thuacan.setTextColor(Color.BLACK);
                    thuacan1.setTextColor(Color.BLACK);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtCannang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    kq2 = Float.parseFloat(String.valueOf(s));

                    float chieucao = Float.parseFloat(edtChieucao.getText().toString());
                    kq2 =kq2/(chieucao*chieucao);
                    tvketQuaDo.setText(String.valueOf(kq2));
                }catch (NumberFormatException e ){}
                if(kq2<18.5){
                    gay.setTextColor(Color.GREEN);
                    gay1.setTextColor(Color.GREEN);
                    binhthuong.setTextColor(Color.BLACK);
                    binhthuong1.setTextColor(Color.BLACK);
                    thuacan.setTextColor(Color.BLACK);
                    thuacan1.setTextColor(Color.BLACK);
                    beophi.setTextColor(Color.BLACK);
                    beophi1.setTextColor(Color.BLACK);
                }else if(kq2>18.4&&kq2<25){
                    binhthuong.setTextColor(Color.GREEN);
                    binhthuong1.setTextColor(Color.GREEN);
                    thuacan.setTextColor(Color.BLACK);
                    thuacan1.setTextColor(Color.BLACK);
                    beophi.setTextColor(Color.BLACK);
                    beophi1.setTextColor(Color.BLACK);
                    gay.setTextColor(Color.BLACK);
                    gay1.setTextColor(Color.BLACK);

                }
                else if(kq2>24.9&&kq2<30){
                    thuacan.setTextColor(Color.GREEN);
                    thuacan1.setTextColor(Color.GREEN);
                    beophi.setTextColor(Color.BLACK);
                    beophi1.setTextColor(Color.BLACK);
                    gay.setTextColor(Color.BLACK);
                    gay1.setTextColor(Color.BLACK);
                    binhthuong.setTextColor(Color.BLACK);
                    binhthuong1.setTextColor(Color.BLACK);
                }
                else if(kq2>30) {
                    beophi.setTextColor(Color.GREEN);
                    beophi1.setTextColor(Color.GREEN);
                    gay.setTextColor(Color.BLACK);
                    gay1.setTextColor(Color.BLACK);
                    binhthuong.setTextColor(Color.BLACK);
                    binhthuong1.setTextColor(Color.BLACK);
                    thuacan.setTextColor(Color.BLACK);
                    thuacan1.setTextColor(Color.BLACK);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public  void initview(){
        edtChieucao = (EditText) findViewById(R.id.edtChieucao);
        edtCannang = (EditText) findViewById(R.id.edtCannang);
        tvketQuaDo = (TextView) findViewById(R.id.tvketQuaDo);

        binhthuong = (TextView) findViewById(R.id.binhthuong);
        binhthuong1 = (TextView) findViewById(R.id.binhthuong1);
        thuacan = (TextView) findViewById(R.id.thuacan);
        thuacan1 = (TextView) findViewById(R.id.thuacan1);
        beophi = (TextView) findViewById(R.id.beophi);
        beophi1 = (TextView) findViewById(R.id.beophi1);
        gay = (TextView) findViewById(R.id.gay);
        gay1 = (TextView) findViewById(R.id.gay1);

    }
}
