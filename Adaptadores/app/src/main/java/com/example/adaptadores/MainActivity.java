package com.example.adaptadores;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal);

        View micabecera=getLayoutInflater().inflate(R.layout.cabecera, null);


        Datos[] datos=new Datos[]{
                new Datos("España", "Madrid"),
                new Datos("Atleti", "Julian"),
                new Datos("Madrid", "CACA"),
                new Datos("Felix", "Vagabundo")
        };
        ListView listado = findViewById(R.id.listview);

        listado.addHeaderView(micabecera);

        Adaptador adaptador = new Adaptador(this,datos);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectdItem = ((Datos)parent.getItemAtPosition(position)).getTexto1();

                Toast.makeText(MainActivity.this, "Elemento pulsado: " + selectdItem, Toast.LENGTH_SHORT).show();
            }

        });

        listado.setAdapter(adaptador);
    }


}