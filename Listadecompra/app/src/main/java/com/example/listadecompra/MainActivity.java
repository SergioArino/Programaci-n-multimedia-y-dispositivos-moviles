package com.example.listadecompra;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> shoppingList;
    private ShoppingListAdapter adapter;
    private ListView listView;
    private EditText editName, editQuantity;
    private Spinner spinnerImages;
    private int[] imageRes = {R.drawable.rojo, R.drawable.verde};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingList = new ArrayList<>();
        adapter = new ShoppingListAdapter(this, shoppingList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        editName = findViewById(R.id.editName);
        editQuantity = findViewById(R.id.editQuantity);
        spinnerImages = findViewById(R.id.spinnerImages);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Rojo", "Verde"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImages.setAdapter(spinnerAdapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editName.getText().toString();
                int cantidad = Integer.parseInt(editQuantity.getText().toString());
                int imagen = imageRes[spinnerImages.getSelectedItemPosition()];

                shoppingList.add(new Item(nombre, cantidad, imagen));
                adapter.notifyDataSetChanged();
            }
        });
    }

    // Menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Añadir");
        menu.add(0, v.getId(), 0, "Eliminar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle().equals("Eliminar")) {
            shoppingList.remove(info.position);
            adapter.notifyDataSetChanged();
        } else if (item.getTitle().equals("Añadir")) {
            shoppingList.add(new Item("Nuevo", 1, R.drawable.rojo));
            adapter.notifyDataSetChanged();
        }
        return true;
    }
}