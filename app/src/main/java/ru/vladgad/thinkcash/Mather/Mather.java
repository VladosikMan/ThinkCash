package ru.vladgad.thinkcash.Mather;


import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import ru.vladgad.thinkcash.Storage.StorGraph;
import ru.vladgad.thinkcash.Storage.StorPropert;
import ru.vladgad.thinkcash.Storage.StorRect;
import ru.vladgad.thinkcash.Time.DataTime;
import ru.vladgad.thinkcash.thirdClass.Categories;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class Mather {
    public static ArrayList<Categories> CalkPercent(ArrayList<Categories> dog,int summD,int summR){
        int i;
        double oneD = ((double) summD)/100;
        double oneR =((double) summR)/100;
        Log.d("mLog", String.valueOf(oneD));
        Log.d("mLog", String.valueOf(oneR));
        for(i=0;i<dog.size();i++){
            Log.d("mLog", String.valueOf(dog.get(i).money));
            if (dog.get(i).type==1){
                if(summD!=0) {
                    dog.get(i).percent =  ((double) dog.get(i).money )/ oneD;
                }
            }else {
                if(summR!=0) {
                    dog.get(i).percent =((double) dog.get(i).money )/ oneR;
                }
            }
            Log.d("mLog", String.valueOf(dog.get(i).percent));
        }
        return dog;
    }
    public static void Max(ArrayList<Operations>cat,String type) throws ParseException {
        int i;
        //поиск максимальных и минимальных элеменов
        byte size=0;
        switch ( type){
            case "d":{
                size=8;
                break;
            }
            case "w":{
                size=7;
                break;
            }
            case  "m":{
                size=10;
                break;
            }
            case "y":{
                size=12;
            }
        }
        int masikD[] = new int[size];
        int masikR[] = new int[size];
        long hope[] = new long[size];
        //сформировать промежутки для рассчёта
        switch (type){
            case "d":{
                for (i=1;i<=8;i++){
                    hope[i-1]=DataTime.startTime+(3*i*60*60*1000);
                }
                break;
            }
            case "w":{
                for (i=1;i<=7;i++){
                    hope[i-1]=DataTime.startTime+(i*24*60*60*1000);
                }
                break;
            }
            case "m":{
                for(i=1;i<=9;i++){
                    hope[i-1]=DataTime.startTime+(3*i*24*60*60*1000);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("M");
                String date = sdf.format(DataTime.operTime);
                byte mo = DataTime.HowDayOfMonth(Byte.parseByte(date));
                mo = (byte) (mo-27);
                hope[9]=DataTime.startTime+(mo*i*24*60*60*1000);
                break;
            }
            case "y":{
                //получить какой год у нас и работать от этого
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String date = sdf.format(DataTime.operTime);

                sdf=new SimpleDateFormat("dd.MM.yyyy");
                String s="01.02."+date;
                Date ddt=sdf.parse(s);
                hope[0]=ddt.getTime();

                s="01.03."+date;
                ddt=sdf.parse(s);
                hope[1]=ddt.getTime();

                s="01.04."+date;
                ddt=sdf.parse(s);
                hope[2]=ddt.getTime();

                s="01.05."+date;
                ddt=sdf.parse(s);
                hope[3]=ddt.getTime();


                s="01.06."+date;
                ddt=sdf.parse(s);
                hope[4]=ddt.getTime();

                s="01.07."+date;
                ddt=sdf.parse(s);
                hope[5]=ddt.getTime();

                s="01.08."+date;
                ddt=sdf.parse(s);
                hope[6]=ddt.getTime();

                s="01.09."+date;
                ddt=sdf.parse(s);
                hope[7]=ddt.getTime();

                s="01.10."+date;
                ddt=sdf.parse(s);
                hope[8]=ddt.getTime();

                s="01.11."+date;
                ddt=sdf.parse(s);
                hope[9]=ddt.getTime();

                s="01.12."+date;
                ddt=sdf.parse(s);
                hope[10]=ddt.getTime();
                int year = Integer.parseInt(date);
                year++;
                date = Integer.toString(year);
                s="01.01."+date;
                ddt=sdf.parse(s);
                hope[11]=ddt.getTime();
                break;
            }
        }
        // рассчёт по
        for(i=0;i<cat.size();i++){
            if(cat.get(i).typecat==1){
                //доход
                switch (type){
                    case "d":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikD[0]=masikD[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikD[1]=masikD[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikD[2]=masikD[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikD[3]=masikD[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikD[4]=masikD[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikD[5]=masikD[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikD[6]=masikD[6]+cat.get(i).quantity;
                                                }
                                                else {
                                                    if(cur<hope[7]){
                                                        masikD[7]=masikD[7]+cat.get(i).quantity;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "w":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikD[0]=masikD[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikD[1]=masikD[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikD[2]=masikD[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikD[3]=masikD[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikD[4]=masikD[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikD[5]=masikD[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikD[6]=masikD[6]+cat.get(i).quantity;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case  "m":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikD[0]=masikD[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikD[1]=masikD[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikD[2]=masikD[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikD[3]=masikD[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikD[4]=masikD[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikD[5]=masikD[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikD[6]=masikD[6]+cat.get(i).quantity;
                                                }
                                                else {
                                                    if(cur<hope[7]){
                                                        masikD[7]=masikD[7]+cat.get(i).quantity;
                                                    }else {
                                                        if(cur<hope[8]){
                                                            masikD[8]=masikD[8]+cat.get(i).quantity;
                                                        }else {
                                                            if(cur<hope[9]){
                                                                masikD[9]=masikD[9]+cat.get(i).quantity;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "y":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikD[0]=masikD[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikD[1]=masikD[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikD[2]=masikD[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikD[3]=masikD[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikD[4]=masikD[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikD[5]=masikD[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikD[6]=masikD[6]+cat.get(i).quantity;
                                                }
                                                else {
                                                    if(cur<hope[7]){
                                                        masikD[7]=masikD[7]+cat.get(i).quantity;
                                                    }else {
                                                        if(cur<hope[8]){
                                                            masikD[8]=masikD[8]+cat.get(i).quantity;
                                                        }else {
                                                            if(cur<hope[9]){
                                                                masikD[9]=masikD[9]+cat.get(i).quantity;
                                                            }else {
                                                                if(cur<hope[10]){
                                                                    masikD[10]=masikD[10]+cat.get(i).quantity;
                                                                }else {
                                                                    if(cur<hope[11]){
                                                                        masikD[11]=masikD[11]+cat.get(i).quantity;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }else {
                //расход
                switch (type){
                    case "d":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikR[0]=masikR[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikR[1]=masikR[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikR[2]=masikR[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikR[3]=masikR[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikR[4]=masikR[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikR[5]=masikR[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikR[6]=masikR[6]+cat.get(i).quantity;
                                                }
                                                else {
                                                    if(cur<hope[7]){
                                                        masikR[7]=masikR[7]+cat.get(i).quantity;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "w":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikR[0]=masikR[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikR[1]=masikR[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikR[2]=masikR[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikR[3]=masikR[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikR[4]=masikR[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikR[5]=masikR[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikR[6]=masikR[6]+cat.get(i).quantity;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case  "m":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikR[0]=masikR[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikR[1]=masikR[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikR[2]=masikR[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikR[3]=masikR[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikR[4]=masikR[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikR[5]=masikR[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikR[6]=masikR[6]+cat.get(i).quantity;
                                                }
                                                else {
                                                    if(cur<hope[7]){
                                                        masikR[7]=masikR[7]+cat.get(i).quantity;
                                                    }else {
                                                        if(cur<hope[8]){
                                                            masikR[8]=masikR[8]+cat.get(i).quantity;
                                                        }else {
                                                            if(cur<hope[9]){
                                                                masikR[9]=masikR[9]+cat.get(i).quantity;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "y":{
                        long cur = Long.parseLong(cat.get(i).data);
                        if(cur<hope[0]){
                            masikR[0]=masikR[0]+cat.get(i).quantity;
                        }
                        else {
                            if(cur<hope[1]){
                                masikR[1]=masikR[1]+cat.get(i).quantity;
                            }else {
                                if(cur<hope[2]){
                                    masikR[2]=masikR[2]+cat.get(i).quantity;
                                }else {
                                    if(cur<hope[3]){
                                        masikR[3]=masikR[3]+cat.get(i).quantity;
                                    }else {
                                        if(cur<hope[4]){
                                            masikR[4]=masikR[4]+cat.get(i).quantity;
                                        }else{
                                            if(cur<hope[5]){
                                                masikR[5]=masikR[5]+cat.get(i).quantity;
                                            }else {
                                                if(cur<hope[6]){
                                                    masikR[6]=masikR[6]+cat.get(i).quantity;
                                                }
                                                else {
                                                    if(cur<hope[7]){
                                                        masikR[7]=masikR[7]+cat.get(i).quantity;
                                                    }else {
                                                        if(cur<hope[8]){
                                                            masikR[8]=masikR[8]+cat.get(i).quantity;
                                                        }else {
                                                            if(cur<hope[9]){
                                                                masikR[9]=masikR[9]+cat.get(i).quantity;
                                                            }else {
                                                                if(cur<hope[10]){
                                                                    masikR[10]=masikR[10]+cat.get(i).quantity;
                                                                }else {
                                                                    if(cur<hope[11]){
                                                                        masikR[11]=masikR[11]+cat.get(i).quantity;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        int maxD=0;
        int maxR=0;
        for(i=0;i<masikD.length;i++){
            if(masikD[i]>maxD){
                maxD=masikD[i];
            }
        }
        for(i=0;i<masikR.length;i++){
            if(masikR[i]>maxR){
                maxR=masikR[i];
            }
        }
        StorGraph.SetGraph(cat,masikD,masikR,maxD,maxR);
    }
    public static void Diagram(ArrayList<Operations>cat){
        //найти общую сумму
        //суммы первой и второй
        //дать им проценты
        int i;
        int maxD,maxR,maxD1,maxD2,maxR1,maxR2;
        maxD=0;
        maxR=0;
        maxD1 =0;
        maxD2 =0;
        maxR1 =0;
        maxR2 =0;
        double procD,procR;
        for(i=0;i<cat.size();i++){
            if(cat.get(i).typecat==1){
                if(cat.get(i).proper==1){
                    maxD1=maxD1+cat.get(i).quantity;
                }else{
                    maxD2=maxD2+cat.get(i).quantity;
                }
                maxD=maxD+cat.get(i).quantity;
            }else {
                if (cat.get(i).proper == 1) {
                    maxR1 = maxR1 + cat.get(i).quantity;
                 } else {
                    maxR2 = maxR2 + cat.get(i).quantity;
                }
                maxR = maxR + cat.get(i).quantity;
            }
        };
        double d1=0;
        double d2=0;
        double r1=0;
        double r2=0;
        procD=((double)maxD)/100;
        procR=((double)maxR)/100;
        if(procD!=0){
            d1 = ((double)maxD1)/procD;
            d2 = ((double)maxD2)/procD;
        }
        if(procR!=0){
            r1 = ((double)maxR1)/procR;
            r2 = ((double)maxR2)/procR;
        }
        StorPropert.SetProper(maxD,maxR,maxD1,maxD2,maxR1,maxR2,d1,d2,r1,r2);
    }
    public static void Cater(ArrayList<Operations>cat){
        int i;
        int maxD=0;
        int maxR=0;
        for(i=0;i<cat.size();i++){
            if (cat.get(i).typecat == 1) {
                maxD=maxD+cat.get(i).quantity;
            }else {
                maxR=maxR+cat.get(i).quantity;
            }
        }
        StorRect.SetRect(maxD,maxR);
        }
    }