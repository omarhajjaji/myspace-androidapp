package com.isamm.myspace;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MySpace.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strSql = "create table Player ("
                +"idPlayer integer primary key autoincrement,"
                +"name text not null,"
                +"lastname text not null,"
                +"login text not null unique,"
                +"password text not null,"
                +"age integer not null,"
                +"score integer not null,"
                +"niveau integer not null)";
        sqLiteDatabase.execSQL(strSql);
        Log.i("DATABASE","onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertNewPlayer(String name,String lastname,int age,String login,String password){
        name = name.replace("'","''");
        lastname = lastname.replace("'","''");
        login = login.replace("'","''");
        password = password.replace("'","''");
        String strSql = "insert into Player (name,lastname,login,password,age,score,niveau) values"+
                " ('"+name+"','"+lastname+"','"+login+"','"+password+"',"+age+","+0+","+1+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE","insertNewPlayer invoked");
    }

    public Player readPlayerByLoginAndPwd(String login,String pass){
        String strSql = "select * from Player where login='"+login+"' and password='"+pass+"'" ;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();
        if(cursor.isAfterLast()){return null;}
        return new Player(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),
                cursor.getInt(6),cursor.getInt(7));

    }
}
