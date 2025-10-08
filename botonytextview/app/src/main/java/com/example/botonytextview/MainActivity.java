package com.example.botonytextview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);
        Button boton1  = findViewById(R.id.boton1);
        Button boton2  = findViewById(R.id.boton2);
        Button boton3  = findViewById(R.id.boton3);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();
        TextView miText = (TextView) findViewById(R.id.texto);
        miText.setText("Nuevo texto a mostrar");
        miText.setTextColor(Color.parseColor("#0000FF"));
        miText.setTextColor(Color.RED);
        miText.setTypeface(null, Typeface.ITALIC);
        miText.setTextSize(24);
        miText.setTypeface(Typeface.SANS_SERIF);
    }
    public void onClick(View view){
        TextView text = findViewById(R.id.texto);
        int id = view.getId();

        if (id == R.id.boton1){
            Animation miAnimation = AnimationUtils.loadAnimation(this,R.anim.animacion);
            miAnimation.setRepeatMode(Animation.RESTART);
            miAnimation.setRepeatCount(20);
            text.startAnimation(miAnimation);
        }
        if (id == R.id.boton2){
            Animation miAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
            text.startAnimation(miAnimation);
        }
        if (id == R.id.boton3){
            text.clearAnimation();
        }
    }
}