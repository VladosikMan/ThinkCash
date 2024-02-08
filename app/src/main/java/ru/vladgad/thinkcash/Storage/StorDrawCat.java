package ru.vladgad.thinkcash.Storage;

import java.util.ArrayList;
import ru.vladgad.thinkcash.thirdClass.Categories;

public class StorDrawCat {
    public static ArrayList<Categories> dog;
    public static int summ;
    public static byte type;
    public static void SetCat(ArrayList<Categories>cat,int summik,byte typik){
        dog =cat;
        summ=summik;
        type=typik;
    }
}
