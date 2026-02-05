package com.example.tema8;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

        // BOTÓN 1 → runOnUiThread()
        btnRunOnUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progreso = 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progreso < MAX) {
                            progreso += 50;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progreso);
                                }
                            });

                            try {
                                Thread.sleep(1000); // 1 segundo
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}