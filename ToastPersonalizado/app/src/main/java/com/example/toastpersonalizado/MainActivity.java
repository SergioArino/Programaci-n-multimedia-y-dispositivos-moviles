package com.example.toastpersonalizado;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.showToastButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar el diseño personalizado
                LayoutInflater inflater = getLayoutInflater();
                View customView = inflater.inflate(R.layout.layout, null);

                // Crear y mostrar el toast
                Toast toast = new Toast(getApplicationContext());
                toast.setView(customView);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                toast.show();
            }
        });
    }
}