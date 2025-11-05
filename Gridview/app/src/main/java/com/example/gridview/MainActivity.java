package com.example.gridview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);

        GridView listado = findViewById(R.id.gridview);
        TextView textView = findViewById(R.id.textview);

        List<String> data = Arrays.asList("Elemento 1", "Elemento 2", "Elemeto 3", "Elemnto 4");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listado.setAdapter(adapter);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elemento = (String) parent.getItemAtPosition(position);
                textView.setText(elemento);
            }
        });
    }

    public class Datos {
        private String texto1;
        private String texto2;

        public Datos(String text1, String text2) {
            texto1 = text1;
            texto2 = text2;
        }

        public String getTexto1() {
            return texto1;
        }

        public String getTexto2() {
            return texto2;
        }
    }
    public class Adaptador extends ArrayAdapter<Datos>{
        private Datos[] datos;
        public Adaptador(Context context, Datos[] datos){
            super(context, R.layout.elementos, datos);
            this.datos= datos;
        }
    }
}