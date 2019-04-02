package com.cao.nang.duan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cao.nang.duan.adapter.SickAdapter;
import com.cao.nang.duan.dao.ListSickDAO;
import com.cao.nang.duan.model.SickList;

import java.util.ArrayList;
import java.util.List;

public class ListSick extends AppCompatActivity {
        ListSickDAO listSickDAO=new ListSickDAO(this);
        SickAdapter sickAdapter;
    private RecyclerView rcListSick;
    List<SickList> sickListList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sick);
        rcListSick = (RecyclerView) findViewById(R.id.rcListSick);
        init();


    }
    public void init(){
       sickListList= listSickDAO.getSickList();
       sickAdapter=new SickAdapter(this,sickListList);
       rcListSick.setLayoutManager(new LinearLayoutManager(this));
       rcListSick.setAdapter(sickAdapter);

    }
}
