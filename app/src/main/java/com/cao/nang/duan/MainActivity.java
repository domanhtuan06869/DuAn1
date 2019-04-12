package com.cao.nang.duan;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.view.MenuItem;
import android.widget.Toast;

import com.cao.nang.duan.alram.AlarmDrug_activity;
import com.cao.nang.duan.base.Base;
import com.cao.nang.duan.chatgroup.LoginGroup;
import com.cao.nang.duan.dao.FindDAO;
import com.cao.nang.duan.dao.ListCategoryDAO;
import com.cao.nang.duan.dao.ListDrugDAO;
import com.cao.nang.duan.dao.ListListDrugDAO;
import com.cao.nang.duan.dao.ListSickDAO;
import com.cao.nang.duan.database.ConnectDB;
import com.cao.nang.duan.drugandhopistal.ListListDrug_activity;
import com.cao.nang.duan.drugandhopistal.ListSick_activity;
import com.cao.nang.duan.drugandhopistal.activity_tim_benh_vien;
import com.cao.nang.duan.fitnessapi.StepCounter_activity;
import com.cao.nang.duan.tienich.Aboutme;
import com.cao.nang.duan.tienich.News;
import com.cao.nang.duan.tienich.TinhLuongNuoc_activity;
import com.cao.nang.duan.tienich.Tinhbeogay_activity;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends Base
        implements NavigationView.OnNavigationItemSelectedListener {
DatabaseReference databaseReference;
ConnectDB connectDB;
    private static final int MY_PERMISSIONS_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.steps) {
            classintent(StepCounter_activity.class);
        } else if (id == R.id.khoiluongct) {
            classintent(Tinhbeogay_activity.class);

        }else  if(id==R.id.ncothe){
            classintent(TinhLuongNuoc_activity.class);
        }
        else if(id==R.id.baouongthuoc){
          classintent(AlarmDrug_activity.class);
        }

        else if (id == R.id.news) {
            classintent(News.class);


        } else if (id == R.id.aboutme) {
            classintent(Aboutme.class);

        } else if (id == R.id.exits) {
         showAlertDialog();

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
        classintent(ListListDrug_activity.class);
    }

    public void CongDong(View view) {
        classintent(LoginGroup.class);
    }

    public void Socuu(View view) {
        this.insetmodulSick();
        classintent(ListSick_activity.class);


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



    private void checkPermission() {
        Log.d("checkPermission","run");
        String[] listPermission = new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        boolean isOn = false;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {

            isOn = true;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {

            isOn = true;
        }

        if (isOn){
            Log.d("request", "go");
            ActivityCompat.requestPermissions(this, listPermission, MY_PERMISSIONS_REQUEST);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("size", ">> " + grantResults.length);
        if (requestCode == 1) {
            for (int i = 0; i < grantResults.length; i++) {
                switch (i) {
                    case 0:
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        } else {

                        }
                        break;
                    case 1:
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        } else {

                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Không thoát được", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}

