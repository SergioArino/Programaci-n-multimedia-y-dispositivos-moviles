package com.example.practica5_materialdesign;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentoFormulario extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);

        TextInputLayout inputLayout = view.findViewById(R.id.inputLayout);
        TextInputEditText editText = view.findViewById(R.id.editText);
        MaterialButton btnEnviar = view.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {

            String texto = editText.getText().toString().trim();

            // Validación personalizada
            if (TextUtils.isEmpty(texto)) {
                inputLayout.setError("Este campo no puede quedar vacío");
            } else if (texto.length() < 3) {
                inputLayout.setError("Introduce al menos 3 letras");
            } else {
                inputLayout.setError(null);

                // Snackbar con mensaje distinto
                Snackbar.make(view,
                                "¡Datos registrados con éxito!",
                                Snackbar.LENGTH_LONG)
                        .setAction("OK", null)
                        .show();
            }
        });

        return view;
    }
}