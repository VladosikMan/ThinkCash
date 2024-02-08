package ru.vladgad.thinkcash.Storage;

import java.util.ArrayList;
import ru.vladgad.thinkcash.thirdClass.Operations;

public class StorGraph {
    public static int[] masikD;
    public static int[] masikR;
    public static ArrayList<Operations>cat;
    public static int maxD;
    public static int maxR;
    public static void SetGraph(ArrayList<Operations>ca,int[]masD,int[]masR,int maD,int maR){
            masikD=masD;
            masikR=masR;
            maxD=maD;
            maxR=maR;
            cat=ca;
    }
}
