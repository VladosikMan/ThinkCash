package ru.vladgad.thinkcash.DataBaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseThinkCash extends SQLiteOpenHelper{

    public  static  final  int DATABASE_VERSION=1;
    public static  final String DATABASE_NAME = "ThinkCash";

    public static final String TABLE_CATEGORIES = "Categories";
    public static final String CATEGORIES_KEY_ID = "_id";
    public static final String CATEGORIES_KEY_TYPE = "type";
    public static final String CATEGORIES_KEY_NAME = "name";
    public static final String CATEGORIES_KEY_PROPERTY = "property";
    public static final String CATEGORIES_KEY_IMAGE="image";
    public static final String CATEGORIES_KEY_TAG = "tag";

    public static final String TABLE_OPERATIONS = "Operations";
    public static final String OPERATIONS_KEY_ID = "_id";
    public static final String OPERATIONS_KEY_TAG = "tag";
    public static final String OPERATIONS_KEY_NOTE = "note";
    public static final String OPERATIONS_KEY_DATA= "data";
    public static final String OPERATIONS_KEY_TYPE = "type";
    public static final String OPERATIONS_KEY_QUANTITY="quantity";

    public DataBaseThinkCash(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ TABLE_CATEGORIES+ "("+CATEGORIES_KEY_ID+" integer primary key,"+CATEGORIES_KEY_TYPE+" int,"+ CATEGORIES_KEY_NAME+ " text,"+ CATEGORIES_KEY_PROPERTY + " int,"+CATEGORIES_KEY_IMAGE + " text," + CATEGORIES_KEY_TAG + " text" +")");
        db.execSQL("create table "+ TABLE_OPERATIONS+ "("+OPERATIONS_KEY_ID+" integer primary key,"+OPERATIONS_KEY_TYPE+" text,"+ OPERATIONS_KEY_NOTE+ " text,"+ OPERATIONS_KEY_QUANTITY + " int,"+OPERATIONS_KEY_DATA + " text," + OPERATIONS_KEY_TAG + " text" +")");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CATEGORIES);
        db.execSQL("drop table if exists " + TABLE_OPERATIONS);
        onCreate(db);
    }
}