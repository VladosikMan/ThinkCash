package ru.vladgad.thinkcash.DrawAn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.text.SimpleDateFormat;
import ru.vladgad.thinkcash.Mather.Point;
import ru.vladgad.thinkcash.Storage.StorGraph;
import ru.vladgad.thinkcash.Time.DataTime;

public class GraphikTable extends ImageView {
    private Paint mPaint;
    private String[] week={"Пн","Вт","Ср","Чт","Пт","Сб","Вс"};
    private String[] year={"Я","Ф","М","А","М","И","И","А","С","О","Н","Д"};
    private String[] month;
    public GraphikTable(Context context, AttributeSet attrs) { super(context, attrs); }
    public GraphikTable(Context context,  AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }
    public GraphikTable(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        int i;
        byte size=1;
        //чертить горизонтальную линию с разметкой в зависимости от типа промежутка
        mPaint.setColor(Color.BLUE);
        //линии начертили, теперь разметку в зависимости от типа

        switch (DataTime.typeInterval){
            case "d":{
                size=8;
                canvas.drawLine(80,350,680,350,mPaint);//горизонталь
                //75
                for(i=0;i<=8;i++){
                     canvas.drawLine(80+i*75,340,80+i*75,360,mPaint);
                     //надпись
                    canvas.drawText(Integer.toString(i*3),80+i*75,370,mPaint);
                }
                break;
            }
            case "w":{
                size=7;
                canvas.drawLine(80,350,680,350,mPaint);//горизонталь
                //86
                for(i=0;i<=7;i++){
                    canvas.drawLine(80+i*86,340,80+i*86,360,mPaint);
                    //надпись
                    if(i>0)
                    canvas.drawText(week[i-1],80+i*86,370,mPaint);
                }
                break;
            }
            case "m":{
                size=10;
                //определить какой месяц
                SimpleDateFormat sdf = new SimpleDateFormat("M");
                String date = sdf.format(DataTime.operTime);
                byte mo = DataTime.HowDayOfMonth(Byte.parseByte(date));
                month= new String[]{"3","6","9","12","15","18","21","24","27","30"};
                switch (mo){
                    case 30:{
                        break;
                    }
                    case 31:{
                        month[9]="31";
                        break;
                    }
                    case 29:{
                        month[9]="29";
                        break;
                    }
                    case 28:{
                        month[9]="28";
                        break;
                    }
                }
                canvas.drawLine(80,350,680,350,mPaint);
                //60
                for(i=0;i<=10;i++){
                    canvas.drawLine(80+i*60,340,80+i*60,360,mPaint);
                    //надпись
                    if(i>0)
                        canvas.drawText(month[i-1],80+i*60,370,mPaint);
                }
                break;
            }
            case "y":{
                size=12;
                canvas.drawLine(80,350,680,350,mPaint);//горизонталь
                //50
                for(i=0;i<=12;i++){
                    canvas.drawLine(80+i*50,340,80+i*50,360,mPaint);
                    //надпись
                    if(i>0)
                        canvas.drawText(year[i-1],80+i*50,370,mPaint);
                }
                break;
            }
        }
        //вертикальная линия
        canvas.drawLine(80,350,80,40,mPaint);//вертикаль
        if(StorGraph.maxD>9){
            double ten = StorGraph.maxD/10;
            for(i=1;i<=10;i++){
                //30
                canvas.drawLine(70,350-i*30,90,350-i*30,mPaint);
                String form = String.format("%.2f", ten*i);
                canvas.drawText(form,10,350-i*30,mPaint);
            }
        }
        Point[] po =new Point[size+1];
        po[0] = new Point();
        po[0].x=80;
        po[0].y=350;
        double omec =((double)StorGraph.maxD)/300;
        for(i=1;i<=StorGraph.masikD.length;i++){
            po[i]=new Point();
           //найти горизонтальную и вертикальные точки
            switch (DataTime.typeInterval){
                case "d":{
                    // горизонтальная точка
                    po[i].x=80+i*75;
                    //вертикальная точка
                    po[i].y= (float) (350-(StorGraph.masikD[i-1]/omec));
                    break;
                }
                case "w":{
                    // горизонтальная точка
                    po[i].x=80+i*86;
                    //вертикальная точка
                    po[i].y= (float) (350-(StorGraph.masikD[i-1]/omec));
                    break;
                }
                case "m":{
                    // горизонтальная точка
                    po[i].x=80+i*60;
                    //вертикальная точка
                    po[i].y= (float) (350-(StorGraph.masikD[i-1]/omec));
                    break;
                }
                case "y":{
                    // горизонтальная точка
                    po[i].x=80+i*50;
                    //вертикальная точка
                    po[i].y= (float) (350-(StorGraph.masikD[i-1]/omec));
                    break;
                }
            }
        }
        mPaint.setColor(Color.RED);
        //чертить график
        for(i=0;i<po.length-1;i++){
            canvas.drawLine(po[i].x,po[i].y,po[i+1].x,po[i+1].y,mPaint);
        }
    }
}
