package com.example.gestordeeventos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listaEventos;
    private Button btnAddEvento;

    private ArrayList<Evento> eventos;
    private ArrayList<String> eventosTexto;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventos = findViewById(R.id.listaEventos);
        btnAddEvento = findViewById(R.id.btnAddEvento);

        eventos = new ArrayList<>();
        eventosTexto = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                eventosTexto
        );

        listaEventos.setAdapter(adapter);

        // Botón añadir evento (ejemplo)
        btnAddEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento evento = new Evento(
                        "Evento de prueba",
                        "20/01/2026",
                        "18:00"
                );

                eventos.add(evento);
                eventosTexto.add(
                        evento.getNombre() + " - " +
                                evento.getFecha() + " " +
                                evento.getHora()
                );

                adapter.notifyDataSetChanged();
            }
        });
    }
}
