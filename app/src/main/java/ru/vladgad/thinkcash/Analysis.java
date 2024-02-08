package ru.vladgad.thinkcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.text.ParseException;
import ru.vladgad.thinkcash.FragAnalysis.FragmentAdvice;
import ru.vladgad.thinkcash.FragAnalysis.FragmentAnal;
import ru.vladgad.thinkcash.FragAnalysis.FragmentPropert;
import ru.vladgad.thinkcash.FragAnalysis.FragmentRect;
import ru.vladgad.thinkcash.FragClass.FragmentMain;
import ru.vladgad.thinkcash.FragClass.FragmentMainDataInt;
import ru.vladgad.thinkcash.Jv.SwipeDetector;
import ru.vladgad.thinkcash.Storage.StorAct;
import ru.vladgad.thinkcash.Time.DataTime;

public class Analysis extends AppCompatActivity implements ViewGroup.OnClickListener {
    private static SwipeDetector swipeDetector;
    private FragmentTransaction fTrans;
    private FragmentAnal fragmentAnal;
    private FragmentMain fragmentMain;
    private FragmentAdvice fragmentAdvice;
    private TextView CurrData;
    private ImageView SHR, SHL;
    private FragmentMainDataInt fragdataint;
    private FragmentPropert fragmentPropert;
    private FragmentRect fragmentRect;
    private Button graph,diag,rect,adv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        graph=(Button)findViewById(R.id.graph);
        diag=(Button)findViewById(R.id.diag);
        rect=(Button)findViewById(R.id.rect);
        adv=(Button)findViewById(R.id.adv);
        graph.setOnClickListener(this);
        diag.setOnClickListener(this);
        rect.setOnClickListener(this);
        adv.setOnClickListener(this);
        fragmentAnal= new FragmentAnal();
        fragmentMain= new FragmentMain();
        fragmentRect = new FragmentRect();
        fragmentPropert=new FragmentPropert();
        fragdataint= new FragmentMainDataInt();
        fragmentAdvice = new FragmentAdvice();
        CurrData = (TextView)findViewById(R.id.CurrData);
        CurrData.setOnClickListener(this);
        SHR=(ImageView)findViewById(R.id.SHR);
        SHL=(ImageView)findViewById(R.id.SHL);
        SHR.setOnClickListener(this);
        SHL.setOnClickListener(this);
        String date = DataTime.StringDataInterVal();
        CurrData.setText(date);
        final Intent intentR;
        intentR = new Intent(this, Scores.class);
        final Intent intentL;
        intentL = new Intent(this, History.class);
        fTrans=getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.FragCont,fragmentAnal);
        fTrans.commit();
        swipeDetector = new SwipeDetector(ViewConfiguration.get(this).getScaledTouchSlop()) {
            @Override
            public void onSwipeDetected(Direction direction) {
                switch (direction){
                    case UP: {
                        break;
                    }
                    case DOWN:{
                        break;
                    }
                    case RIGHT:{
                        startActivity(intentR);
                        break;
                    }
                    case LEFT:{
                        startActivity(intentL);
                        break;
                    }
                    case UN_EXPT:{
                        break;
                    }
                    default:
                }
            }
        };
    }
    @Override
    protected void onStart() { super.onStart(); }
    @Override
    public void onClick(View v) {
        String date;
        switch (v.getId()){
            case R.id.graph:{
                fTrans=getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentAnal);
                fTrans.commit();
                break;
            }
            case R.id.diag:{
                fTrans=getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentPropert);
                fTrans.commit();
                break;
            }
            case R.id.rect:{
                fTrans=getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentRect);
                fTrans.commit();
                break;
            }
            case R.id.adv:{
                fTrans=getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragmentAdvice);
                fTrans.commit();
                break;
            }
             case R.id.SHL:{
                try {
                    DataTime.ShiftInterval((byte) 1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date = DataTime.StringDataInterVal();
                CurrData.setText(date);
                break;
            }
            case R.id.SHR:{
                try {
                    DataTime.ShiftInterval((byte) 2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date = DataTime.StringDataInterVal();
                CurrData.setText(date);
                break;
            }
            case R.id.CurrData:{
                StorAct.SetAct("a");
                fTrans= getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragdataint);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) { return swipeDetector.onTouchEvent(event); }
}