package com.example.edittext;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        String [] opciones = {"opcion1","opcion2","opcion3","opcion4","opcion5"};
        AutoCompleteTextView textoleido = findViewById(R.id.miTexto);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,opciones);
        textoleido.setAdapter(adaptador);
    }
}