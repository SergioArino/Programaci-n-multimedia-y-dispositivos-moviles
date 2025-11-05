package com.example.adaptadores;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal);
        ListView listado = findViewById(R.id.listview);

        Datos[] datos=new Datos[]{
                new Datos("España", "Madrid"),
                new Datos("Atleti", "Julian"),
                new Datos("Madrid", "CACA"),
                new Datos("Felix", "Vagabundo")
        };

        Adaptador miAdaptador = new Adaptador(this, datos);
        listado.setAdapter(miAdaptador);
    }


}