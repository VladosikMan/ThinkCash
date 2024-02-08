package ru.vladgad.thinkcash.FragAnalysis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.text.ParseException;
import java.util.ArrayList;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseFunction;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.DrawAn.GraphikTable;
import ru.vladgad.thinkcash.DrawAn.GraphikTable2;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Time.DataTime;
import ru.vladgad.thinkcash.thirdClass.Operations;
import static ru.vladgad.thinkcash.Mather.Mather.Max;

public class FragmentAnal extends Fragment {
    private  DataBaseThinkCash dataBaseThinkCash;
    private Context context;
    private GraphikTable graph1;
    private GraphikTable2 graph2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.frag_anal_anal,null);
        context = container.getContext();
        dataBaseThinkCash=new DataBaseThinkCash(context);
        graph1 =(GraphikTable)v.findViewById(R.id.graph1);
        graph2 =(GraphikTable2)v.findViewById(R.id.graph2);
      return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        try {
            ListGraph();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void ListGraph() throws ParseException {
        ArrayList<Operations> cat = new ArrayList<Operations>(1);
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        cat = DataBaseFunction.QueryBDO(dataBaseThinkCash,database,cat);
        switch (DataTime.typeInterval){
            case "d":{
                Max(cat,"d");
                break;
            }
            case "w":{
                Max(cat,"w");
                break;
            }
            case "m":{
                Max(cat,"m");
                break;
            }
            case "y":{
                Max(cat,"y");
                break;
            }
        }
        graph1.invalidate();
        graph2.invalidate();
        dataBaseThinkCash.close();
    }
}