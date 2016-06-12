package net.flexhouse.persisdadosone.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.flexhouse.persisdadosone.R;
import net.flexhouse.persisdadosone.database.helper.LivrosHelper;

/**
 * Created by thiag on 11/06/2016.
 */
public class LivroCursorAdapter extends CursorAdapter {
    public LivroCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_custom_livro, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtId = (TextView) view.findViewById(R.id.txt_id);
        TextView txtTitulo = (TextView) view.findViewById(R.id.txt_titulo);
        TextView txtAutor = (TextView) view.findViewById(R.id.txt_autor);
        TextView txtEditora = (TextView) view.findViewById(R.id.txt_editora);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(LivrosHelper.ID));
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.TITULO));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.AUTOR));
        String editora = cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.EDITORA));

        txtId.setText(String.valueOf(id));
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtEditora.setText(editora);
    }
}