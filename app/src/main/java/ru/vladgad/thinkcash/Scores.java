package ru.vladgad.thinkcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import ru.vladgad.thinkcash.FragScor.FragmentScor;
import ru.vladgad.thinkcash.Jv.SwipeDetector;

public class Scores extends AppCompatActivity {
    private static SwipeDetector swipeDetector;
    private FragmentScor fragmentScor;
    private FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        fragmentScor = new FragmentScor();
        final Intent intentR;
        intentR = new Intent(this, MainActivity.class);
        final Intent intentL;
        intentL = new Intent(this, Analysis.class);
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
        fTrans.add(R.id.FragCont,fragmentScor);
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
}