package ru.vladgad.thinkcash.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DataTime {
    //класс для работы со временем, имеет 3 статических поля и 6 методов
        //поля
            //начало промежутка времени
            //конец промежутка времени
            //тип промежутка
   public static long operTime = System.currentTimeMillis();
   public static long startTime,finishTime;
   public static String typeInterval = "d";
    //методы
            //1-Изменить тип промежутка времени.На входе - новый тип
            //2-Сместить промежутки по типу.
            //3-Установить текущее время
            //4-Отправка даты для создания операции. На выходе - дата для проведения операции
            //5-Сформировать строку для вывода. На выходе - строка для вывожа
            //6-Установить промежутки дат для выборки. На выходе - сфомированное начало промежутка времени, сформированный конец промежутка времени
   public static void  ChangeInterval(String newType) throws ParseException {
       if(typeInterval!=newType){
           typeInterval=newType;
           switch (typeInterval){
               case "d":{
                   SetIntervalQuery();
                   break;
               }
               case "w":{
                    operTime = TimeDiff(operTime);
                    SimpleDateFormat  sdf = new SimpleDateFormat("EEEE");
                    String date = sdf.format(operTime);
                    byte dayWeek = DataTime.DayWeek(date);
                    byte diff = (byte) (dayWeek-1);
                    operTime = TimeDiff(operTime);
                    operTime = operTime-(diff*(24*60*60*1000));
                    SetIntervalQuery();
                    break;
               }
               case "m":{
                   operTime = TimeDiff(operTime);
                   SimpleDateFormat  sdf = new SimpleDateFormat("d");
                   String date = sdf.format(operTime);
                   byte dayMonth = Byte.parseByte(date);
                   byte diff=(byte)(dayMonth-1);
                   operTime =(long) operTime- ((long)diff*24*60*60*1000);
                   sdf = new SimpleDateFormat("dd.MM.yyyy");
                   date = sdf.format(DataTime.operTime);
                   SetIntervalQuery();
                   break;
               }
               case "y":{
                   operTime = TimeDiff(operTime);
                   SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                   String date = sdf.format(operTime);
                   Date ddt = sdf.parse(date);
                   long Time=ddt.getTime();
                   Time = operTime-Time;
                   sdf = new SimpleDateFormat("yyyy");
                   date = "01.01.";
                   date =date+ sdf.format(operTime);
                   sdf = new SimpleDateFormat("dd.MM.yyyy");
                   ddt = sdf.parse(date);
                   operTime=ddt.getTime();
                   operTime=operTime+Time;
                   SetIntervalQuery();
                   break;
               }
           }
       }
   }
   public  static void ShiftInterval(byte shr) throws ParseException {
        switch (typeInterval){
            case "d":{
                if(shr==1){
                    operTime = TimeDiff(operTime);
                    operTime = operTime-(24*60*60*1000);
                }else{
                    operTime = TimeDiff(operTime);
                    operTime = operTime+(24*60*60*1000);
                }
                SetIntervalQuery();
                break;
            }
            case "w":{
                if(shr==1){
                    operTime = TimeDiff(operTime);
                    operTime = operTime-(7*(24*60*60*1000));
                }else{
                    operTime = TimeDiff(operTime);
                    operTime = operTime+(7*(24*60*60*1000));
                }
                SetIntervalQuery();
                break;
            }
            case "m":{
                operTime = TimeDiff(operTime);
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(operTime);
                Date ddt = sdf.parse(date);
                long Time=ddt.getTime();
                Time = operTime-Time;
                sdf = new SimpleDateFormat("M");
                date = sdf.format(operTime);
                sdf = new SimpleDateFormat("yyyy");
                int year =Integer.parseInt(sdf.format(operTime));
                byte month = Byte.parseByte(date);
                if(shr==1){
                    if(month==1){
                        month=12;
                        year--;
                    }
                    else {
                        month--;
                    }
                }else{
                    if(month==12){
                        month=1;
                        year++;
                    }
                    else {
                        month++;
                    }
                }
                if(month<10){
                    date="01.0"+Byte.toString(month)+"."+Integer.toString(year);
                }else {
                    date="01."+Byte.toString(month)+"."+Integer.toString(year);
                }
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                ddt = sdf.parse(date);
                operTime=ddt.getTime();
                operTime=operTime+Time;
                SetIntervalQuery();
                break;
            }
            case "y":{
                operTime = TimeDiff(operTime);
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date = sdf.format(operTime);
                Date ddt = sdf.parse(date);
                long Time=ddt.getTime();
                Time = operTime-Time;
                sdf = new SimpleDateFormat("yyyy");
                int year =Integer.parseInt(sdf.format(operTime));
                if(shr==1){
                    year--;
                }else{
                    year++;
                }
                date="01.01."+Integer.toString(year);
                sdf = new SimpleDateFormat("dd.MM.yyyy");
                ddt = sdf.parse(date);
                operTime=ddt.getTime();
                operTime=operTime+Time;
                SetIntervalQuery();
                break;
            }
        }
   }
    public static void CurrentTime() throws ParseException {
      // operTime= System.currentTimeMillis();
        SetIntervalQuery();
   }
   public static String StringDataInterVal(){
       String s ="";
       switch (typeInterval){
           case "d":{
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               s = sdf.format(operTime);
               break;
           }
           case "w":{
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               s = sdf.format(startTime);
               sdf = new SimpleDateFormat("dd.MM.yyyy");
               s += " - "+sdf.format(finishTime);
               break;
           }
           case "m":{
               SimpleDateFormat sdf = new SimpleDateFormat("M");
               byte month =Byte.parseByte(sdf.format(operTime));
               switch (month){
                   case 1:
                       s="Январь";
                       break;
                   case 2:
                       s="Февраль";
                       break;
                   case 3:
                       s="Март";
                       break;
                   case 4:
                       s="Апрель";
                       break;
                   case 5:
                       s="Май";
                       break;
                   case 6:
                       s="Июнь";
                       break;
                   case 7:
                       s="Июль";
                       break;
                   case 8:
                       s="Август";
                       break;
                   case 9:
                       s="Сентябрь";
                       break;
                   case 10:
                       s="Октябрь";
                       break;
                   case 11:
                       s="Ноябрь";
                       break;
                   case 12:
                       s="Декабрь";
                       break;
               }
               sdf = new SimpleDateFormat("yyyy");
               int year =Integer.parseInt(sdf.format(operTime));
               s=s+" "+Integer.toString(year)+" год";
               break;
           }
           case "y":{
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
               int year =Integer.parseInt(sdf.format(operTime));
               s=Integer.toString(year) +" - год";
               break;
           }
       }
       return s;
   }
   public static void SetIntervalQuery() throws ParseException {
       switch (typeInterval){
           case "d":{
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               String date = sdf.format(operTime);
               Date ddt = sdf.parse(date);
               startTime=ddt.getTime();
               finishTime = startTime+(24*60*60*1000);
               break;
           }
           case "w":{
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               String date = sdf.format(operTime);
               Date ddt = sdf.parse(date);
               startTime=ddt.getTime();
               finishTime=startTime+(7*(24*60*60*1000));
               break;
           }
           case  "m":{
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               String date = sdf.format(operTime);
               Date ddt = sdf.parse(date);
               startTime=ddt.getTime();
               sdf = new SimpleDateFormat("M");
               date = sdf.format(operTime);
               byte how = DataTime.HowDayOfMonth(Byte.parseByte(date));
               finishTime=(long)startTime+((long)how*24*60*60*1000);
               break;
           }
           case "y":{
               long startstarttime =operTime;
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
               String date = "01.01.";
               date =date+ sdf.format(operTime);
               sdf = new SimpleDateFormat("dd.MM.yyyy");
               Date ddt = sdf.parse(date);
               startTime=ddt.getTime();
               sdf = new SimpleDateFormat("yyyy");
               date = "31.12.";
               date =date+ sdf.format(startstarttime);
               sdf = new SimpleDateFormat("dd.MM.yyyy");
               ddt = sdf.parse(date);
               finishTime=ddt.getTime();
               break;
           }
       }
   }
    private static long TimeDiff(long OperTimer) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(System.currentTimeMillis());
        Date ddt = sdf.parse(date);
        long timer=ddt.getTime();
        timer = System.currentTimeMillis()-timer;
        date = sdf.format(OperTimer);
        ddt = sdf.parse(date);
        OperTimer = ddt.getTime()+timer;
        return  OperTimer;
    }
private static byte DayWeek(String dayweek){
       byte day=1;
       switch (dayweek){
           case "понедельник":day=1;
           break;
           case "вторник":day=2;
               break;
           case "среда":day=3;
               break;
           case "четверг":day=4;
               break;
           case "пятница":day=5;
               break;
           case "суббота":day=6;
               break;
           case "воскресенье":day=7;
               break;
       }
       return  day;
}
public static boolean LeapYear(int year) {
    boolean flag=false;
    if(year%4==0){
        flag=true;
        if(year%100==0){
            if(year%400==0){
                flag=true;
            }
        }else {
            flag=true;
        }
    }
return flag;
}
public static byte HowDayOfMonth(byte month){
       switch (month){
           case 1:month=31;
               break;
           case 2:
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    String date = sdf.format(startTime);
                    boolean leap = false;
                    leap= DataTime.LeapYear(Integer.parseInt(date));
                    if(leap) month=29;
                    else
                        month=28;
               break;
           case 3:month=31;
               break;
           case 4:month=30;
               break;
           case 5:month=31;
               break;
           case 6:month=30;
               break;
           case 7:month=31;
               break;
           case 8:month=31;
               break;
           case 9:month=30;
               break;
           case 10:month=31;
               break;
           case 11:month=30;
               break;
           case 12:month=31;
               break;
       }
       return month;
    };
}