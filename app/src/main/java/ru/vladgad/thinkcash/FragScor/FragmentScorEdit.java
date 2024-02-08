package ru.vladgad.thinkcash.FragScor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorScor;

public class FragmentScorEdit extends Fragment implements View.OnClickListener{
    private Context context;
    private FragmentTransaction fTrans;
    private FragmentScor fragmentScor;
    private TextView nameScor;
    private EditText money;
    private Button cancel,ok;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_scor_edit,null);
        fragmentScor = new FragmentScor();
        context=container.getContext();
        nameScor= (TextView)v.findViewById(R.id.nameScor);
        money=(EditText)v.findViewById(R.id.money);
        cancel=(Button)v.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        ok=(Button)v.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        SharedPreferences sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
        switch (StorScor.position){
            case 1:{
                nameScor.setText("Наличные");
                money.setText(Long.toString(sPref.getLong("NAL",0)));
                break;
            }
            case 2:{
                nameScor.setText("Электронные");
                money.setText(Long.toString(sPref.getLong("EL",0)));
                break;
            }
            case 3:{
                nameScor.setText("Акции");
                money.setText(Long.toString(sPref.getLong("AK",0)));
                break;
            }
            case 4:{
                nameScor.setText("Криптовалюта");
                money.setText(Long.toString(sPref.getLong("KRI",0)));
                break;
            }
        }
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:{
                SharedPreferences sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
                switch (StorScor.position){
                    case 1:{
                        nameScor.setText("Наличные");
                        money.setText(Long.toString(sPref.getLong("NAL",0)));
                        break;
                    }
                    case 2:{
                        nameScor.setText("Электронные");
                        money.setText(Long.toString(sPref.getLong("EL",0)));
                        break;
                    }
                    case 3:{
                        nameScor.setText("Акции");
                        money.setText(Long.toString(sPref.getLong("AK",0)));
                        break;
                    }
                    case 4:{
                        nameScor.setText("Криптовалюта");
                        money.setText(Long.toString(sPref.getLong("KRI",0)));
                        break;
                    }
                }
                break;
            }
            case R.id.ok:{
                SharedPreferences  sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                switch (StorScor.position){
                    case 1:{
                        ed.putLong("NAL",Long.parseLong(money.getText().toString()));
                        break;
                    }
                    case 2:{
                        ed.putLong("EL",Long.parseLong(money.getText().toString()));
                        break;
                    }
                    case 3:{
                        ed.putLong("AK",Long.parseLong(money.getText().toString()));
                        break;
                    }
                    case 4:{
                        ed.putLong("KRI",Long.parseLong(money.getText().toString()));
                        break;
                    }
                }
                ed.commit();
                fTrans= getFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentScor);
                fTrans.commit();
                break;
            }
        }
    }
}