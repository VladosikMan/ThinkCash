	""package ru.vladgad.thinkcash.FragClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.R;	
import ru.vladgad.thinkcash.Storage.StorCat;

public class FragmentMainChanhCat extends Fragment implements View.OnClickListener{
    private EditText editCat;
    private Button cancel,delete,ok;
    private TextView nameCat;
    private RadioButton rb1,rb2;
    private FragmentTransaction fTrans;
    private FragmentMain fragmentMain;
    private Context context;
    private DataBaseThinkCash dataBaseThinkCash;
    private String name;
    private int proper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main_changcat,null);
        context=container.getContext();
        fragmentMain= new FragmentMain();
        editCat=(EditText)v.findViewById(R.id.editCat);
        cancel=(Button)v.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        delete=(Button)v.findViewById(R.id.delete);
        delete.setOnClickListener(this);
        ok=(Button)v.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        nameCat=(TextView)v.findViewById(R.id.nameCat);
        rb1= (RadioButton)v.findViewById(R.id.main);
        rb2= (RadioButton)v.findViewById(R.id.additional);
        dataBaseThinkCash= new DataBaseThinkCash(context);
        SQLiteDatabase database= dataBaseThinkCash.getWritableDatabase();
        Cursor cursor=null;
        cursor = database.query(dataBaseThinkCash.TABLE_CATEGORIES, new String[]{DataBaseThinkCash.CATEGORIES_KEY_NAME,DataBaseThinkCash.CATEGORIES_KEY_PROPERTY}, dataBaseThinkCash.CATEGORIES_KEY_TAG + " = ?", new String[]{StorCat.tag.toString()}, null, null, null);
        cursor.moveToFirst();
        int tagIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_NAME);
        name = cursor.getString(tagIndex);
        int properIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_PROPERTY);
        proper = cursor.getInt(properIndex);
        nameCat.setText(name);
        if(proper==1){
            rb1.setChecked(true);
        }else {
            rb2.setChecked(true);
        }
        cursor.close();
        database.close();
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:{
                nameCat.setText(name);
                if(proper==1){
                    rb1.setChecked(true);
                }else {
                    rb2.setChecked(true);
                }
                break;
            }
            case R.id.delete:{
                //  здесь потом нужно дописать вопрос, нужно ли удалять
                SQLiteDatabase database= dataBaseThinkCash.getWritableDatabase();
                database.delete(dataBaseThinkCash.TABLE_CATEGORIES,dataBaseThinkCash.CATEGORIES_KEY_TAG+" =?", new String[]{StorCat.tag});
               //не удалил данные из второй таблицы дебич
                database.delete(dataBaseThinkCash.TABLE_OPERATIONS,dataBaseThinkCash.OPERATIONS_KEY_TAG+" =?",new String[]{StorCat.tag});
                database.close();
                break;
            }
            case R.id.ok:{
                if(editCat.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getActivity(),
                            "Пора покормить кота!", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    SQLiteDatabase database= dataBaseThinkCash.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(dataBaseThinkCash.CATEGORIES_KEY_NAME,editCat.getText().toString());
                    byte propery;
                    if(rb1.isChecked()==true){
                        propery=1;
                    }else {
                        propery=2;
                    }
                    cv.put(dataBaseThinkCash.CATEGORIES_KEY_PROPERTY,propery);
                    database.update(dataBaseThinkCash.TABLE_CATEGORIES,cv,dataBaseThinkCash.CATEGORIES_KEY_TAG+ " =?",new String[]{StorCat.tag});
                    database.close();
                    fTrans= getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.FragCont,fragmentMain);
                    fTrans.commit();
                }
                break;
            }
        }
    }
}