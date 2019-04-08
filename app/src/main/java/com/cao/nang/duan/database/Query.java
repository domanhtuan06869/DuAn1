package com.cao.nang.duan.database;

public class Query {
    //bảng
    final static public String TABLE_DRUG_LIST="  TABLE_DRUG_LIST";//BẢNG DANH MỤC THUỐC
    final static public String TABLE_CATEGORY_DRUG="TABLE_CATEGORY_DRUG";//BẢNG THỂ LOẠI THUỐC
    final static public String TABLE_DRUG="TABLE_DRUG";// BẢNG THUỐC
    final static public String TABLE_SICK_LIST="TABLE_SICK_LIST";// BẢNG DANH SÁCH BỆNH
    final static public String TABLE_FIND_HOSPITAL="TABLE_FIND_HOSPITAL";// BẢNg tìm kiếm
    final static public String TABLE_HOSPITAL="TABLE_HOSPITAL";// BẢNG  bệnh viện
    final static public String TABLE_FIND_HOSPITAL_DISTRICT="TABLE_HOSPITAL_DISTICT";// BẢNG  bệnh viện


    ///

    public static final String DRUG_LIST="DRUG_LIST";//DANH MỤC THUỐC
    //THỂ LOẠI THUỐC
    final static public String DRUG_CATEGORY="DRUG_CATEGORY";//THỂ LOẠI THUỐC
    public static final String DRUG_CATEGORY_FOREIGN_KEY  = "foreign key ( "+DRUG_LIST+" ) references "+TABLE_CATEGORY_DRUG+"("+DRUG_LIST+") ";


    //THUỐC
    final static public String NAME_DRUG   ="NAME_DRUG";  //TÊN THUỐC
    final static public String PRICE_DRUG    ="PRICE_DRUG";// GIÁ THUỐC
    final static public String INGREDIENT_DRUG    ="INGREDIENT_DRUG";// thành phần thuốc
    final static public String ASSIGN_DRUG    ="ASSIGN_DRUG";//CHỈ ĐỊNH
    final static public String CONTRAINDICATED_DRUG    ="CONTRAINDICATED_DRUG"; ///CHỐNG CHỈ ĐỊNH
    final static public String USE_DRUG    ="USE_DRUG";// CÁCH DÙNG
    final static public String SIDE_EFFECTS    ="SIDE_EFFECTS"; //TÁC DỤNG PHỤ
    final static public String ATTENTION    ="ATTENTION"; //CHÚ Ý
    public static final String DRUG_FOREIGN_KEY  = "foreign key ( "+DRUG_CATEGORY+" ) references "+TABLE_DRUG+"("+DRUG_CATEGORY+") ";



    /////DANH MỤC BỆNH
    final  static public String SICK_NAME    ="SICK_NAME"; //TÊN BỆNH
    final public static String TREATMENT    ="TREATMENT"; // ĐIỀU TRỊ

    /// TÌM KIẾM BỆNH VIỆN THEO TỈNH /HUYỆN
    final  static public String PROVINCE    ="PROVINCE"; // TỈNH TP
    final  static public String DISTRICT    ="DISTRICT"; // QUẬN HUYỆN



    //// BỆNH VIỆN
    final  static public String  HOSPITAL_NAME    ="HOSPITAL_NAME "; // TÊN BV
    final  static public String  ADDRESS_HOSPITAL    ="ADDRESS_HOSPITAL"; //
    final  static public String  LONGITUDE    ="LONGITUDE"; // KINH ĐỘ
    final  static public String  LATITUDE    ="LATITUDE"; // VĨ ĐỘ
    public static final String HOSPITAL_FOREIGN_KEY  = "foreign key ( "+PROVINCE+" ) references "+TABLE_FIND_HOSPITAL+"("+PROVINCE+") ";


    //tạo bảng
    //danh mục

    public static final String create_TABLE_DRUG_LIST =
            "create table "+TABLE_DRUG_LIST+"(" +
                    "" + DRUG_LIST +" nvarchar  not null primary key)";

//thể loại
    public static final String create_TABLE_CATEGORY_DRUG =
            "create table "+TABLE_CATEGORY_DRUG+"(" +
                    "" + DRUG_CATEGORY +" nvarchar not null primary key,"+
                    "" + DRUG_LIST +" nvarchar)";
//thuốc
    public static final String create_TABLE_DRUG =
            "create table "+TABLE_DRUG+"(" +
                    "" + NAME_DRUG +" nvarchar not null primary key ,"+
                    "" + DRUG_CATEGORY  +" nvarchar,"+
                    "" + PRICE_DRUG +" nvarchar ,"+
                    "" + INGREDIENT_DRUG +" nvarchar ,"+
                    "" + ASSIGN_DRUG +" nvarchar,"+
                    "" + CONTRAINDICATED_DRUG +" nvarchar, "+
                    "" +  USE_DRUG+" nvarchar,"+
                    "" +SIDE_EFFECTS +" nvarchar, "+
                    "" +ATTENTION +" nvarchar"+
                    ")";

    public static final String create_TABLE_SICK_LIST =
            "create table "+TABLE_SICK_LIST+"(" +
                    "" + SICK_NAME +" nvarchar not null primary key,"
                    +TREATMENT +" nvarchar)";

    public static final String create_TABLE_FIND_HOSPITAL =
            "create table "+TABLE_FIND_HOSPITAL+"(" +
                    "" + PROVINCE +" nvarchar not null primary key)";

    public static final String create_TABLE_HOSPITAL=
            "create table "+TABLE_HOSPITAL+"(" +
                    "" + HOSPITAL_NAME +" nvarchar not null primary key,"+
                    "" + PROVINCE  +" nvarchar,"+
                    "" + DISTRICT +" nvarchar ,"+
                    "" + ADDRESS_HOSPITAL +" nvarchar ,"+
                    "" + LONGITUDE +" double,"+
                    "" + LATITUDE  +" double"+
                    ")";
    public  static  final String creat_TABLE_HOSPITAL_DISTRICT="create table "+TABLE_FIND_HOSPITAL_DISTRICT+"(" +
            ""+DISTRICT+" nvarchar not null primary key,"+
            "" + PROVINCE +" nvarchar"+
            ")";

}
