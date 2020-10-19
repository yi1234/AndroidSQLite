package cn.hhe.androidsqlite.ui.main;

import android.provider.BaseColumns;

/**
 * Create By zhongwen
 * on 2020/10/19
 * 表名 字段 数据库操作语句
 */
public class FeedReadContact {

    public FeedReadContact() {

    }

    public static class FeedEntity implements BaseColumns {
        public static final String TABLE_NAME = "entity";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_SEX = "sex";
    }

    //创建表 version 1
    public static final String SQL_CREATE_ENTITYS_1 = "CREATE TABLE " + FeedEntity.TABLE_NAME
            + " (" + FeedEntity._ID + " INTERGER PRIMARY KEY,"
            + FeedEntity.COLUMN_NAME_TITLE + " TEXT,"
            + FeedEntity.COLUMN_NAME_SUBTITLE + " TEXT)";

   //删除表
    public static final String SQL_DELETE_ENTITYS = "DROP TABLE IF EXISTS " + FeedEntity.TABLE_NAME;

    //1 创建临时表
    public static final String SQL_CREATE_TEMP = "ALTER TABLE " + FeedEntity.TABLE_NAME + " RENAME TO " + FeedEntity.TABLE_NAME + "_temp";

    //创建表 version 2
    public static final String SQL_CREATE_ENTITYS_2 = "CREATE TABLE " + FeedEntity.TABLE_NAME
            + " (" + FeedEntity._ID + " INTERGER PRIMARY KEY,"
            + FeedEntity.COLUMN_NAME_TITLE + " TEXT,"
            + FeedEntity.COLUMN_NAME_SUBTITLE + " TEXT,"
            + FeedEntity.COLUMN_NAME_AGE + " TEXT)";

    public static final String SQL_CREATE_ENTITYS_3 = "CREATE TABLE " + FeedEntity.TABLE_NAME
            + " (" + FeedEntity._ID + " INTERGER PRIMARY KEY,"
            + FeedEntity.COLUMN_NAME_TITLE + " TEXT,"
            + FeedEntity.COLUMN_NAME_SUBTITLE + " TEXT,"
            + FeedEntity.COLUMN_NAME_AGE + " TEXT,"
            + FeedEntity.COLUMN_NAME_SEX + " TEXT)";


    //3 将临时表的数据导入新表（注意处理修改的列）
    public static final String SQL_INSERT_OLD_DATA = "insert into " + FeedEntity.TABLE_NAME + "(_id, title, subtitle, age)"
            + " select _id, title, subtitle, \"18\" from " + FeedEntity.TABLE_NAME + "_temp";

    //4 删除临时表
    public static final String SQL_DELETE_TEMP = "DROP TABLE IF EXISTS " + FeedEntity.TABLE_NAME + "_temp";

    // 增加一个临时字段
    public static final String SQL_ADD_FIELD = "alter table " + FeedEntity.TABLE_NAME + " add column " + FeedEntity.COLUMN_NAME_SEX + " char";

}
