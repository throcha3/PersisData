package net.flexhouse.persisdadosone.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.flexhouse.persisdadosone.R;
import net.flexhouse.persisdadosone.database.controller.LivrosController;
import net.flexhouse.persisdadosone.database.helper.LivrosHelper;

public class AlteraActivity extends AppCompatActivity {

    EditText mEdtLivro;
    EditText mEdtAutor;
    EditText mEdtEditora;
    Button mBtnAlterar;
    Button mBtnDeletar;
    Cursor cursor;
    LivrosController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new LivrosController(getBaseContext());

        mEdtLivro = (EditText)findViewById(R.id.editText4);
        mEdtAutor = (EditText)findViewById(R.id.editText5);
        mEdtEditora = (EditText)findViewById(R.id.editText6);

        mBtnAlterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        mEdtLivro.setText(cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.TITULO)));
        mEdtAutor.setText(cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.AUTOR)));
        mEdtEditora.setText(cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.EDITORA)));

        mBtnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro( Integer.parseInt(codigo), mEdtLivro.getText().toString(),mEdtAutor.getText().toString(),
                        mEdtEditora.getText().toString() );
                Toast.makeText(getBaseContext(), "Registro alterado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AlteraActivity.this,InserirActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mBtnDeletar = (Button)findViewById(R.id.button3);
        mBtnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Toast.makeText(getBaseContext(), "Registro deletado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AlteraActivity.this,InserirActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
