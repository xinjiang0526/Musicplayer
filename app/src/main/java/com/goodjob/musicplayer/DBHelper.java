package com.goodjob.musicplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class DBHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER="create table user(" +
            "id integer primary key autoincrement,"
            +"account text,"
            +"password text)";

    private Context mContext;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory Factory, int version){
        super(context,name,Factory,version);
        mContext=context;
    }
    @Override
    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext,"创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //onCreate(db);
    }

}
