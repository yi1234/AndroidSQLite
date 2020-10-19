package cn.hhe.androidsqlite.ui.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Create By zhongwen
 * on 2020/10/19
 * 表的创建 升级
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //这里永远都是最新的创建表格代码
        db.execSQL(FeedReadContact.SQL_CREATE_ENTITYS_3);
        //创建表只会走一次
        Log.d("onCreate","onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("onUpgrade","onUpgrade");
        //升级表也只会走一次
       if(1 == oldVersion){
            db.execSQL(FeedReadContact.SQL_CREATE_TEMP);
            db.execSQL(FeedReadContact.SQL_CREATE_ENTITYS_2);
            db.execSQL(FeedReadContact.SQL_INSERT_OLD_DATA);
            db.execSQL(FeedReadContact.SQL_DELETE_TEMP);
        }else if(2 == oldVersion){
            db.execSQL(FeedReadContact.SQL_ADD_FIELD);
        }

    }





}
