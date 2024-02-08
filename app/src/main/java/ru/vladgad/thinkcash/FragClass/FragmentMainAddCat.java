 package ru.vladgad.thinkcash.FragClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorCat;

public class FragmentMainAddCat extends Fragment implements  View.OnClickListener{
    private TextView namecat;
    private EditText edit;
    private DataBaseThinkCash dataBaseThinkCash;
    private Button btn1,btn2;
    private RadioButton rb1,rb2;
    private Context context;
    private FragmentTransaction fTrans;
    private FragmentMain fragmentMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main_addcat,null);
        context = container.getContext();
        fragmentMain = new FragmentMain();
        dataBaseThinkCash=new DataBaseThinkCash(context);
        namecat=(TextView)v.findViewById(R.id.nameCat);
        edit = (EditText)v.findViewById(R.id.editCat);
        rb1 = (RadioButton)v.findViewById(R.id.main);
        rb2 = (RadioButton)v.findViewById(R.id.additional);
        btn1=(Button)v.findViewById(R.id.cancel);
        btn1.setOnClickListener(this);
        btn2=(Button)v.findViewById(R.id.ok);
        btn2.setOnClickListener(this);
        if(StorCat.type==1){
            namecat.setText("Доход");
            rb1.setText("Основной");
            rb2.setText("Дополнительный");
        }else {
            namecat.setText("Расход");
            rb1.setText("Важный");
            rb2.setText("Не важный");
        }
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:{
                edit.setText("");
                rb1.setChecked(true);
                rb2.setChecked(false);
                break;
            }
            case R.id.ok:{
                //проверка введённых данных
                if(edit.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getActivity(),
                            "Что есть пустота?", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    ArrayList<String> str = new ArrayList<String>();
                    ArrayList<String> strTag = new ArrayList<String>();
                    SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
                    Cursor cursor = null;
                    if (StorCat.type == 1) {
                        Log.d("mLog","Есть контакт");
                        cursor = database.query(dataBaseThinkCash.TABLE_CATEGORIES, new String[]{dataBaseThinkCash.CATEGORIES_KEY_NAME, dataBaseThinkCash.CATEGORIES_KEY_TAG}, dataBaseThinkCash.CATEGORIES_KEY_TYPE + " = 1", null, null, null, null);
                    } else {
                        cursor = database.query(dataBaseThinkCash.TABLE_CATEGORIES, new String[]{DataBaseThinkCash.CATEGORIES_KEY_NAME, dataBaseThinkCash.CATEGORIES_KEY_TAG}, dataBaseThinkCash.CATEGORIES_KEY_TYPE + " = 2", null, null, null, null);
                    }
                    String tag;
                    if( cursor.moveToFirst()){
                        int nameIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_NAME);
                        int tagIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_TAG);
                         do {
                              str.add(cursor.getString(nameIndex));
                              strTag.add(cursor.getString(tagIndex));
                         } while (cursor.moveToNext());

                        int cout = Integer.parseInt(strTag.get(strTag.size()-1).substring(2));
                        cout++;
                        if(StorCat.type==1){
                            tag="D-";
                        }else {
                            tag="R-";
                        }
                        tag=tag+Integer.toString(cout);
                      }else{
                        if(StorCat.type==1){
                            tag="D-0";
                        }else {
                            tag="R-0";
                        }
                    }
                    cursor.close();
                    int i;
                    //проверка
                    ContentValues cv = new ContentValues();
                    cv.put(dataBaseThinkCash.CATEGORIES_KEY_TYPE,(int)StorCat.type);
                    cv.put(dataBaseThinkCash.CATEGORIES_KEY_NAME,edit.getText().toString());
                    if(rb1.isChecked()==true){
                        cv.put(dataBaseThinkCash.CATEGORIES_KEY_PROPERTY,(int)1);
                    }else {
                        cv.put(dataBaseThinkCash.CATEGORIES_KEY_PROPERTY,(int)2);
                    }
                    cv.put(dataBaseThinkCash.CATEGORIES_KEY_IMAGE,".");
                    cv.put(dataBaseThinkCash.CATEGORIES_KEY_TAG,tag);
                    database.insert(dataBaseThinkCash.TABLE_CATEGORIES,null,cv);
                    database.close();
                    fTrans= getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.FragCont,fragmentMain);
                    fTrans.commit();
                }
            }
        }
    }
}
