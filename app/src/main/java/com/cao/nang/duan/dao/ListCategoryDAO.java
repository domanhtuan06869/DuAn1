package com.cao.nang.duan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cao.nang.duan.R;
import com.cao.nang.duan.database.ConnectDB;
import com.cao.nang.duan.database.Query;
import com.cao.nang.duan.model.CategoryDrug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListCategoryDAO {
    ConnectDB connectDB;
    public ListCategoryDAO(Context context) {
        this.connectDB=new ConnectDB(context);

    }

    //insert dữ liệu vào thể loại
    public void insertAllListCategoryDurg(Context context) {
        SQLiteDatabase sqLiteDatabase = connectDB.getWritableDatabase();
        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.category));

        sqLiteDatabase.beginTransaction();
        String line;
        while (scanner.hasNextLine() && ((line = scanner.nextLine()) != null)) {
            String[] values = line.split("/");
            if (values.length != 2)
                continue;

            for (int i = 0; i < values.length; i++)
                values[i] = values[i].replace("\"", "");

            ContentValues cv = new ContentValues();
            cv.put(Query.DRUG_CATEGORY, values[0]);
            cv.put(Query.DRUG_LIST, values[1]);

            sqLiteDatabase.insert(Query.TABLE_CATEGORY_DRUG, null, cv);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }
    public  void deleteCategory(){
        SQLiteDatabase sqLiteDatabase = connectDB.getWritableDatabase();
        sqLiteDatabase.delete(Query.TABLE_CATEGORY_DRUG,null,null);
        sqLiteDatabase.close();
    }
    //phương thức lấy dữ liêu database category Durg
    public List<CategoryDrug> getAllDurgWithDruglist(String drug_list){
        List<CategoryDrug> list = new ArrayList<>();

        String query = "select * from  "+Query.TABLE_CATEGORY_DRUG+" where "+Query.DRUG_LIST+ "='"+drug_list+"'";
        SQLiteDatabase db = connectDB.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);

        if(c != null){
            if(c.getCount() > 0){
                while (c.moveToNext()){
                    // lấy giá trị từng cột trong database
                    String categorydrug = c.getString(0);// lấy giá trị tl drug
                    String druglist = c.getString(1);// lấy giá trị tl drug



                    /// thêm giá trị vào đối tượng Durg
                   CategoryDrug  categoryDrug= new CategoryDrug(categorydrug,druglist);
                    list.add(categoryDrug);
                }
                c.close();
                db.close();
            }
        }

        return list;
    }
}
