package ru.vladgad.thinkcash.Jv;
import android.view.MotionEvent;
public abstract class SwipeDetector {
    private boolean isStarted=false;
    private float startX=0;
    private float startY=0;
    private int minToachLen=10;
    public SwipeDetector(int minToachLen) {
        this.minToachLen = minToachLen*5;
    }
    public abstract void onSwipeDetected(Direction direction);
    private double calcDist(float dx, float dy){
        return Math.sqrt(dx*dx+dy*dy);
    }
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                startX = event.getX();
                startY = event.getY();
                isStarted=true;
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                break;
            }
            case MotionEvent.ACTION_UP:{
                float dx = event.getX()-startX;
                float dy = event.getY()-startY;
                if(calcDist(dx,dy)>=minToachLen){
                    onSwipeDetected(Direction.get(calcAngle(dx,dy)));
                }
                startX=startY = 0;
                isStarted=false;
                break;
            }
            default:
                startX=startY=0;
                isStarted=false;
        }
        return false;
    }
    private int calcAngle(float dx,float dy){
        return (int)((Math.atan2(dy,dx)+Math.PI)*180/Math.PI+180)%360;
    }
    public enum Direction{
         UN_EXPT,
        LEFT,
        RIGHT,
        UP,
        DOWN;
         public static Direction get(int angle){
             Direction res = UN_EXPT;
             if(inRange(angle,45,135))res=UP;
             else if(inRange(angle,135,225))res=RIGHT;
             else if(inRange(angle,225,315))res=DOWN;
             else if(inRange(angle,315,360)||inRange(angle,0,45))res=LEFT;
             return res;
        }
        private  static  boolean inRange(int angle, int min, int max){
             return angle>= min && angle<=max;
        }
    }
}
