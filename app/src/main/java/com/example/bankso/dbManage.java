package com.example.bankso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class dbManage extends SQLiteOpenHelper {
    public static final int DatabaseVersion = 1;
    public static final String DataBaseName = "Bank4";
    private static final String TABLE_NAME = "user";
    private static final String ID_COL = "id";

    private static final String Name_Col="name";
    private static final String Gender_Col="gender";
    private static final String Email_COL = "email";
    private static final String Phone_COL = "phone";
    private static final String Address_COL = "address";
    private static final String Passward_COL = "passward";
    private static  final String Amount_COL = "amount";
    private static final String Role_COL = "role";

    private static final String QID_COL = "qid";
    private static final String Deposit_COL = "deposit";
    private static final String Withdraw_COL = "withdraw";

    private static final String QTABLE_NAME = "amount";
    private static final String PID_COL = "previd";



    public dbManage(Context context) {
        super(context, DataBaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Name_Col + " TEXT,"
                + Gender_Col + " TEXT,"
                + Email_COL + " TEXT,"
                + Phone_COL + " TEXT,"
                + Address_COL + " TEXT, "
                + Passward_COL + " TEXT,"
                + Amount_COL + " Text, "
                + Role_COL + " TEXT)";

        String query1 = "CREATE TABLE " + QTABLE_NAME + " ("
                + QID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PID_COL + " TEXT,"
                + Deposit_COL + " TEXT,"
                + Withdraw_COL + " TEXT)";


        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QTABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewUser(String name,String gender,String email,String phonenum, String address,String passward,String role) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Name_Col,name);
        value.put(Gender_Col,gender);
        value.put(Email_COL, email);
        value.put(Phone_COL, phonenum);
        value.put(Address_COL, address);
        value.put(Passward_COL, passward);
        value.put(Role_COL, role);
        value.put(Amount_COL, "0" );

        sqLiteDatabase.insert(TABLE_NAME, null, value);
        sqLiteDatabase.close();
    }

    boolean login(String email1, String passward) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "select * from user where email='" + email1 + "' and passward='" + passward + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    boolean Adminlogin(String email,String passward){
        String role1="1";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql = "select * from user where email='" + email + "' and passward='" + passward + "' and role='" + role1 +"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    String getName(String email1,String passward){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select name from user where email='" + email1 + "' and passward='" + passward + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String name=cursor.getString(0);
            return name;
        }
        else{
            return "";
        }
    }

    String getemail(String name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select email from user where name='" + name + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String email=cursor.getString(0);
            return email;
        }
        else{
            return "";
        }
    }

    String getpassward(String name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select passward from user where name='" + name + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String pass=cursor.getString(0);
            return pass;
        }
        else{
            return "";
        }
    }

    String getPassward(String email1,String passward){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select passward from user where email='" + email1 + "' and passward='" + passward + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String pass=cursor.getString(0);
            return pass;
        }
        else{
            return "";
        }
    }

    String getaccountnumber(String email1,String passward){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select id from user where email='" + email1 + "' and passward='" + passward + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String accountnum="0112 3397 4299 12"+cursor.getString(0);
            return accountnum;
        }
        else{
            return "";
        }
    }

    String getaccountnumber1(String name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select id from user where name='" + name + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String accountnum="01123397429912"+cursor.getString(0);
            return accountnum;
        }
        else{
            return "";
        }
    }

    public void addAmount(String pid,String deposit,String withdraw){
        SQLiteDatabase sq= this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(PID_COL,pid);
        value.put(Deposit_COL,deposit);
        value.put(Withdraw_COL,withdraw);
        sq.insert(QTABLE_NAME,null,value);
        sq.close();
    }

    public void withdrawAmount(String pid,String deposit,String withdraw){
        SQLiteDatabase sq= this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(PID_COL,pid);
        value.put(Deposit_COL,deposit);
        value.put(Withdraw_COL,withdraw);
        sq.insert(QTABLE_NAME,null,value);
        sq.close();
    }
//ArrayList<HashMap<String, String>>
    public ArrayList<HistoryModel> showData(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql= "select qid, deposit, withdraw from amount where previd='" + id + "'";
        Cursor cursor= db.rawQuery(sql,null);
        ArrayList<HistoryModel> userhistroy=new ArrayList<>();
        while(cursor.moveToNext()){
            HistoryModel historyModel=new HistoryModel();
            historyModel.id =cursor.getString(0);
            historyModel.deposit=cursor.getString(1);
            historyModel.withdraw=cursor.getString(2);
            userhistroy.add(historyModel);
        }
        return userhistroy;
    }

    public String getDetail(String email,String passward){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql= "select id, name, gender, email, phone, address, passward from user where email='" + email + "' and passward='" + passward + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String detail="Account number: "+"01123397429912"+cursor.getString(0)+"\n"
                          +" Name: "+cursor.getString(1)+"\n"
                          +" Gender:"+cursor.getString(2)+"\n"
                          +" Email:"+cursor.getString(3)+"\n"
                          +" Phone Number:"+cursor.getString(4)+"\n"
                          +" Address:"+cursor.getString(5)+"\n"
                          +" Passward:"+cursor.getString(6);
            return detail;
        }
        else{
            return "";
        }
    }

    public String getAmount(String email1,String passward){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select amount from user where email='" + email1 + "' and passward='" + passward + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String bal=cursor.getString(0);
            return bal;
        }
        else{
            return "";
        }
    }


    public String getid(String email1,String passward){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql= "select id from user where email='" + email1 + "' and passward='" + passward + "'";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            String id=cursor.getString(0);
            return id;
        }
        else{
            return "";
        }
    }

    public boolean updateAmt(String amt,String email1,String passward){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String sql= "update amt from user where email='" + email1 + "' and passward='" + passward + "'";
     //   String sql= "UPDATE user SET Amount_COL = Amount_COL+amt WHERE email='" + email1 + "' and passward='" + passward + "'" ;
        ContentValues contentValues=new ContentValues() ;
        contentValues.put(Amount_COL,amt);
        int l=sqLiteDatabase.update(TABLE_NAME,contentValues,"email=?",new String[]{email1});
        if(l>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int deleteUser(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int ans=sqLiteDatabase.delete(TABLE_NAME, "email=?", new String[]{email});
        return ans;
    }
}
