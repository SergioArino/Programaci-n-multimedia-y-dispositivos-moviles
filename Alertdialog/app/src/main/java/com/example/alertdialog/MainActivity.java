package com.example.alertdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mostrar el AlertDialog al iniciar
        showAlertDialog();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Aceptar notificaciones");
        builder.setMessage("Deseas permitir notificaciones.");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al presionar "Aceptar"
                Toast.makeText(MainActivity.this, "Has aceptado", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al presionar "Cancelar"
                Toast.makeText(MainActivity.this, "Has cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("Ignorar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al presionar "Ignorar"
                Toast.makeText(MainActivity.this, "Has ignorado", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show(); // Mostrar el diálogo
    }
}