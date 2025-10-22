package com.example.togglebutton;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView TxtEstado;

    private ToggleButton togglebtn;

    private ImageButton imgBtn;

    private boolean isAntony2 = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TxtEstado = findViewById(R.id.TxtEstado);
        togglebtn = findViewById(R.id.miToggleButton);
        imgBtn = findViewById(R.id.Imgbtn);


        togglebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    TxtEstado.setText("Estado: Pulsado");
                } else{
                    TxtEstado.setText("Estado: No pulsado");
                }
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Has pulsado el ImageButton", Toast.LENGTH_SHORT).show();

                if (isAntony2) {
                    imgBtn.setImageResource(R.drawable.antony); // Cambia a antony
                } else {
                    imgBtn.setImageResource(R.drawable.antony2); // Cambia a antony2
                }

                isAntony2 = !isAntony2; // Alterna el estado
            }
        });


    }
}