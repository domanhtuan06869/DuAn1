package com.cao.nang.duan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cao.nang.duan.adapter.DrugAdapter;
import com.cao.nang.duan.base.Base;
import com.cao.nang.duan.dao.ListDrugDAO;
import com.cao.nang.duan.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class ListDrug extends Base {
    private RecyclerView rcviewDrug;
    ListDrugDAO listDrugDAO=new ListDrugDAO(this);
    List<Drug>drugList=new ArrayList<>();
    DrugAdapter drugAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drug);
        Intent in=getIntent();
        Bundle b= in.getExtras();
        String category=b.getString("List_Category");

        rcviewDrug = (RecyclerView) findViewById(R.id.rcviewDrug);
       drugList= listDrugDAO.getAllDurgWithCategory(category);
        drugAdapter=new DrugAdapter(this,drugList);
        rcviewDrug.setLayoutManager(new LinearLayoutManager(this));
        rcviewDrug.setAdapter(drugAdapter);
        showToast(category);


    }
}
