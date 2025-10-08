package com.example.textview;

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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    protected void onStart(){
        super.onStart();
        TextView miTexto = (TextView) findViewById(R.id.texto);
        miTexto.setText("Nuevo texto");
        miTexto.setTextColor(Color.parseColor("#0000FF"));
        miTexto.setTextColor(Color.RED);
        miTexto.setTypeface(null, Typeface.ITALIC);
        miTexto.setTextSize(24);
        miTexto.setTypeface(Typeface.SANS_SERIF);

        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(20);
        miTexto.startAnimation(animacion);
    }
}