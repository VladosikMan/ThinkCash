package ru.vladgad.thinkcash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.ParseException;
import ru.vladgad.thinkcash.FragClass.FragmentMain;
import ru.vladgad.thinkcash.FragClass.FragmentMainDataInt;
import ru.vladgad.thinkcash.Storage.StorAct;
import ru.vladgad.thinkcash.Time.DataTime;
import ru.vladgad.thinkcash.Jv.SwipeDetector;

public class MainActivity extends AppCompatActivity implements ViewGroup.OnClickListener {
//open view
   private TextView CurrData;
   private ImageView SHR, SHL;
   private static SwipeDetector swipeDetector;
   private FragmentMain fragmain;
   private FragmentMainDataInt fragdataint;
   private FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            DataTime.CurrentTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fragmain=new FragmentMain();
        fragdataint=new FragmentMainDataInt();
        CurrData = (TextView)findViewById(R.id.CurrData);
        CurrData.setOnClickListener(this);
        SHR=(ImageView)findViewById(R.id.SHR);
        SHL=(ImageView)findViewById(R.id.SHL);
        SHR.setOnClickListener(this);
        SHL.setOnClickListener(this);
        String date = DataTime.StringDataInterVal();
        CurrData.setText(date);
        final Intent intentR;
        intentR = new Intent(this, History.class);
        final Intent intentL;
        intentL = new Intent(this, Scores.class);
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
            fTrans=getSupportFragmentManager().beginTransaction();
            fTrans.add(R.id.FragCont,fragmain);
            fTrans.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    public void onClick(View v) {
        String date;
        switch (v.getId()){
            case R.id.SHL:{
                try {
                    DataTime.ShiftInterval((byte) 1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date = DataTime.StringDataInterVal();
                CurrData.setText(date);
                    fragmain.SetView();
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

                    fragmain.SetView();
                break;
            }
            case R.id.CurrData:{
                StorAct.SetAct("m");
                fTrans= getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.FragCont,fragdataint);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeDetector.onTouchEvent(event);
    }
}