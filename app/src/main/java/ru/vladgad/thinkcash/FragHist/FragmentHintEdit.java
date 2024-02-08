package ru.vladgad.thinkcash.FragHist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import ru.vladgad.thinkcash.Storage.StorOper;

public class FragmentHintEdit extends Fragment implements  View.OnClickListener{
    private DataBaseThinkCash dataBaseThinkCash;
    private FragmentHist fragmentHist;
    private FragmentTransaction fTrans;
    private Context context;
    private TextView nameCat;
    private Spinner spinner;
    private EditText note;
    private EditText money;
    private Button cancel,ok,delete;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_hist_edit,null);
        context = container.getContext();
        nameCat = (TextView)v.findViewById(R.id.nameCat);
        spinner = (Spinner)v.findViewById(R.id.spinner);
        note = (EditText)v.findViewById(R.id.note);
        money=(EditText)v.findViewById(R.id.money);
        cancel=(Button)v.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        ok=(Button)v.findViewById(R.id.oker);
        ok.setOnClickListener(this);
        delete=(Button)v.findViewById(R.id.delete);
        delete.setOnClickListener(this);
        nameCat.setText(StorOper.name);
        note.setText(StorOper.note);
        money.setText(Integer.toString(StorOper.money));
        byte pos = Byte.parseByte(StorOper.type.toString().substring(2));
        if(pos<4){
            spinner.setSelection(pos-1);
        }
        fragmentHist= new FragmentHist();
        dataBaseThinkCash= new DataBaseThinkCash(context);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:{
                nameCat.setText(StorOper.name);
                note.setText(StorOper.note);
                money.setText(Integer.toString(StorOper.money));
                byte pos = Byte.parseByte(StorOper.type.toString().substring(2));
                if(pos<4){
                    spinner.setSelection(pos-1);
                }
                break;
            }
            case R.id.oker:{
                //проветр
                if((money.getText().toString().isEmpty())||(Integer.parseInt(money.getText().toString())<=0)){
                    Toast toast = Toast.makeText(getActivity(),
                            "Что такое пустота", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_QUANTITY, Integer.parseInt(money.getText().toString()));
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_NOTE, note.getText().toString());
                    byte pos = (byte) spinner.getSelectedItemPosition();
                    pos++;
                    String s = "S-";
                    s = s + Byte.toString(pos);
                    cv.put(dataBaseThinkCash.OPERATIONS_KEY_TYPE, s);
                    database.update(dataBaseThinkCash.TABLE_OPERATIONS, cv, dataBaseThinkCash.OPERATIONS_KEY_ID + " =?", new String[]{Integer.toString(StorOper.ido)});
                    database.close();
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.FragCont, fragmentHist);
                    fTrans.commit();
                }
                break;
            }
            case R.id.delete:{
                // здесь спросить у пользователя, а уверен ли он
                SQLiteDatabase database= dataBaseThinkCash.getWritableDatabase();
                database.delete(dataBaseThinkCash.TABLE_OPERATIONS,dataBaseThinkCash.OPERATIONS_KEY_ID+" =?", new String[]{Integer.toString(StorOper.ido)});
                database.close();
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentHist);
                fTrans.commit();
                break;
            }
        }
    }
}