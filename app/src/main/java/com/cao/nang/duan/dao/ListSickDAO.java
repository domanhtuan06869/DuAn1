package com.cao.nang.duan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cao.nang.duan.R;
import com.cao.nang.duan.database.ConnectDB;
import com.cao.nang.duan.database.Query;
import com.cao.nang.duan.model.SickList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListSickDAO {
    ConnectDB connectDB;


    public ListSickDAO(Context context) {
        this.connectDB = new ConnectDB(context);
    }


    ///deletetableSick
    public  void deletetable(){
        SQLiteDatabase sqLiteDatabase=connectDB.getWritableDatabase();
        sqLiteDatabase.delete(Query.TABLE_SICK_LIST,null,null);
        sqLiteDatabase.close();
    }
    public void insertSick(Context context){
        SQLiteDatabase sqLiteDatabase=connectDB.getWritableDatabase();
        Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.sick));

        sqLiteDatabase.beginTransaction();
        String line;
        while(scanner.hasNextLine() && ((line = scanner.nextLine()) != null)) {
            String[] values = line.split(",");
            if(values.length != 2)
                continue;

            for(int i = 0; i < values.length; i++)
                values[i] = values[i].replace("\"", "");

            ContentValues cv = new ContentValues();
            cv.put(Query.SICK_NAME,values[0]);
            cv.put(Query.TREATMENT,values[1]);
            sqLiteDatabase.insert(Query.TABLE_SICK_LIST, null, cv);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }
    //lay du lieu bang list sick
    public List<SickList> getSickList(){
        List<SickList> lists=new ArrayList<>();
        String query="select * from "+Query.TABLE_SICK_LIST;
        SQLiteDatabase sqLiteDatabase=connectDB.getReadableDatabase();
        Cursor c =sqLiteDatabase.rawQuery(query,null);
        if(c!=null){
            if(c.getCount()>0){
                while(c.moveToNext()){
                    String namesick=c.getString(0);//ten benh
                    String treatent =c.getString(1);//cach chua benh
                   SickList sickList=new SickList(namesick,treatent);
                   lists.add(sickList);

                }
                c.close();
                sqLiteDatabase.close();
            }

        }
        return  lists;

    }

}
