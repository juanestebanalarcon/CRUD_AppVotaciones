package com.uao.parcial1alarconjuan_sqliteappcrud.sqlConn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String DB_NAME, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, DB_NAME, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table votantes(cedula integer primary key, nombre text, colegio text, nromesa integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists votantes");
        db.execSQL("create table votantes(cedula integer primary key, nombre text, colegio text, nromesa integer)");
    }



}
