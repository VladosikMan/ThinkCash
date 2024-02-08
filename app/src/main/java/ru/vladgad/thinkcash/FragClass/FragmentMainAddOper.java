package ru.vladgad.thinkcash.FragClass;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorCat;
import ru.vladgad.thinkcash.Time.DataTime;

public final class FragmentMainAddOper extends Fragment implements View.OnClickListener {
    private DataBaseThinkCash dataBaseThinkCash;
    private Context context;
    private TextView nameCat;
    private Spinner spinner;
    private EditText note;
    private EditText money;
    private Button cancel,ok,editing;
    private FragmentTransaction fTrans;
    private FragmentMain fragmentMain;
    private FragmentMainChanhCat fragmentMainChanhCat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main_addoper, null);
        fragmentMain = new FragmentMain();
        fragmentMainChanhCat = new FragmentMainChanhCat();
        context = container.getContext();
        nameCat = (TextView) v.findViewById(R.id.nameCat);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        note = (EditText) v.findViewById(R.id.note);
        money = (EditText) v.findViewById(R.id.money);
        editing = (Button) v.findViewById(R.id.editing);
        editing.setOnClickListener(this);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        ok = (Button) v.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        dataBaseThinkCash = new DataBaseThinkCash(context);
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        Cursor cursor = null;
        cursor = database.query(dataBaseThinkCash.TABLE_CATEGORIES, new String[]{DataBaseThinkCash.CATEGORIES_KEY_NAME}, dataBaseThinkCash.CATEGORIES_KEY_TAG + " = ?", new String[]{StorCat.tag.toString()}, null, null, null);
        if(cursor.moveToFirst()){
            int tagIndex = cursor.getColumnIndex(dataBaseThinkCash.CATEGORIES_KEY_NAME);
            String name = cursor.getString(tagIndex);
            nameCat.setText(name);
         }
        cursor.close();
        database.close();
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editing:{
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentMainChanhCat);
                fTrans.commit();
                break;
            }
            case R.id.cancel:{
                    note.setText("");
                    money.setText("");
                    spinner.setSelection(0);
                break;
            }
            case R.id.ok:{
                if(money.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getActivity(),
                            "Что такое пустота", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    byte c = (byte) spinner.getSelectedItemPosition();
                    c++;
                    String ss = StorCat.tag;
                    ss = ss.substring(0,1);
                    byte type;
                    if(ss.equalsIgnoreCase("d")){
                        type=1;
                    }else{
                        type=2;
                    }
                    SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
                    ContentValues cv= new ContentValues();
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_TAG,StorCat.tag);
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_TYPE,"S-"+Byte.toString(c));
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_NOTE,note.getText().toString());
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_QUANTITY,Integer.parseInt(money.getText().toString()));
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_DATA,Long.toString(DataTime.operTime));
                    database.insert(dataBaseThinkCash.TABLE_OPERATIONS,null,cv);
                    SharedPreferences sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    switch (c){
                        case 1:{
                            long mon = sPref.getLong("NAL",0);
                            if (type==1){
                            mon=mon+(long)Integer.parseInt(money.getText().toString());
                            }
                            else{
                                mon=mon-(long)Integer.parseInt(money.getText().toString());
                            }
                            ed.putLong("NAL",mon);
                            break;
                        }
                        case 2:{
                            long mon = sPref.getLong("EL",0);
                            if (type==1){
                                mon=mon+(long)Integer.parseInt(money.getText().toString());
                            }
                            else{
                                mon=mon-(long)Integer.parseInt(money.getText().toString());
                            }
                            ed.putLong("EL",mon);
                            break;
                        }
                        case 3:{
                            long mon = sPref.getLong("AK",0);
                            if (type==1){
                                mon=mon+(long)Integer.parseInt(money.getText().toString());
                            }
                            else{
                                mon=mon-(long)Integer.parseInt(money.getText().toString());
                            }
                            ed.putLong("AK",mon);
                            break;
                        }
                        case 4:{
                            long mon = sPref.getLong("KRI",0);
                            if (type==1){
                                mon=mon+(long)Integer.parseInt(money.getText().toString());
                            }
                            else{
                                mon=mon-(long)Integer.parseInt(money.getText().toString());
                            }
                            ed.putLong("KRI",mon);
                            break;
                        }
                    }
                    ed.commit();
                    fTrans= getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.FragCont,fragmentMain);
                    fTrans.commit();
                }
                break;
            }
        }
    }
}