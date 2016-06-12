package net.flexhouse.persisdadosone.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.flexhouse.persisdadosone.R;
import net.flexhouse.persisdadosone.database.controller.LivrosController;
import net.flexhouse.persisdadosone.database.helper.LivrosHelper;
import net.flexhouse.persisdadosone.ui.adapter.LivroCursorAdapter;

public class OutraConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        LivrosController crud = new LivrosController(getBaseContext());
        final Cursor cursor = crud.getData();

        ListView lvItems = (ListView) findViewById(R.id.listView);
        LivroCursorAdapter livroCursorAdapter = new LivroCursorAdapter(this, cursor);

        assert lvItems != null;
        lvItems.setAdapter(livroCursorAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(LivrosHelper.ID));
                Intent intent = new Intent(OutraConsultaActivity.this, AlteraActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}
