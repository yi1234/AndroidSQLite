package cn.hhe.androidsqlite.ui.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By zhongwen
 * on 2020/10/19
 * 管理类 增删改查
 */
public class FeedReaderDao {

    private FeedReaderDbHelper dbHelper;

    public FeedReaderDao(Context context) {
        init(context);
    }

    private void init(Context context) {
        dbHelper = new FeedReaderDbHelper(context);
    }

    /**
     * 插入数据
     *
     * @param title
     * @param subTitle
     */
    public void insert(String title, String subTitle, String age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReadContact.FeedEntity.COLUMN_NAME_TITLE, title);
        values.put(FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE, subTitle);
        if(FeedReaderDbHelper.DATABASE_VERSION == 2){
            values.put(FeedReadContact.FeedEntity.COLUMN_NAME_AGE, age);
        }
        if(FeedReaderDbHelper.DATABASE_VERSION == 3){
            values.put(FeedReadContact.FeedEntity.COLUMN_NAME_AGE, age);
            values.put(FeedReadContact.FeedEntity.COLUMN_NAME_SEX, "男");
        }
        long index = db.insert(FeedReadContact.FeedEntity.TABLE_NAME, null, values);
        Log.d("insert", "index = " + index);
    }

    /**
     * 查询插入数据列表
     *
     * @return
     */
    public List<FeedBean> query() {
        String[] columns = new String[]{BaseColumns._ID, FeedReadContact.FeedEntity.COLUMN_NAME_TITLE, FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE, FeedReadContact.FeedEntity.COLUMN_NAME_AGE};
        if(FeedReaderDbHelper.DATABASE_VERSION == 1){
             columns = new String[]{BaseColumns._ID, FeedReadContact.FeedEntity.COLUMN_NAME_TITLE, FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE};
        }else if(FeedReaderDbHelper.DATABASE_VERSION == 2){
            columns = new String[]{BaseColumns._ID, FeedReadContact.FeedEntity.COLUMN_NAME_TITLE, FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE, FeedReadContact.FeedEntity.COLUMN_NAME_AGE};
        } else {
             columns = new String[]{BaseColumns._ID, FeedReadContact.FeedEntity.COLUMN_NAME_TITLE, FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE, FeedReadContact.FeedEntity.COLUMN_NAME_AGE, FeedReadContact.FeedEntity.COLUMN_NAME_SEX};
        }

        String selection = FeedReadContact.FeedEntity.COLUMN_NAME_TITLE + " = ?";
        String sortOrder = FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE + " DESC ";
        String[] selectionArgs = {"Ok"};
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(FeedReadContact.FeedEntity.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<FeedBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            FeedBean feedEntity = new FeedBean();
            feedEntity.setTitle(cursor.getString(cursor.getColumnIndex(FeedReadContact.FeedEntity.COLUMN_NAME_TITLE)));
            feedEntity.setSubTitle(cursor.getString(cursor.getColumnIndex(FeedReadContact.FeedEntity.COLUMN_NAME_SUBTITLE)));
            if (FeedReaderDbHelper.DATABASE_VERSION == 2) {
                feedEntity.setAge(cursor.getString(cursor.getColumnIndex(FeedReadContact.FeedEntity.COLUMN_NAME_AGE)));
            }else if(FeedReaderDbHelper.DATABASE_VERSION == 3){
                feedEntity.setAge(cursor.getString(cursor.getColumnIndex(FeedReadContact.FeedEntity.COLUMN_NAME_AGE)));
                feedEntity.setSex(cursor.getString(cursor.getColumnIndex(FeedReadContact.FeedEntity.COLUMN_NAME_SEX)));
            }
            list.add(feedEntity);
        }
        cursor.close();
        return list;
    }

    /**
     * 删除数据
     */
    public void delete() {
        String whereCaluse = FeedReadContact.FeedEntity.COLUMN_NAME_TITLE + " LIKE ?";
        String[] whereSelections = {"NO"};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int index = db.delete(FeedReadContact.FeedEntity.TABLE_NAME, whereCaluse, whereSelections);
        Log.d("delete", "index = " + index);
    }


    public void update() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String title = "NO";
        ContentValues values = new ContentValues();
        values.put(FeedReadContact.FeedEntity.COLUMN_NAME_TITLE, title);
        String whereCaluse = FeedReadContact.FeedEntity.COLUMN_NAME_TITLE + " LIKE ?";
        String[] whereSections = {"Ok"};
        db.update(FeedReadContact.FeedEntity.TABLE_NAME, values, whereCaluse, whereSections);
    }

    public void close() {
        dbHelper.close();
    }

}
