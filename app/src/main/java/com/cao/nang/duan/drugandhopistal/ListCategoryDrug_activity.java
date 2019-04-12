package com.cao.nang.duan.drugandhopistal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.adapter.CategoryDrugAdapter;
import com.cao.nang.duan.base.Base;
import com.cao.nang.duan.dao.ListCategoryDAO;
import com.cao.nang.duan.model.CategoryDrug;

import java.util.ArrayList;
import java.util.List;

public class ListCategoryDrug_activity extends Base {
    List<CategoryDrug> categoryDrugList=new ArrayList<>();
    ListCategoryDAO listCategoryDAO=new ListCategoryDAO(this);
    CategoryDrugAdapter categoryDrugAdapter;
    private RecyclerView rcCategory;
    static  final String drunglist_ ="List_Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_list_category_drug);
        Intent in =getIntent();
        Bundle b=in.getExtras();
        String drunglist=b.getString(drunglist_);
        rcCategory = (RecyclerView) findViewById(R.id.rcCategory);

       // listCategoryDAO.insertAllListCategoryDurg(this);
        categoryDrugList=listCategoryDAO.getAllDurgWithDruglist(drunglist);
        categoryDrugAdapter=new CategoryDrugAdapter(this,categoryDrugList);
        rcCategory.setLayoutManager(new LinearLayoutManager(this));
        rcCategory.setAdapter(categoryDrugAdapter);





    }
}
