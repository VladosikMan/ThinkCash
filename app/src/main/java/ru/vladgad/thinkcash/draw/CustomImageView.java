package ru.vladgad.thinkcash.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import ru.vladgad.thinkcash.Storage.StorDrawCat;
import ru.vladgad.thinkcash.Storage.StorDrawCat1;

import static android.graphics.Paint.Style.*;

public class CustomImageView extends ImageView {
    private Paint mPaint;
    public CustomImageView(Context context) {
        super(context);
    }
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomImageView(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle);    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint = new Paint();
        mPaint.setStyle(STROKE);
        mPaint.setTextSize(20);
        mPaint.setSubpixelText(true);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        float w, h, cx, cy;
        w = getWidth();
        h = getHeight();
        cx = w / 2;
        cy = h / 2;
            mPaint.setColor(Color.BLACK);
            int rad = 130;
            int rad1 = 105;
            canvas.drawCircle(cx,cy,rad,mPaint);
            canvas.drawCircle(cx,cy,rad1,mPaint);
            float x,y,x1,y1,fi,fix;
            if(StorDrawCat.summ==0){
                fi=0;
                mPaint.setColor(Color.WHITE);
                while (fi<=100){
                    fix=(float)((3.6*fi)*Math.PI)/180;
                    x = (float) Math.floor(rad*Math.cos(fix));
                    y = (float) Math.floor(rad*Math.sin(fix));
                    x1 = (float) Math.floor(rad1*Math.cos(fix));
                    y1 = (float) Math.floor(rad1*Math.sin(fix));;
                    canvas.drawLine(x+cx,y+cy,x1+cx,y1+cy,mPaint);
                    fi= (float) (fi+0.1);
                }
                mPaint.setColor(Color.BLACK);
                canvas.drawText(Integer.toString(StorDrawCat.summ),cx,cy,mPaint);
            }else{
                double procef=0;
                mPaint.setColor(Color.WHITE);
                mPaint.setStrokeWidth(2);
                fi = (float) procef;
                x = (float) Math.floor(rad*Math.cos(fi));
                y = (float) Math.floor(rad*Math.sin(fi));
                x1 = (float) Math.floor(rad1*Math.cos(fi));
                y1 = (float) Math.floor(rad1*Math.sin(fi));
                canvas.drawLine(x+cx,y+cy,x1+cx,y1+cy,mPaint);
                int i;
                for(i=0;i<StorDrawCat.dog.size();i++){
                    byte type = 0;
                    String s = StorDrawCat1.dog.get(i).tag.substring(0, 1);
                    if (s.equals("D")) {
                        type = 1;
                    } else {
                        type = 2;
                    }
                    if(StorDrawCat.type==type){
                        if((StorDrawCat.dog.get(i).money!=0)&&(StorDrawCat.dog.get(i).percent>2)){
                            mPaint.setARGB(127,StorDrawCat.dog.get(i).r,StorDrawCat.dog.get(i).g,StorDrawCat.dog.get(i).b);
                            fi=(float)(fi+0.2);
                           procef = procef+StorDrawCat.dog.get(i).percent;
                            while (fi<procef){
                                fix=(float)((3.6*fi)*Math.PI)/180;
                                x = (float) Math.floor(rad*Math.cos(fix));
                                y = (float) Math.floor(rad*Math.sin(fix));
                                x1 = (float) Math.floor(rad1*Math.cos(fix));
                                y1 = (float) Math.floor(rad1*Math.sin(fix));;
                                canvas.drawLine(x+cx,y+cy,x1+cx,y1+cy,mPaint);
                                fi= (float) (fi+0.1);
                            }
                            mPaint.setColor(Color.WHITE);
                            fi = (float) procef;
                            fi = (float) ((3.6*fi)*Math.PI)/180;
                            x = (float) Math.floor(rad*Math.cos(fi));
                            y = (float) Math.floor(rad*Math.sin(fi));
                            x1 = (float) Math.floor(rad1*Math.cos(fi));
                            y1 = (float) Math.floor(rad1*Math.sin(fi));;
                            canvas.drawLine(x+cx,y+cy,x1+cx,y1+cy,mPaint);
                            fi = (float) procef;
                        }
                    }
                    }
                canvas.drawText(Integer.toString(StorDrawCat.summ), cx-20, cy, mPaint);
                }
        }
    }