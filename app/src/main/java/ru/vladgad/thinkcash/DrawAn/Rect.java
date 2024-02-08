package ru.vladgad.thinkcash.DrawAn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import ru.vladgad.thinkcash.Storage.StorRect;

public class Rect extends ImageView {
    Paint mPaint;
    public Rect(Context context) { super(context); }
    public Rect(Context context,  AttributeSet attrs) { super(context, attrs); }
    public Rect(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE); // установим белый цвет
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        //нарисоват два прямоуголика
        //определить горизонтальную линию
        float w, h, cx, cy;
        w = getWidth();
        h = getHeight();
        cx = w / 2;
        cy = h / 2;
        if((StorRect.maxD!=0)||(StorRect.maxR!=0)){
          if(StorRect.maxD>StorRect.maxR){
                int max = StorRect.maxD;
                float pro = max /400;
                float size= ((float)StorRect.maxR)/pro;
                canvas.drawRect(cx-60,500,cx-40,100,mPaint);
                canvas.drawText(Integer.toString(StorRect.maxD),cx-80,100,mPaint);
                canvas.drawRect(cx+60,500,cx+40,500-size,mPaint);
                 canvas.drawText(Integer.toString(StorRect.maxR),cx+80,500-size,mPaint);
          }else{
              int max = StorRect.maxR;
              float pro = max /400;
              float size= ((float)StorRect.maxD)/pro;
              canvas.drawRect(cx-60,500,cx-40,500-size,mPaint);
              canvas.drawText(Integer.toString(StorRect.maxD),cx-80,500-size,mPaint);
              canvas.drawRect(cx+60,500,cx+40,100,mPaint);
              canvas.drawText(Integer.toString(StorRect.maxR),cx+80,100,mPaint);
          }
        }
    }
}