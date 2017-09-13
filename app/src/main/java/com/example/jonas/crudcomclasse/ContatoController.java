package com.example.jonas.crudcomclasse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by jonas on 29/08/2017.
 */

public class ContatoController extends DataBaseAdapter{

    public ContatoController(Context context){
        // Integração com Banco de Dados
        super(context);
    }

    public boolean create(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("contato", null, values) > 0;
        db.close();

        return isCreate;
    }

    public int totalDeContatos(){

        return  0;
    }

    public List<Contato> listarContatos(){

        return null;
    }

    public Contato buscarPeloID(int contatoID){

        return null;
    }

    public boolean updade(Contato contato){

        return true;
    }

    public boolean delete(int contatoID){
        return true;
    }

}
