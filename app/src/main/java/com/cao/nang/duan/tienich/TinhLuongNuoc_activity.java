package com.cao.nang.duan.tienich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.base.Base;

import java.text.DecimalFormat;

public class TinhLuongNuoc_activity extends Base {
    private EditText edtSocan;
    private EditText edtPhut;
    private TextView tvketQuanc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tinh_luong_nuoc);
        edtSocan = (EditText) findViewById(R.id.edtSocan);
        edtPhut = (EditText) findViewById(R.id.edtPhut);
        tvketQuanc = (TextView) findViewById(R.id.tvketQuanc);
        edtSocan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              try {
                  float socan=Float.parseFloat(String.valueOf(s));

                  float tinh = (float) ((socan * 2) * 0.5);
                  DecimalFormat df = new DecimalFormat("0.00");
                  float ketqua = (float) (tinh * 0.03);
                  df.format(ketqua);
                  tvketQuanc.setText(String.valueOf(ketqua+" lít "));
              }catch (NumberFormatException n){}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPhut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    float socan = Float.parseFloat(edtSocan.getText().toString());
                    float tinh = (float) ((socan * 2) * 0.5);
                    float sophut = Float.parseFloat(String.valueOf(s));
                    float ketqua =  tinh + (sophut / 30 * 12);
                    DecimalFormat df = new DecimalFormat("0.00");
                    float ketqua2 = (float) (ketqua * 0.03);
                    df.format(ketqua2);
                    tvketQuanc.setText(String.valueOf(ketqua2+" lít"));
                }catch (NumberFormatException n){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
