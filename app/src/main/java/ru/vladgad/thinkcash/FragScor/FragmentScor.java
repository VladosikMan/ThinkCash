package ru.vladgad.thinkcash.FragScor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorScor;

public class FragmentScor extends Fragment {
    private FragmentTransaction fTrans;
    private ListView lvSimple;
    private SimpleAdapter sAdapter;
    private Context context;
    private FragmentScorEdit fragmentScorEdit;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_scor,null);
        lvSimple = (ListView)v.findViewById(R.id.lvSimple);
        context=container.getContext();
        fragmentScorEdit = new FragmentScorEdit();
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        ListLoad();
    }
    private void ListLoad() {
        String[]scor={"Наличные","Электронные","Акции","Криптовалюта"};
        ArrayList<Long>money = new ArrayList<Long>();
        ArrayList<Map<String,Object>> data = new ArrayList<Map<String, Object>>(4);
        Map<String,Object> m ;
        int i;
       SharedPreferences sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
        money.add(sPref.getLong("NAL",0));
        money.add(sPref.getLong("EL",0));
        money.add(sPref.getLong("AK",0));
        money.add(sPref.getLong("KRI",0));
        for(i=0;i<scor.length;i++){
            m = new HashMap<String, Object>();
            m.put("Name", scor[i]);
            m.put("Money",money.get(i));
            data.add(m);
        }
        String[]from={"Name","Money"};
        int [] to={R.id.nameScor,R.id.money};
        SimpleAdapter sAdapter = new SimpleAdapter(context,data,R.layout.scor,from,to);
        lvSimple.setAdapter(sAdapter);
        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentScorEdit);
                fTrans.addToBackStack(null);
                fTrans.commit();
                StorScor.SetPos((byte) (position+1));
            }
        });
    }
}