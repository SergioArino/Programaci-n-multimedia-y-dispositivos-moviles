package com.example.practica5_materialdesign;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.practica5_materialdesign.R;

public class Fragmento2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragmento2, container, false);

        Button btn = view.findViewById(R.id.btnCalculadora);

        btn.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setClassName(
                    "com.example.calculadorapractica",
                    "com.example.calculadorapractica.MainActivity"
            );

            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getActivity(),
                        "No se pudo abrir Calculadora",
                        Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}