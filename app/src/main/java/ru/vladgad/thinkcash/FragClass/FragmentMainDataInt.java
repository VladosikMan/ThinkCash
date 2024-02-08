package ru.vladgad.thinkcash.FragClass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import ru.vladgad.thinkcash.FragAnalysis.FragmentAnal;
import ru.vladgad.thinkcash.FragHist.FragmentHist;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorAct;
import ru.vladgad.thinkcash.Time.DataTime;
public class FragmentMainDataInt extends Fragment implements View.OnClickListener{
    private Button day,week,month,year;
    private FragmentMain fragmentMain;
    private FragmentHist fragmentHist;
    private FragmentAnal fragmentAnal;
    private FragmentTransaction fTrans;
    private FragmentMain fragmain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main_dataint,null);
        fragmentMain = new FragmentMain();
        fragmentHist = new FragmentHist();
        fragmentAnal = new FragmentAnal();
        day = (Button)v.findViewById(R.id.day);
        day.setOnClickListener(this);
        week = (Button)v.findViewById(R.id.week);
        week.setOnClickListener(this);
        month = (Button)v.findViewById(R.id.month);
        month.setOnClickListener(this);
        year = (Button)v.findViewById(R.id.year);
        year.setOnClickListener(this);
        fragmentMain = new FragmentMain();
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.day:{
                try {
                    DataTime.ChangeInterval("d");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(DataTime.startTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.finishTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.operTime);
               date = DataTime.StringDataInterVal();
                ((TextView)getActivity().findViewById(R.id.CurrData)).setText(date);
                GetAct();
                break;
            }
            case R.id.week:{
                try {
                    DataTime.ChangeInterval("w");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(DataTime.startTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.finishTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.operTime);
                date = DataTime.StringDataInterVal();
                ((TextView)getActivity().findViewById(R.id.CurrData)).setText(date);
                GetAct();
                break;
            }
            case R.id.month:{
                try {
                    DataTime.ChangeInterval("m");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(DataTime.startTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.finishTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.operTime);
                 date = DataTime.StringDataInterVal();
                ((TextView)getActivity().findViewById(R.id.CurrData)).setText(date);
                GetAct();
                break;
            }
            case R.id.year:{
                try {
                    DataTime.ChangeInterval("y");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(DataTime.startTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.finishTime);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                date = sdf.format(DataTime.operTime);
                date = DataTime.StringDataInterVal();
                ((TextView)getActivity().findViewById(R.id.CurrData)).setText(date);
                GetAct();
                break;
            }
        }
    }
    private void GetAct(){
        switch (StorAct.type){
            case "m":{
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentMain);
                fTrans.commit();
                break;
            }
            case "h":{
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentHist);
                fTrans.commit();
                break;
            }
            case "a":{
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentAnal);
                fTrans.commit();
                break;
            }
        }
    }
}