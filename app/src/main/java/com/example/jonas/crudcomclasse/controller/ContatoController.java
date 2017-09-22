package com.example.jonas.crudcomclasse.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jonas.crudcomclasse.adapter.DataBaseAdapter;
import com.example.jonas.crudcomclasse.model.Contato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas on 29/08/2017.
 */

public class ContatoController extends DataBaseAdapter {

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

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM contato";

        int contador = db.rawQuery(sql, null).getCount();

        return  contador;
    }

    public List<Contato> listarContatos(){

        List<Contato> contatos = new ArrayList<>();

        String sql = "SELECT * FROM contato ORDER by id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){

            do{

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String email = cursor.getString(cursor.getColumnIndex("email"));

                Contato contato = new Contato();

                contato.setId((id));
                contato.setNome(nome);
                contato.setEmail(email);

                contatos.add(contato);

            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return contatos;
    }

    public Contato buscarPeloID(int contatoID){

        Contato contato = new Contato();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato WHERE id = " + contatoID;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){

            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            contato = new Contato();

            contato.setId(contatoID);
            contato.setNome(nome);
            contato.setEmail(email);
        }

        return contato;
    }

    public boolean updade(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());

        String where = "id = ?";

        String[] whererArgs = { Integer.toString(contato.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isupdate = db.update("contato", values, where, whererArgs) > 0 ;

        db.close();

        return isupdate;
    }

    public boolean delete(int contatoID){

        boolean isDeletado = false;

        SQLiteDatabase db = this.getWritableDatabase();
        isDeletado = db.delete("contato", "id ='" + contatoID + "'",null) > 0;
        db.close();

        return isDeletado;
    }

}
