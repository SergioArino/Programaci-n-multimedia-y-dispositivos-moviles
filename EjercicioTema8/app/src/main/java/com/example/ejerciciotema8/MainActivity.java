package com.example.ejerciciotema8;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button btnRunOnUi, btnHandler;
    Handler handler = new Handler();
    int progreso = 0;
    final int MAX = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        btnRunOnUi = findViewById(R.id.btnRunOnUi);
        btnHandler = findViewById(R.id.btnHandler);

        progressBar.setMax(MAX);

        btnRunOnUi.setOnClickListener(v -> {
            progreso = 0;
            new Thread(() -> {
                while (progreso < MAX) {
                    progreso += 50;

                    runOnUiThread(() -> progressBar.setProgress(progreso));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        btnHandler.setOnClickListener(v -> {
            progreso = 0;
            new Thread(() -> {
                while (progreso < MAX) {
                    progreso += 50;

                    handler.post(() -> progressBar.setProgress(progreso));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }
}