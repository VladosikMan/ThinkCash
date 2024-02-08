package ru.vladgad.thinkcash.FragClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseFunction;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.Mather.Mather;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorCat;
import ru.vladgad.thinkcash.Storage.StorDrawCat;
import ru.vladgad.thinkcash.Storage.StorDrawCat1;
import ru.vladgad.thinkcash.draw.CustomImageView;
import ru.vladgad.thinkcash.draw.CustomImageView2;
import ru.vladgad.thinkcash.thirdClass.Categories;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class FragmentMain extends Fragment implements ViewGroup.OnClickListener {
    private static DataBaseThinkCash dataBaseThinkCash;
    private static LinearLayout Doxod,Rasxod;
    private static ImageView img[];
    private static TextView headText[],quaText[];
    private static TextView textMon,scor;
    private FragmentTransaction fTrans;
    private FragmentMainAddCat fragaddcat;
    private FragmentMainAddOper fragaddoper;
    private static Context context;
    private Button btnaddD,btnaddR;
    private static CustomImageView imgView;//аааааааааааааааааааааааааааааааааааааааааааааааа
    private static CustomImageView2 imgView2;//аааааааааааааааааааааааааааааааааааааааааааааааа
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main,null);
        textMon=(TextView)v.findViewById(R.id.textMoney);
        scor=(TextView)v.findViewById(R.id.score);
        btnaddD=(Button)v.findViewById(R.id.btnAddD);
        btnaddD.setOnClickListener(this);
        btnaddR=(Button)v.findViewById(R.id.btnAddR);
        btnaddR.setOnClickListener(this);
        fragaddcat= new FragmentMainAddCat();
        fragaddoper= new FragmentMainAddOper();
        context = container.getContext();
        Doxod=(LinearLayout)v.findViewById(R.id.Doxod);
        Rasxod=(LinearLayout)v.findViewById(R.id.Rasxod);
        dataBaseThinkCash=new DataBaseThinkCash(context);
        imgView = (CustomImageView)v.findViewById(R.id.imageView1);
        imgView2 = (CustomImageView2)v.findViewById(R.id.imageView2);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        SetView();
    }
   public  void SetView() {
        ArrayList<Categories> dog = new ArrayList<Categories>(1);
        ArrayList<Operations> cat = new ArrayList<Operations>(1);
        int i=0;
        int j=0;
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        dog = DataBaseFunction.QueryBD(dataBaseThinkCash,database,dog);
        cat = DataBaseFunction.QueryBDO(dataBaseThinkCash,database,cat);
        img = new ImageView[dog.size()];
        headText = new TextView[dog.size()];
        quaText= new TextView[dog.size()];
        Doxod.removeAllViews();
        Rasxod.removeAllViews();
        int SummD=0;
        int SummR=0;
        LinearLayout.LayoutParams lParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lParams.topMargin=15;
        lParams.bottomMargin=15;
        LinearLayout.LayoutParams imgparams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,100);

        for(i=0;i<dog.size();i++){
            headText[i]= new TextView(context);
            img[i] = new ImageView(context);
            quaText[i]= new TextView(context);
            headText[i].setText(dog.get(i).name);
            int Summ=0;
                for(j=0;j<cat.size();j++){
                    if(cat.get(j).tager.equalsIgnoreCase(dog.get(i).tag)){
                        Summ = Summ+cat.get(j).quantity;
                        if(cat.get(j).typecat==1){
                            SummD=SummD+cat.get(j).quantity;
                        }else
                        {
                            SummR=SummR+cat.get(j).quantity;
                        }
                    }
                }
               dog.get(i).money=Summ;
            quaText[i].setText(Integer.toString(Summ));
            img[i].setTag(dog.get(i).tag);
            headText[i].setTextColor(Color.argb(127,dog.get(i).r,dog.get(i).g,dog.get(i).b));
            quaText[i].setTextColor(Color.argb(127,dog.get(i).r,dog.get(i).g,dog.get(i).b));
            if(dog.get(i).type==1){
                img[i].setImageDrawable(getResources().getDrawable(R.drawable.avor));
                Doxod.addView(headText[i], lParams);
                img[i].setTag(dog.get(i).tag);
                Doxod.addView(img[i], imgparams);
                img[i].setOnClickListener(this);
                Doxod.addView(quaText[i], lParams);
            }else {
                img[i].setImageDrawable(getResources().getDrawable(R.drawable.sunin));
                Rasxod.addView(headText[i], lParams);
                img[i].setTag(dog.get(i).tag);
                Rasxod.addView(img[i], imgparams);
                img[i].setOnClickListener(this);
                Rasxod.addView(quaText[i], lParams);
            }
        }
        textMon.setText(Integer.toString(SummD-SummR));
       SharedPreferences sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
       SharedPreferences.Editor ed = sPref.edit();
       long mon = sPref.getLong("NAL",0);
        mon =mon+ sPref.getLong("EL",0);
        mon =mon+ sPref.getLong("AK",0);
        mon =mon+ sPref.getLong("KRI",0);
        ed.commit();
        scor.setText(Long.toString(mon));
       dog= Mather.CalkPercent(dog,SummD,SummR);
       StorDrawCat.SetCat(dog,SummD,(byte)1);
       imgView.invalidate();
       StorDrawCat1.SetCat(dog,SummR,(byte)2);
       imgView2.invalidate();
       dataBaseThinkCash.close();
    }
    @Override
    public  void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddD:{
                StorCat.SetType((byte) 1);
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragaddcat);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            }
            case R.id.btnAddR:{
                StorCat.SetType((byte) 2);
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragaddcat);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            }
            default:{
                StorCat.SetTag(v.getTag().toString());
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragaddoper);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            }
        }
    }
}