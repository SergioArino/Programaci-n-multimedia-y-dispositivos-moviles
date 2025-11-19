package com.example.tema5basicadapter;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private List<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        items = new ArrayList<>();
        items.add(new ListItem(R.drawable.espana, "España", "ESPAÑA"));
        items.add(new ListItem(R.drawable.italia, "Italia", "ITALIA"));
        items.add(new ListItem(R.drawable.ic_launcher_foreground, "Android", "ANDROID"));
        items.add(new ListItem(R.drawable.francia, "Francia", "FRANCIA"));

        adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);

    }
}