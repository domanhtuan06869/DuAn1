package com.cao.nang.duan.drugandhopistal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cao.nang.duan.R;
import com.cao.nang.duan.adapter.FindHospitalAdapter;
import com.cao.nang.duan.base.Base;
import com.cao.nang.duan.dao.FindDAO;
import com.cao.nang.duan.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class activity_tim_benh_vien extends Base {
    private FindDAO finDAO;
    private Spinner spnTinh;
    private Spinner spnHuyen;
    String tentinh;
    String huyen;
    private RecyclerView rcHospital;
    List<String> provinces=new ArrayList<>();
    List<String>districtList=new ArrayList<>();
    List<Hospital>hospitalList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tim_benh_vien);
        rcHospital = (RecyclerView) findViewById(R.id.rcHospital);

        spnTinh = (Spinner) findViewById(R.id.spnTinh);
        spnHuyen = (Spinner) findViewById(R.id.spnHuyen);
        finDAO=new FindDAO(this);
//        finDAO.deletetable();
//        finDAO.insertAllProvince(this);
        provinces=finDAO.getAllListProvine();
        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,provinces);
        spnTinh.setAdapter(adapter);
        spnTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tentinh=spnTinh.getSelectedItem().toString();
                district(tentinh);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void district(String tentinh){
//        finDAO.deletetableDistrc();
//        finDAO.insertProvinceAndDistrct(this);
        districtList= finDAO.getAllListProvineAndDistrict(tentinh);
        ArrayAdapter<String>adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,districtList);
        spnHuyen.setAdapter(adapter);

       spnHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               huyen= spnHuyen.getSelectedItem().toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }

    public void TimBv(View view){
          //  finDAO.deletetableHospital();
          //  finDAO.insertHospital(this);
            hospitalList.clear();
            hospitalList = finDAO.getAllListHospitalWithProvince(tentinh);
            FindHospitalAdapter findHospitalAdapter = new FindHospitalAdapter(this, hospitalList);
            rcHospital.setLayoutManager(new LinearLayoutManager(this));
            findHospitalAdapter.notifyDataSetChanged();
            rcHospital.setAdapter(findHospitalAdapter);


    }

    public void TimbvwithVsDistrict(View view) {
//        finDAO.deletetableHospital();
//        finDAO.insertHospital(this);
        hospitalList.clear();
        hospitalList = finDAO.getAllListHospitalWithProvinceandDistrict(tentinh,huyen);
        FindHospitalAdapter findHospitalAdapter = new FindHospitalAdapter(this, hospitalList);
        rcHospital.setLayoutManager(new LinearLayoutManager(this));
        findHospitalAdapter.notifyDataSetChanged();
        rcHospital.setAdapter(findHospitalAdapter);
    }
}

