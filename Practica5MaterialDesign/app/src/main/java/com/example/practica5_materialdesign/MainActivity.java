package com.example.practica5_materialdesign;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargamos el layout principal
        setContentView(R.layout.activity_main);

        // Referencias a los elementos de la vista
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        FloatingActionButton fab = findViewById(R.id.fab);

        // Creamos el adaptador para gestionar los fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Añadimos los fragments al adaptador
        adapter.addFragment(new FragmentoFormulario(), "Formulario");
        adapter.addFragment(new Fragmento2(), "Pestaña 2");
        adapter.addFragment(new Fragmento3(), "Pestaña 3");

        // Asignamos el adaptador al ViewPager
        viewPager.setAdapter(adapter);

        // Conectamos el TabLayout con el ViewPager
        tabLayout.setupWithViewPager(viewPager);

        // Asignamos iconos a cada pestaña (EXTRA)
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_person);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_ajustes);

        // Acción del Floating Action Button
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Acción realizada correctamente", Snackbar.LENGTH_LONG)
                        .setAction("Aceptar", v -> {
                            // Acción del botón DESHACER (opcional)
                        })
                        .show()
        );
    }
}


