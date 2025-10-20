package com.example.calculadorapractica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private double primerNumero = 0;
    private double segundoNumero = 0;
    private String operador = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid);
    //Creacion de identificadores para cada boton, tambien para el texto donde se va a ver todo
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

        //Enlace de los botones para el OnClick

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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String actual = text.getText().toString();

        if (actual.equals("0")) actual = "";
//Añadimos a los todos los botones del 0 al 9 que si le damos click, se ponga ese número
        if (id == R.id.boton0) {
            text.setText(actual + "0");
        } else if (id == R.id.boton1) {
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
        } else if (id == R.id.botoncoma) {
        // Si el texto está vacío o solo tiene "0", empezamos con "0."
        if (actual.isEmpty() || actual.equals("0")) {
            text.setText("0.");
        } else if (!actual.contains(".")) {
            // Si ya hay contenido pero no contiene punto, lo añadimos
            text.setText(actual + ".");
        }
        //Si le damos al boton sumar, el primer numero lo convierte de string a numero decimal, y lo pone junto a +
    }else if (id == R.id.botonsuma) {
            try {
                primerNumero = Double.parseDouble(actual);
                operador = " + ";
                text.setText(actual + operador);
            } catch (Exception e) {
                text.setText("Error");
            }
            //Si le damos al boton retar, el primer numero lo convierte de string a numero decimal, y lo pone junto a -
        } else if (id == R.id.botonrestar) {
            try {
                primerNumero = Double.parseDouble(actual);
                operador = " - ";
                text.setText(actual + operador);
            } catch (Exception e) {
                text.setText("Error");
            }
            //Si le damos al boton multiplicar, el primer numero lo convierte de string a numero decimal, y lo pone junto a *
        }else if (id == R.id.botonmultiplicar) {
            try {
                primerNumero = Double.parseDouble(actual);
                operador = " * ";
                text.setText(actual + operador);
            } catch (Exception e) {
                text.setText("Error");
            }
            //Si le damos al boton dvidir, el primer numero lo convierte de string a numero decimal, y lo pone junto a /
        }else if (id == R.id.botondividir) {
            try {
                primerNumero = Double.parseDouble(actual);
                operador = " / ";
                text.setText(actual + operador);
            } catch (Exception e) {
                text.setText("Error");
            }
            //Si le damos al boton igual, calcula el resultado, cogiendo el primer numero en decimal, cogiendo el operador y convierte el segundo de string a numero en decimal. Para que luego muestre el rresultado
        }else if (id == R.id.botonigual) {
            try {
                // Obtenemos el texto actual completo
                String texto = text.getText().toString();

                // Buscamos la posición del operador (sin espacios). Si no encuentra operador devuelve -1, por el indexOf
                int pos = texto.indexOf(operador.trim());

                // Si no se encuentra el operador, mostramos error
                if (pos == -1) {
                    text.setText("Error");
                    return;
                }

                // Extraemos el segundo número desde después del operador con el substring, que sirve para coger lo que haya despues de la posicion + 1.
                String segundoTexto = texto.substring(pos + 1).trim();

                // Convertimos el segundo número gracias a parseDouble
                segundoNumero = Double.parseDouble(segundoTexto);

                // Calculamos el resultado
                double resultado = 0;
                //Al darle al operador que queremos, y al poner los dos numeros, hace el calculo y lo mete en resultado, para luego mostrarlo
                if (operador.equals(" + ")) {
                    resultado = primerNumero + segundoNumero;
                } else if (operador.equals(" - ")) {
                    resultado = primerNumero - segundoNumero;
                } else if (operador.equals(" * ")) {
                    resultado = primerNumero * segundoNumero;
                } else if (operador.equals(" / ")) {
                    resultado = primerNumero / segundoNumero;
                    }

                // Mostramos el resultado
                text.setText(String.valueOf(resultado));
            } catch (Exception e) {
                text.setText("Error");
            }
            //Al darle al boton AC, borra todo
        }else if (id == R.id.botonAC) {
            primerNumero = 0;
            segundoNumero = 0;
            operador = "";
            text.setText("0");
            //Al darle al boton C borra lo ultimo escrito
        } else if (id == R.id.botonC) {
            if (!actual.isEmpty()) {
                actual = actual.substring(0, actual.length() - 1);
                if (actual.isEmpty()) actual = "0";
                text.setText(actual);
            }
        }
    }
}