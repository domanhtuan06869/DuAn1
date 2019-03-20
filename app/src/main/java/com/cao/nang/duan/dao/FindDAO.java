package com.cao.nang.duan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cao.nang.duan.R;
import com.cao.nang.duan.database.ConnectDB;
import com.cao.nang.duan.database.Query;
import com.cao.nang.duan.model.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindDAO {
    ConnectDB connectDB;

    public FindDAO(Context context) {
        this.connectDB = new ConnectDB(context);
    }
    // phương thức thêm provin
    public void insertAllProvince(Context context) {
        SQLiteDatabase sqLiteDatabase = connectDB.getWritableDatabase();
        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.provin));

        sqLiteDatabase.beginTransaction();
        String line;
        while (scanner.hasNextLine() && ((line = scanner.nextLine()) != null)) {
            String[] values = line.split(",");
            if (values.length != 1)
                continue;

            for (int i = 0; i < values.length; i++)
                values[i] = values[i].replace("\"", "");

            ContentValues cv = new ContentValues();
            cv.put(Query.PROVINCE, values[0]);

            sqLiteDatabase.insert(Query.TABLE_FIND_HOSPITAL, null, cv);

        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }
    public void insertProvinceAndDistrct(Context context) {
        SQLiteDatabase sqLiteDatabase = connectDB.getWritableDatabase();
        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.district));

        sqLiteDatabase.beginTransaction();
        String line;
        while (scanner.hasNextLine() && ((line = scanner.nextLine()) != null)) {
            String[] values = line.split(",");
            if (values.length != 2)
                continue;

            for (int i = 0; i < values.length; i++)
                values[i] = values[i].replace("\"", "");

            ContentValues cv = new ContentValues();
            cv.put(Query.DISTRICT, values[0]);
            cv.put(Query.PROVINCE, values[1]);

            sqLiteDatabase.insert(Query.TABLE_FIND_HOSPITAL_DISTRICT, null, cv);

        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }
    //xóa bảng tỉnh
    public  void deletetable(){
        SQLiteDatabase db = connectDB.getWritableDatabase();
        db.delete(Query.TABLE_FIND_HOSPITAL,null,null);
        db.close();
    }
    /// xoa bang benh vien
    public  void deletetableHospital() {
        SQLiteDatabase db = connectDB.getWritableDatabase();
        db.delete(Query.TABLE_HOSPITAL, null, null);
        db.close();
    }
    /// xoa bang huyen
    public  void deletetableDistrc() {
        SQLiteDatabase db = connectDB.getWritableDatabase();
        db.delete(Query.TABLE_FIND_HOSPITAL_DISTRICT, null, null);
        db.close();
    }
    public List<String> getAllListProvineAndDistrict(String tinh){
        List<String> list = new ArrayList<>();


        String query = "select * from " + Query.TABLE_FIND_HOSPITAL_DISTRICT+" where "+ Query.PROVINCE+"='"+tinh+"'"; //ttruy vấn lấy ds tỉnh
        SQLiteDatabase db = connectDB.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    String district = c.getString(0);// lấy dữ liệu

                    list.add(district);
                }
                c.close();
            }
        }
        return list;


    }
    /// lấy danh sách tỉnh
    public List<String> getAllListProvine(){
        List<String> list = new ArrayList<>();


        String query = "select * from " + Query.TABLE_FIND_HOSPITAL; //ttruy vấn lấy ds tỉnh
        SQLiteDatabase db = connectDB.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    String provine = c.getString(0);// lấy dữ liệu


                    list.add(provine);
                }
                c.close();
            }
        }
        return list;


    }

    //insert bảng dữ liệu bệnh viện
    public void insertHospital(Context context) {
        SQLiteDatabase sqLiteDatabase = connectDB.getWritableDatabase();
        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.hospital));

        sqLiteDatabase.beginTransaction();
        String line;
        while (scanner.hasNextLine() && ((line = scanner.nextLine()) != null)) {
            String[] values = line.split(",");
            if (values.length != 6)
                continue;

            for (int i = 0; i < values.length; i++)
                values[i] = values[i].replace("\"", "");

            ContentValues cv = new ContentValues();
            cv.put(Query.HOSPITAL_NAME, values[0]);
            cv.put(Query.PROVINCE, values[1]);
            cv.put(Query.DISTRICT, values[2]);
            cv.put(Query.ADDRESS_HOSPITAL, values[3]);
            cv.put(Query.LONGITUDE, values[4]);
            cv.put(Query.LATITUDE, values[5]);

            sqLiteDatabase.insert(Query.TABLE_HOSPITAL, null, cv);

        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }
// lấy dữ liệu bệnh viện theo tỉnh
    public List<Hospital> getAllListHospitalWithProvince(String tinh){
        List<Hospital> list = new ArrayList<>();

        String query = "select * from " + Query.TABLE_HOSPITAL+" where "+ Query.PROVINCE+"='"+tinh+"'"; //ttruy vấn lấy bvien theo tỉnh

        SQLiteDatabase db = connectDB.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    String name = c.getString(0);// lấy dữ liệu
                    String province=c.getString(1);
                    String district=c.getString(2);
                    String address=c.getString(3);
                    double longtitude= c.getDouble(4);
                    double latitude=c.getDouble(5);
                    Hospital hospital=new Hospital(name,province,district,address,longtitude,latitude);
                    list.add(hospital);
                }
                c.close();
            }
        }
        return list;


    }
    public List<Hospital> getAllListHospitalWithProvinceandDistrict(String tinh, String huyen){
        List<Hospital> list = new ArrayList<>();

        String query = "select * from " + Query.TABLE_HOSPITAL+" where "+ Query.PROVINCE+"='"+tinh+"'"+" and "+Query.DISTRICT+"='"+huyen+"'"; //ttruy vấn lấy bvien theo tỉnh

        SQLiteDatabase db = connectDB.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    String name = c.getString(0);// lấy dữ liệu
                    String province=c.getString(1);
                    String district=c.getString(2);
                    String address=c.getString(3);
                    double longtitude=c.getDouble(4);
                    double latitude=c.getDouble(5);
                    Hospital hospital=new Hospital(name,province,district,address,longtitude,latitude);
                    list.add(hospital);
                }
                c.close();
            }
        }
        return list;


    }
}
