package ru.vladgad.thinkcash.Storage;

public class StorOper {
    final String ATTRIBUTE_NAME_NAME = "name";
    final String ATTRIBUTE_NAME_NOTE = "note";
    final String ATTRIBUTE_NAME_DATA = "data";
    final String ATTRIBUTE_NAME_MONEY = "money";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    final String ATTRIBUTE_NAME_TYPE = "type";
    final String ATTRIBUTE_NAME_ID = "id";
    final String ATTRIBUTE_NAME_IDO = "ido";
    public static String name;
    public static String note;
    public static String data;
    public static int money;
    public static String type;
    public static int id;
    public static int ido;
    public static void SetName(String aname){ name=aname;}
    public static void SetNote(String anote){ note=anote;}
    public static void SetData(String adata){ data=adata;}
    public static void SetMoney(int amoney){ money=amoney;}
    public static void SetType(String atype){ type=atype;}
    public static void SetId(int aid){ id=aid;}
    public static void SetIdo(int aido){ ido=aido;}
}
