package com.example.practica5_materialdesign;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practica5_materialdesign.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentoFormulario extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflamos el layout del fragment
        View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);

        // Referencias a los elementos del formulario
        TextInputLayout inputLayout = view.findViewById(R.id.inputLayout);
        TextInputEditText editText = view.findViewById(R.id.editText);
        MaterialButton btnEnviar = view.findViewById(R.id.btnEnviar);

        // Acción del botón Enviar
        btnEnviar.setOnClickListener(v -> {

            // Obtenemos el texto introducido
            String texto = editText.getText().toString();

            // Validación del campo
            if (TextUtils.isEmpty(texto) || texto.length() < 3) {
                // Mostramos error si no cumple la condición
                inputLayout.setError("Debe tener al menos 3 caracteres");
            } else {
                // Quitamos el error si es válido
                inputLayout.setError(null);

                // Mostramos un Snackbar de confirmación
                Snackbar.make(view, "Formulario enviado correctamente",
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
