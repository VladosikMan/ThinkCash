package ru.vladgad.thinkcash.FragAnalysis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseFunction;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.DrawAn.DiagramProper;
import ru.vladgad.thinkcash.DrawAn.DiagramProper2;
import ru.vladgad.thinkcash.Mather.Mather;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class FragmentPropert extends Fragment {
    private DataBaseThinkCash dataBaseThinkCash;
    private Context context;
    private DiagramProper dia1;
    private DiagramProper2 dia2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_anal_proper,null);
        context = container.getContext();
        dataBaseThinkCash=new DataBaseThinkCash(context);
        dia1=(DiagramProper)v.findViewById(R.id.dia1);
        dia2=(DiagramProper2)v.findViewById(R.id.dia2);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        ListDiag();
    }
    public void ListDiag() {
        ArrayList<Operations> cat = new ArrayList<Operations>(1);
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        cat = DataBaseFunction.QueryBDO(dataBaseThinkCash,database,cat);
        Mather.Diagram(cat);
        dataBaseThinkCash.close();
        dia1.invalidate();
        dia2.invalidate();
    }
}
