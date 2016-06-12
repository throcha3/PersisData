package net.flexhouse.persisdadosone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.flexhouse.persisdadosone.R;
import net.flexhouse.persisdadosone.database.controller.LivrosController;

public class InserirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        Button botao = (Button)findViewById(R.id.btn_cadastrar);
        Button consultar = (Button)findViewById(R.id.btn_consultar);
        Button consultaroutro = (Button)findViewById(R.id.btn_consultaroutro);

        assert consultar != null;
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InserirActivity.this, ConsultaActivity.class);
                startActivity(i);
            }
        });
        assert consultaroutro != null;
        consultaroutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(InserirActivity.this, OutraConsultaActivity.class);
                startActivity(x);
            }
        });

        assert botao != null;
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivrosController crud = new LivrosController(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.editText);
                EditText autor = (EditText)findViewById((R.id.editText2));
                EditText editora = (EditText)findViewById(R.id.editText3);
                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = editora.getText().toString();
                String resultado;

                resultado = crud.insereDado(tituloString,autorString,editoraString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }

}
