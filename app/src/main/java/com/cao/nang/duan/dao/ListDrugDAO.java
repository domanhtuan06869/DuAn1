package com.cao.nang.duan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cao.nang.duan.R;
import com.cao.nang.duan.database.ConnectDB;
import com.cao.nang.duan.database.Query;
import com.cao.nang.duan.model.Drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListDrugDAO {

    ConnectDB connectDB;

    public ListDrugDAO(Context context) {
        this.connectDB=new ConnectDB(context);

    }
    //phương thức insert danh sách thuốc bằng file csv excel
    public  void insertAllDrug(Context context) {
        SQLiteDatabase sqLiteDatabase=connectDB.getWritableDatabase();
        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.drug));

        sqLiteDatabase.beginTransaction();
        String line;
        while(scanner.hasNextLine() && ((line = scanner.nextLine()) != null)) {
            String[] values = line.split("/");
            if(values.length != 9)
                continue;

            for(int i = 0; i < values.length; i++)
                values[i] = values[i].replace("\"", "");

            ContentValues cv = new ContentValues();
            cv.put(Query.NAME_DRUG,values[0]);
            cv.put(Query.DRUG_CATEGORY,values[1]);
            cv.put(Query.PRICE_DRUG,values[2]);
            cv.put(Query.INGREDIENT_DRUG,values[3]);
            cv.put(Query. ASSIGN_DRUG,values[4]);
            cv.put(Query. CONTRAINDICATED_DRUG,values[5]);
            cv.put(Query. USE_DRUG,values[6]);
            cv.put(Query. SIDE_EFFECTS,values[7]);
            cv.put(Query.ATTENTION,values[8]);
            sqLiteDatabase.insert(Query.TABLE_DRUG, null, cv);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }
    public  void deleteTableDrug(){
        SQLiteDatabase sqLiteDatabase=connectDB.getWritableDatabase();
        sqLiteDatabase.delete(Query.TABLE_DRUG,null,null);
    }
/// phương thức lấy list thuốc theo thể loại
    public List<Drug> getAllDurgWithCategory(String category_drug){
        List<Drug> list = new ArrayList<>();

        String query = "select * from  "+Query.TABLE_DRUG+" where "+Query.DRUG_CATEGORY+ "='"+category_drug+"'";
     //  String query = "select * from  "+Query.TABLE_DRUG+"";
        SQLiteDatabase db = connectDB.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);

        if(c != null){
            if(c.getCount() > 0){
                while (c.moveToNext()){
                    // lấy giá trị từng cột trong database
                    String name_drug = c.getString(0);//tên thuốc
                    String category_drug_ = c.getString(1); // thể loại
                    String price_durg = c.getString(2);// giá
                    String ingredient_durg = c.getString(3);// thành phần
                    String assign_durg = c.getString(4);// chỉ định
                    String contraindicate_durg = c.getString(5);// chống chỉ định
                    String use_durg = c.getString(6); // cách dùng
                    String side_effects = c.getString(7); //tác dụng phụ
                    String attention = c.getString(8);// chú ý về thuốc

                    /// thêm giá trị vào đối tượng Durg
                    Drug drug = new Drug(name_drug,category_drug_,price_durg,ingredient_durg,assign_durg,contraindicate_durg,use_durg,side_effects,attention);
                    list.add(drug);
                }
                c.close();
                db.close();
            }
        }

        return list;
    }
}
