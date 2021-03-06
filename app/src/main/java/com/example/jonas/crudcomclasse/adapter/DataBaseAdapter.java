package com.example.jonas.crudcomclasse.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jonas on 29/08/2017.
 */

public class DataBaseAdapter extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "CrudCompleto.DB";

    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE contato " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "email TEXT ) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //OBSERVAÇÂO: é de responsabilidade do desenvolvedor criar os procedimentos de backups de seus dados
        //antes de efetuar um upgrade de versão.
    }
}
