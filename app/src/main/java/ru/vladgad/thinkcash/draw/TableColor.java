package ru.vladgad.thinkcash.draw;

public class TableColor {

    public static   Map[] map = new Map[10];
    public TableColor() {
        byte i;
        map[0]= new Map();
        map[0].r =  254;
        map[0].g =  0;
        map[0].b =  0;

        map[1]= new Map();
        map[1].r =  0;
        map[1].g =  254;
        map[1].b =  0;

        map[2]= new Map();
        map[2].r =  0;
        map[2].g =  0;
        map[2].b =  254;

        map[3]= new Map();
        map[3].r =  254;
        map[3].g =  254;
        map[3].b =  0;

        map[4]= new Map();
        map[4].r =  254;
        map[4].g =  0;
        map[4].b =  254;

        map[5]= new Map();
        map[5].r =  0;
        map[5].g =  254;
        map[5].b =  254;

        map[6]= new Map();
        map[6].r =  255;
        map[6].g =  127;
        map[6].b =  127;

        map[7]= new Map();
        map[7].r =  127;
        map[7].g =  254;
        map[7].b =  127;


        map[8]= new Map();
        map[8].r =  127;
        map[8].g =  127;
        map[8].b =  254;


        map[9]= new Map();
        map[9].r =  127;
        map[9].g =  127;
        map[9].b =  127;
    }
}
