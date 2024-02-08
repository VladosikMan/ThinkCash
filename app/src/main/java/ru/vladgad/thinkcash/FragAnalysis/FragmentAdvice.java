package ru.vladgad.thinkcash.FragAnalysis;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseFunction;
import ru.vladgad.thinkcash.DataBaseClass.DataBaseThinkCash;
import ru.vladgad.thinkcash.Mather.Mather;
import ru.vladgad.thinkcash.R;
import ru.vladgad.thinkcash.Storage.StorPropert;
import ru.vladgad.thinkcash.thirdClass.Categories;
import ru.vladgad.thinkcash.thirdClass.Operations;
// выполняется без привязки ко выбранному времни
public class FragmentAdvice extends Fragment {
    private DataBaseThinkCash dataBaseThinkCash;
    private Context context;
    private TextView analScor;
    private TextView analCat;
    private TextView analProper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_anal_adv,null);
        context = container.getContext();
        dataBaseThinkCash=new DataBaseThinkCash(context);
        analScor=(TextView)v.findViewById(R.id.analscor);
        analCat=(TextView)v.findViewById(R.id.analcat);
        analProper=(TextView)v.findViewById(R.id.analproper);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        ListAdv();
    }
    public void ListAdv() {
        //советы по счетам
        //смотрим суммарное состояние пользователя на данный момент
        String s="";
        SharedPreferences sPref = getContext().getSharedPreferences("MyPref", getContext().MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        //считали счета
        long nal,el,ak,kri;
        nal=sPref.getLong("NAL",0);
        el =sPref.getLong("EL",0);
        ak=sPref.getLong("AK",0);
        kri=sPref.getLong("KRI",0);
        long summ = nal+el+ak+kri;
        if(summ<=0){
            s="У вас проблемы с финансовым обеспечением. Пересмотрите эффективность ваших способов заработка или найдите новые";
        }else {
            if(summ<=(long)10330) {
                s = "Ваша финансовое состояние меньше прожиточного минимума, рекомендую вам пересмотреть ваши траты или эффективность способов заработка ";
            }else {
                if((summ>(long) 10330)&&(summ<(long)10000)){
                    s= "Ваше финансовое состояние стабильно, стараетесь не опускать планку и задумываетесь о вложениях ваших средств";
                    if(nal==0){
                        s=s+"Хотя сегодня роль наличных средств уменьшается, всё равно рекомендуется имень малую часть их для бытовых трат\n";
                    }
                    if(el==0){
                        s=s+"Электронные деньги являются главным средством обмена сегодня, крайне рекоменудется перенисти часть средств из других счетов\n";
                    }
                }else {
                    s="Судя по вашему финансовому состоянию, вас вполне можно считать хорошо обеспеченным. Не останавливаейтесь, инвистируйте свободные ресурсы в выгодные дела\n";
                    //можем анализиолва каждый счёт отдельно
                    if(nal==0){
                        s=s+"Хотя сегодня роль наличных средств уменьшается, всё равно рекомендуется имень малую часть их для бытовых трат\n";
                    }
                    if(el==0){
                        s=s+"Электронные деньги являются главным средством обмена сегодня, крайне рекоменудется перенисти часть средств из других счетов\n";
                    }
                    if(ak==0){
                        s=s+"Изучаете инвестирование, в долгосрочной перспективе очень пригодиться\n";
                    }
                    if(kri==0){
                        s=s+"Ещё одна форма инвестирования, возмонжно за ней будующие, рекомендуется к изучению\n";
                    }
                }
            }
        }
        analScor.setText(s);
        //счета сделали,теперь надо сделать выборку по всем операциям и расфосовать их по катериям
        ArrayList<Categories>dog = new ArrayList<>();
        ArrayList<Operations> cat = new ArrayList<Operations>(1);
        SQLiteDatabase database = dataBaseThinkCash.getWritableDatabase();
        dog= DataBaseFunction.QueryBD(dataBaseThinkCash,database,dog);
        cat = DataBaseFunction.QueryBDOR(dataBaseThinkCash,database,cat);
        int[] summCat = new int[dog.size()];
        int[] typeCat = new int[dog.size()];
        int i,j;
        for(i=0;i<dog.size();i++){
            if(dog.get(i).type==1){
                typeCat[i]=1;
            }else{
                typeCat[i]=2;
            }
        }
        for(i=0;i<cat.size();i++){
            for(j=0;j<dog.size();j++){
                if(cat.get(i).tager.equalsIgnoreCase(dog.get(j).tag)){
                    summCat[j]=summCat[j]+cat.get(i).quantity;
                }
            }
        }
        s="";
        //доходы
        for(i=0;i<dog.size();i++){
            if(typeCat[i]==1){
                if(summCat[i]==0){
                    s=s+"Категория "+dog.get(i).name+" не приносит вам дохода, возможно вас стоит пересмотреть её эффективность\n";
                }
            }
        }
        for(i=0;i<dog.size();i++){
            if(typeCat[i]==2){
                if(summCat[i]==0){
                    s=s+"Категория "+dog.get(i).name+" не имеет операций, возможно, её стоит удалить\n";
                }
            }
        }
        analCat.setText(s);
        s="";
        Mather.Diagram(cat);
        if(StorPropert.proD2>StorPropert.proD1){
            s=s+"Процентное соотношение основных и дополнительных доходов выходит в пользу вторых, возможно вам стоит пересмотреть важность категории,отказаться от малоэффективных категорий\n";
        }else {
            s=s+"Процентное соотношение доходов в норме\n";
        }
        if(StorPropert.proR2>15){
            s="Численность необязательных расходов переходит 15 процентов, пересмотрите свои расходы по не важным делам\n";
        }else{
            if(StorPropert.proR2>1){
            s=s+"Процентное соотношение расходов в норме\n";
            }else{
                s=s+"Необязательные расходы практически не фигирирует в вашей деятельности, можно спокойно увеличить количество\n";
            }
        }
        analProper.setText(s);
    }
}
