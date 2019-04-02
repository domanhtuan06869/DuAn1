package com.cao.nang.duan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.cao.nang.duan.base.Base;
import com.cao.nang.duan.dao.FindDAO;
import com.cao.nang.duan.dao.ListCategoryDAO;
import com.cao.nang.duan.dao.ListDrugDAO;
import com.cao.nang.duan.dao.ListListDrugDAO;
import com.cao.nang.duan.dao.ListSickDAO;
import com.cao.nang.duan.database.ConnectDB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Base
        implements NavigationView.OnNavigationItemSelectedListener {
DatabaseReference databaseReference;
ConnectDB connectDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_manage1) {

        } else if (id == R.id.nav_manage12) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void BenhVien(View view) {
        this.InsertDatabaseFindHospital();
        classintent(activity_tim_benh_vien.class);
    }

    public void Thuoc(View view) {
        this.insertModulListDrug();
        classintent(ListListDrug.class);
    }

    public void CongDong(View view) {
        classintent(LoginGroup.class);
    }

    public void Socuu(View view) {
        this.insetmodulSick();


    }
    //modul benh vien
    public void InsertDatabaseFindHospital(){
        FindDAO findDAO=new FindDAO(this);
        findDAO.deletetableHospital();  //xóa bảng bệnh viện
        findDAO.insertHospital(this);//thêm bảng bệnh viện
        findDAO.deletetableDistrc();//xóa bảng huyện
        findDAO.insertProvinceAndDistrct(this);///thêm bảng huyện
        findDAO.deletetable(); //xóa bảng tỉnh
        findDAO.insertAllProvince(this);/// thêm bảng Tỉnh
    }
    //modul Drug
    public  void insertModulListDrug(){
        ListListDrugDAO listListDrugDAO=new ListListDrugDAO(this);
        listListDrugDAO.deleteDrugList();// xóa bảng danh mục
        listListDrugDAO.insertAllListListDrug(this);//thêm bảng danh mục

        ListCategoryDAO listCategoryDAO=new ListCategoryDAO(this);
        listCategoryDAO.deleteCategory();//xóa bảng categoryDrug
        listCategoryDAO.insertAllListCategoryDurg(this);//thêm bảng categorydrug

        ListDrugDAO listDrugDAO=new ListDrugDAO(this);
        listDrugDAO.deleteTableDrug();
        listDrugDAO.insertAllDrug(this);

    }
    public  void insetmodulSick(){
        ListSickDAO listSickDAO=new ListSickDAO(this);
        listSickDAO.deletetable();// xoa bang
        listSickDAO.insertSick(this);// insert bang benh
    }
}
