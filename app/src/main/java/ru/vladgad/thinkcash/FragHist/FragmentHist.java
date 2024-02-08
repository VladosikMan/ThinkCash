package ru.vladgad.thinkcash.FragHist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseFunction;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorOper;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class FragmentHist ex	tends Fragment {
    private static ListView lvSimple;
    private SimpleAdapter sAdapter;
    private static DataBaseThinkCash dataBaseThinkCash;
    static final int imgD = R.drawable.avor;
    static final int imgR = R.drawable.sunin;
    private static Context context;
    private static FragmentHintEdit fragmentHintEdit;
    private static FragmentTransaction fTrans;
    static final String ATTRIBUTE_NAME_NAME = "name";
    static final String ATTRIBUTE_NAME_NOTE = "note";
    static final String ATTRIBUTE_NAME_DATA = "data";
    static final String ATTRIBUTE_NAME_MONEY = "money";
    static final String ATTRIBUTE_NAME_IMAGE = "image";
    static final String ATTRIBUTE_NAME_TYPE = "type";
    static final String ATTRIBUTE_NAME_ID = "id";
    static final String ATTRIBUTE_NAME_IDO = "ido";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_hist,null);
        context = container.getContext();
        dataBaseThinkCash=new DataBaseThinkCash(context);
        lvSimple = (ListView)v.findViewById(R.id.lvSimple);
        fragmentHintEdit = new FragmentHintEdit();
        Raf();
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        ListLoad();
    }
   public static void ListLoad(){
        ArrayList<Operations> dog = new ArrayList<Operations>();
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        dog = DataBaseFunction.QueryBDO(dataBaseThinkCash,database,dog);
        int i;
        ArrayList<Map<String,Object>> data = new ArrayList<Map<String, Object>>(4);
        Map<String,Object> m ;
        for(i=0;i<dog.size();i++){
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_NAME, dog.get(i).tag);
            m.put(ATTRIBUTE_NAME_NOTE,dog.get(i).note);
            m.put(ATTRIBUTE_NAME_MONEY,dog.get(i).quantity);
            m.put(ATTRIBUTE_NAME_DATA,dog.get(i).data);
            if(dog.get(i).typecat==1) {
                m.put(ATTRIBUTE_NAME_IMAGE, imgD);
            }else{
                m.put(ATTRIBUTE_NAME_IMAGE, imgR);
            }
            m.put(ATTRIBUTE_NAME_ID,dog.get(i).typecat);
            m.put(ATTRIBUTE_NAME_TYPE,dog.get(i).type);
            m.put(ATTRIBUTE_NAME_IDO,dog.get(i).id);
            data.add(m);
        }
        String[]from={ATTRIBUTE_NAME_NAME,ATTRIBUTE_NAME_NOTE,ATTRIBUTE_NAME_DATA,ATTRIBUTE_NAME_MONEY,ATTRIBUTE_NAME_IMAGE,ATTRIBUTE_NAME_ID,ATTRIBUTE_NAME_TYPE,ATTRIBUTE_NAME_IDO};
        int [] to={R.id.nameCat,R.id.note,R.id.scores,R.id.money,R.id.ivImg, R.id.id,R.id.type,R.id.ido};
        SimpleAdapter sAdapter = new SimpleAdapter(context,data,R.layout.item,from,to);
        lvSimple.setAdapter(sAdapter);
    }
    private void Raf(){
        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView nameCat = (TextView)view.findViewById(R.id.nameCat);
                StorOper.SetName(nameCat.getText().toString());
                nameCat = (TextView)view.findViewById(R.id.note);
                StorOper.SetNote(nameCat.getText().toString());
                nameCat = (TextView)view.findViewById(R.id.note);
                StorOper.SetNote(nameCat.getText().toString());
                nameCat = (TextView)view.findViewById(R.id.scores);
                StorOper.SetData(nameCat.getText().toString());
                nameCat = (TextView)view.findViewById(R.id.money);
                StorOper.SetMoney(Integer.parseInt(nameCat.getText().toString()));
                nameCat = (TextView)view.findViewById(R.id.id);
                StorOper.SetId(Integer.parseInt(nameCat.getText().toString()));
                nameCat = (TextView)view.findViewById(R.id.ido);
                StorOper.SetIdo(Integer.parseInt(nameCat.getText().toString()));
                nameCat = (TextView)view.findViewById(R.id.type);
                StorOper.SetType(nameCat.getText().toString());
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentHintEdit);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });
    }
}