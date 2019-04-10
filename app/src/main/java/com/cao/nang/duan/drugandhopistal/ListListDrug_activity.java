package com.cao.nang.duan.drugandhopistal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.adapter.ListListDrugAdapter;
import com.cao.nang.duan.dao.ListListDrugDAO;
import com.cao.nang.duan.model.ListDrug;

import java.util.ArrayList;
import java.util.List;

public class ListListDrug_activity extends AppCompatActivity {
    private RecyclerView rcListListDrug;
    List<ListDrug> listDrugList=new ArrayList<>();
    ListListDrugDAO listListDrugDAO=new ListListDrugDAO(this);
    ListListDrugAdapter listDrugAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_list_list_drug);
        rcListListDrug = (RecyclerView) findViewById(R.id.rcListListDrug);
//        listListDrugDAO.deleteDrugList();
//        listListDrugDAO.insertAllListListDrug(this);
        listDrugList=listListDrugDAO.getListListDrug();
        listDrugAdapter=new ListListDrugAdapter(this,listDrugList);
        rcListListDrug.setLayoutManager(new LinearLayoutManager(this));
        rcListListDrug.setAdapter(listDrugAdapter);

    }
}
