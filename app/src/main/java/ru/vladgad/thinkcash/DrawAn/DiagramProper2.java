package ru.vladgad.thinkcash.DrawAn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import ru.vladgad.thinkcash.Storage.StorPropert;

public class DiagramProper2 extends ImageView {
    private Paint mPaint;
    public DiagramProper2(Context context) { super(context); }
    public DiagramProper2(Context context, AttributeSet attrs) { super(context, attrs); }
    public DiagramProper2(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        float w, h, cx, cy;
        w = getWidth();
        h = getHeight();
        cx = w / 2;
        cy = h / 2;
        float rad=150;
        mPaint.setColor(Color.BLUE); // установим белый цвет
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        final RectF oval = new RectF();
        oval.set(cx - rad, cy - rad, cx + rad, cy + rad);
        if((StorPropert.proR1==0)&&(StorPropert.proR2==0)){
            mPaint.setColor(Color.WHITE);
            canvas.drawCircle(cx,cy,rad,mPaint);
            canvas.drawText("0", cx-350, cy, mPaint);
            canvas.drawText("0", cx+250, cy, mPaint);

        }else
        {
            if(StorPropert.proR1<1){
                mPaint.setColor(Color.RED);
                canvas.drawArc(oval, 0, 360, true, mPaint);
            }else
            {
                if(StorPropert.proR2<2){
                    mPaint.setColor(Color.BLUE);
                    canvas.drawArc(oval, 0, 360, true, mPaint);
                }else {
                    float angle = (float) (StorPropert.proR1 * 3.6);
                    mPaint.setColor(Color.BLUE);
                    canvas.drawArc(oval, 0, angle, true, mPaint);
                    mPaint.setColor(Color.RED);
                    canvas.drawArc(oval, angle, 360 - angle, true, mPaint);
                }
                canvas.drawText(Integer.toString(StorPropert.maxR1)+"  "+ Double.toString(StorPropert.proR1)+"%", cx-350, cy, mPaint);
                canvas.drawText(Integer.toString(StorPropert.maxR2)+"  "+ Double.toString(StorPropert.proR2)+"%", cx+250, cy, mPaint);
            }
        }
    }
}
