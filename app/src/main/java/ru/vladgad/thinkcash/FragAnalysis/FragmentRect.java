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
import ru.vladgad.thinkcash.DrawAn.Rect;
import ru.vladgad.thinkcash.Mather.Mather;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class FragmentRect extends Fragment {
    private DataBaseThinkCash dataBaseThinkCash;
    private Context context;
    private Rect rect;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_anal_rect,null);
        context = container.getContext();
        dataBaseThinkCash=new DataBaseThinkCash(context);
        rect=(Rect)v.findViewById(R.id.rect);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        ListRect();
    }
    private void ListRect() {
        ArrayList<Operations> cat = new ArrayList<Operations>(1);
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        cat = DataBaseFunction.QueryBDO(dataBaseThinkCash,database,cat);
        Mather.Cater(cat);
        rect.invalidate();
        dataBaseThinkCash.close();
    }
}
