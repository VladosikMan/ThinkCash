package ru.vladgad.thinkcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.text.ParseException;
import ru.vladgad.thinkcash.FragClass.FragmentMainDataInt;
import ru.vladgad.thinkcash.FragHist.FragmentHist;
import ru.vladgad.thinkcash.Jv.SwipeDetector;
import ru.vladgad.thinkcash.Storage.StorAct;
import ru.vladgad.thinkcash.Time.DataTime;

public class History extends AppCompatActivity implements View.OnClickListener {
    private static SwipeDetector swipeDetector;
    private TextView CurrData;
    private ImageView SHR, SHL;
    private FragmentHist fragmentHist;
    private FragmentTransaction fTrans;
    private FragmentMainDataInt fragdataint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        fragdataint = new FragmentMainDataInt();
        CurrData = (TextView)findViewById(R.id.CurrData);
        CurrData.setOnClickListener(this);
        SHR=(ImageView)findViewById(R.id.SHR);
        SHL=(ImageView)findViewById(R.id.SHL);
        SHR.setOnClickListener(this);
        SHL.setOnClickListener(this);
        String date = DataTime.StringDataInterVal();
        CurrData.setText(date);
        fragmentHist = new FragmentHist();
        final Intent intentR;
        intentR = new Intent(this, Analysis.class);
        final Intent intentL;
        intentL = new Intent(this, MainActivity.class);
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
                }
            }
        };
        fTrans=getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.FragCont,fragmentHist);
        fTrans.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeDetector.onTouchEvent(event);
    }
    @Override
    public void onClick(View v) {
        String date;
            switch (v.getId()){
                case  R.id.SHL:{
                    try {
                        DataTime.ShiftInterval((byte) 1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    date = DataTime.StringDataInterVal();
                    CurrData.setText(date);
                    FragmentHist.ListLoad();
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
                    FragmentHist.ListLoad();
                    break;
                }
                case R.id.CurrData:{
                    StorAct.SetAct("h");
                    fTrans= getSupportFragmentManager().beginTransaction();
                    fTrans.replace(R.id.FragCont,fragdataint);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    break;
                }
            }
    }
}