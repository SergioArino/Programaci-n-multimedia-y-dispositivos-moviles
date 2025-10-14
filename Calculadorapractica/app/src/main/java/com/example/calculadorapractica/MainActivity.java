package com.example.calculadorapractica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid);

        text = findViewById(R.id.texto);

        Button boton0 = findViewById(R.id.boton0);
        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);
        Button boton4 = findViewById(R.id.boton4);
        Button boton5 = findViewById(R.id.boton5);
        Button boton6 = findViewById(R.id.boton6);
        Button boton7 = findViewById(R.id.boton7);
        Button boton8 = findViewById(R.id.boton8);
        Button boton9 = findViewById(R.id.boton9);
        Button botoncoma = findViewById(R.id.botoncoma);
        Button botonsuma = findViewById(R.id.botonsuma);
        Button botonrestar = findViewById(R.id.botonrestar);
        Button botondividir = findViewById(R.id.botondividir);
        Button botonmmultiplicar = findViewById(R.id.botonmultiplicar);
        Button botonigual = findViewById(R.id.botonigual);
        Button botonAC = findViewById(R.id.botonAC);
        Button botonC = findViewById(R.id.botonC);

        boton0.setOnClickListener(this);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        boton5.setOnClickListener(this);
        boton6.setOnClickListener(this);
        boton7.setOnClickListener(this);
        boton8.setOnClickListener(this);
        boton9.setOnClickListener(this);
        botoncoma.setOnClickListener(this);
        botonsuma.setOnClickListener(this);
        botonrestar.setOnClickListener(this);
        botondividir.setOnClickListener(this);
        botonmmultiplicar.setOnClickListener(this);
        botonigual.setOnClickListener(this);
        botonAC.setOnClickListener(this);
        botonC.setOnClickListener(this);



    }

    private boolean endsWithOperator(String s) {
        if (s.isEmpty()) return false;
        char last = s.charAt(s.length() - 1);
        return last == '+' || last == '-' || last == '*' || last == '/' || last == '.';
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        String actual = text.getText().toString();

        if (actual.equals("0")) actual = ""; // Evita que se quede el "0" inicial



        if (id == R.id.boton0) {
            text.setText(actual + "0");
        }else if (id == R.id.boton1) {
            text.setText(actual + "1");
        } else if (id == R.id.boton2) {
            text.setText(actual + "2");
        } else if (id == R.id.boton3) {
            text.setText(actual + "3");
        } else if (id == R.id.boton4) {
            text.setText(actual + "4");
        } else if (id == R.id.boton5) {
            text.setText(actual + "5");
        } else if (id == R.id.boton6) {
            text.setText(actual + "6");
        } else if (id == R.id.boton7) {
            text.setText(actual + "7");
        } else if (id == R.id.boton8) {
            text.setText(actual + "8");
        } else if (id == R.id.boton9) {
            text.setText(actual + "9");
        } else if (id == R.id.botonsuma) {
        if (!actual.isEmpty() && !endsWithOperator(actual)) {
            text.setText(actual + "+");
        }
        } else if (id == R.id.botonrestar) {
        if (!actual.isEmpty() && !endsWithOperator(actual)) {
            text.setText(actual + "-");
        }
        } else if (id == R.id.botondividir) {
        if (!actual.isEmpty() && !endsWithOperator(actual)) {
            text.setText(actual + "/");
        }
        } else if (id == R.id.botonmultiplicar) {
        if (!actual.isEmpty() && !endsWithOperator(actual)) {
            text.setText(actual + "*");
        }
        } else if (id == R.id.botoncoma) {
            if (!endsWithOperator(actual) || actual.equals("0")) {
                text.setText(actual + ".");
            }
        }else if (id == R.id.botonigual) {
        if (!actual.isEmpty() && !endsWithOperator(actual)) {
            text.setText(actual + "=");
            }
        }
         else if (id == R.id.botonAC) {
            text.setText("0");
        }else if (id == R.id.botonC) {
        if (!actual.isEmpty()) {
            actual = actual.substring(0, actual.length() - 1);
            if (actual.isEmpty()) {
                actual = "0";
            }
            text.setText(actual);
        }
    }
    }
}