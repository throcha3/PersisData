package net.flexhouse.persisdadosone.database.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.flexhouse.persisdadosone.database.helper.LivrosHelper;

/**
 * Created by thiag on 19/04/2016.
 */
public class LivrosController {
    private SQLiteDatabase db;
    private LivrosHelper banco;

    public LivrosController(Context context){
        banco = new LivrosHelper(context);
    }

    public String insereDado(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(LivrosHelper.TITULO, titulo);
        valores.put(LivrosHelper.AUTOR, autor);
        valores.put(LivrosHelper.EDITORA, editora);

        resultado = db.insert(LivrosHelper.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.AUTOR,banco.EDITORA};
        String where = LivrosHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(LivrosHelper.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = LivrosHelper.ID + "=" + id;

        valores = new ContentValues();
        valores.put(LivrosHelper.TITULO, titulo);
        valores.put(LivrosHelper.AUTOR, autor);
        valores.put(LivrosHelper.EDITORA, editora);

        db.update(LivrosHelper.TABELA,valores,where,null);
        db.close();
    }
    public void deletaRegistro(int id){
        String where = LivrosHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(LivrosHelper.TABELA,where,null);
        db.close();
    }

}
