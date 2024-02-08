package ru.vladgad.thinkcash.DataBaseClass;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import ru.vladgad.thinkcash.Time.DataTime;
import ru.vladgad.thinkcash.draw.TableColor;
import ru.vladgad.thinkcash.thirdClass.Categories;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class DataBaseFunction {
    public static ArrayList<Categories> QueryBD(DataBaseThinkCash dataBaseThinkCash, SQLiteDatabase database, ArrayList<Categories> dog){
        Cursor cursor = database.query(dataBaseThinkCash.TABLE_CATEGORIES,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
        int idIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_ID);
        int typeIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_TYPE);
        int nameIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_NAME);
        int properIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_PROPERTY);
        int imageIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_IMAGE);
        int tagIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_TAG);
        int i=0;
        int d=0;
        int r=0;
        // инициализтируем таблицу
        TableColor tableColor= new TableColor();
        do{
            dog.add(new Categories());
            if(cursor.getInt(typeIndex)==1){
                dog.get(i).r=TableColor.map[d].r;
                dog.get(i).g=TableColor.map[d].g;
                dog.get(i).b=TableColor.map[d].b;
                d++;
            }
            else
            {
                dog.get(i).r=TableColor.map[r].r;
                dog.get(i).g=TableColor.map[r].g;
                dog.get(i).b=TableColor.map[r].b;
                r++;
            }
            dog.get(i).SetId(cursor.getInt(idIndex));
            dog.get(i).SetName(cursor.getString(nameIndex));
            dog.get(i).SetType(cursor.getInt(typeIndex));
            dog.get(i).SetImage(cursor.getString(nameIndex));
            dog.get(i).SetProperty(cursor.getInt(properIndex));
            dog.get(i).SetImage(cursor.getString(imageIndex));
            dog.get(i).SetTag(cursor.getString(tagIndex));
            i++;
        }while (cursor.moveToNext());
        }
        cursor.close();

        return dog;
    }
    public static ArrayList<Operations> QueryBDO(DataBaseThinkCash dataBaseThinkCash, SQLiteDatabase database, ArrayList<Operations> dog){
        Cursor cursor;
        int i=0;
        Long herd;
        String table = dataBaseThinkCash.TABLE_OPERATIONS + " as OP inner join "+ dataBaseThinkCash.TABLE_CATEGORIES+ " as CA on OP.tag = CA.tag";
        String columns[] = {"OP._id as ID","OP.type as Type","OP.note as Note","OP.data as Data","OP.quantity as Quan","CA.name as Categor","CA.type as CatType","CA.tag as Tager,CA.property as Proper"};
        cursor = database.query(table, columns,null, null, null, null, null);
       if(cursor.moveToFirst()) {
           cursor.moveToFirst();
        int tagIndex = cursor.getColumnIndex("Categor");
        int tagerIndex = cursor.getColumnIndex("Tager");
        int typeIndex = cursor.getColumnIndex("Type");
        int noteIndex = cursor.getColumnIndex("Note");
        int quanIndex = cursor.getColumnIndex("Quan");
        int dataIndex = cursor.getColumnIndex("Data");
        int idIndex = cursor.getColumnIndex("ID");
        int catIndex = cursor.getColumnIndex("CatType");
        int properIndex = cursor.getColumnIndex("Proper");
        do{
            herd = Long.parseLong(cursor.getString(dataIndex));
            if((herd>= DataTime.startTime)&&(herd<=DataTime.finishTime)){
            dog.add(new Operations());
            dog.get(i).SetTag(cursor.getString(tagIndex));
           dog.get(i).SetId(cursor.getInt(idIndex));
           dog.get(i).SetType(cursor.getString(typeIndex));
           dog.get(i).SetData(cursor.getString(dataIndex));
           dog.get(i).SetNote(cursor.getString(noteIndex));
           dog.get(i).SetQuan(cursor.getInt(quanIndex));
           dog.get(i).SetTypeCat(cursor.getInt(catIndex));
           dog.get(i).SetTager(cursor.getString(tagerIndex));
           dog.get(i).SetProperCat(cursor.getInt(properIndex));
           i++;
            }
        }while (cursor.moveToNext());
       }
        cursor.close();
        return dog;
    }
    public static ArrayList<Operations> QueryBDOR(DataBaseThinkCash dataBaseThinkCash, SQLiteDatabase database, ArrayList<Operations> dog){
        Cursor cursor;
        int i=0;
        Long herd;
        String table = dataBaseThinkCash.TABLE_OPERATIONS + " as OP inner join "+ dataBaseThinkCash.TABLE_CATEGORIES+ " as CA on OP.tag = CA.tag";
        String columns[] = {"OP._id as ID","OP.type as Type","OP.note as Note","OP.data as Data","OP.quantity as Quan","CA.name as Categor","CA.type as CatType","CA.tag as Tager,CA.property as Proper"};
        cursor = database.query(table, columns,null, null, null, null, null);
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
        int tagIndex = cursor.getColumnIndex("Categor");
        int tagerIndex = cursor.getColumnIndex("Tager");
        int typeIndex = cursor.getColumnIndex("Type");
        int noteIndex = cursor.getColumnIndex("Note");
        int quanIndex = cursor.getColumnIndex("Quan");
        int dataIndex = cursor.getColumnIndex("Data");
        int idIndex = cursor.getColumnIndex("ID");
        int catIndex = cursor.getColumnIndex("CatType");
        int properIndex = cursor.getColumnIndex("Proper");
        do{
                dog.add(new Operations());
                dog.get(i).SetTag(cursor.getString(tagIndex));
                dog.get(i).SetId(cursor.getInt(idIndex));
                dog.get(i).SetType(cursor.getString(typeIndex));
                dog.get(i).SetData(cursor.getString(dataIndex));
                dog.get(i).SetNote(cursor.getString(noteIndex));
                dog.get(i).SetQuan(cursor.getInt(quanIndex));
                dog.get(i).SetTypeCat(cursor.getInt(catIndex));
                dog.get(i).SetTager(cursor.getString(tagerIndex));
                dog.get(i).SetProperCat(cursor.getInt(properIndex));
                i++;
        }while (cursor.moveToNext());
        }
        cursor.close();
        return dog;
    }
}
    /*
    Cursor cursor = database.query(dataBaseThinkCash.TABLE_CATEGORIES,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
        int idIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_ID);
        int catIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_NAME);
        int ImpIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_TAG);
        do{
            Log.d("mLog","ID = " +cursor.getInt(idIndex)+" name = "+ cursor.getString(catIndex)+ " cond = "+ cursor.getString(ImpIndex));

        }while (cursor.moveToNext());
    }
        else
    {
        Log.d("mLog","0 rows");
    }
        cursor.close();*/
  /*  SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
    Cursor cursor = database.query(dataBaseThinkCash.TABLE_OPERATIONS,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
                int Index1= cursor.getColumnIndex(dataBaseThinkCash.OPERATIONS_KEY_TAG);
                int Index2 = cursor.getColumnIndex(dataBaseThinkCash.OPERATIONS_KEY_QUANTITY);
                int Index3 = cursor.getColumnIndex(dataBaseThinkCash.OPERATIONS_KEY_NOTE);
                int Index4 = cursor.getColumnIndex(dataBaseThinkCash.OPERATIONS_KEY_DATA);
                int Index5 = cursor.getColumnIndex(dataBaseThinkCash.OPERATIONS_KEY_ID);
                int Index6 = cursor.getColumnIndex(dataBaseThinkCash.OPERATIONS_KEY_TYPE);
                do{
                Log.d("mLog","ID = " +cursor.getInt(Index5)+" note = "+ cursor.getString(Index3)+ " quan = "+ cursor.getString(Index2)+" tag = "+ cursor.getString(Index1));

                }while (cursor.moveToNext());
                }
                else
                {
                Log.d("mLog","0 rows");
                }
                cursor.close();*/