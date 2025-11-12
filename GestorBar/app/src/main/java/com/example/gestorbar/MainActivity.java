package com.example.gestorbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoCompleteWaiter = findViewById(R.id.autoCompleteWaiter);
        EditText editTextTotal = findViewById(R.id.editTextTotal);
        CheckBox checkBoxTip = findViewById(R.id.checkBoxTip);
        SeekBar seekBarTip = findViewById(R.id.seekBarTip);
        TextView textViewTipPercent = findViewById(R.id.textViewTipPercent);
        RadioGroup radioGroupPayment = findViewById(R.id.radioGroupPayment);
        RatingBar ratingBarService = findViewById(R.id.ratingBarService);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);

        TextView textViewTotal = findViewById(R.id.textViewTotal);
        TextView textViewPago = findViewById(R.id.textViewPago);
        TextView textViewValoracion = findViewById(R.id.textViewValoracion);
        TextView textViewCamarero = findViewById(R.id.textViewCamarero);

        // Autocompletado de camareros
        String[] camareros = {"Carlos", "Lucía", "Marta", "Jorge", "Ana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, camareros);
        autoCompleteWaiter.setAdapter(adapter);

        // Actualizar texto de porcentaje
        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewTipPercent.setText("Propina: " + progress + "%");
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editTextTotal.getText().toString();
                textViewTotal.setTextColor(Color.RED);
                textViewPago.setText("");
                textViewValoracion.setText("");
                textViewCamarero.setText("");

                if (input.isEmpty()) {
                    textViewTotal.setText("Debes ingresar el total de la cuenta.");
                    return;
                }

                double total;
                try {
                    total = Double.parseDouble(input);
                    if (total <= 0) {
                        textViewTotal.setText("El total debe ser mayor que cero.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    textViewTotal.setText("Formato inválido. Ingresa solo números.");
                    return;
                }

                int tipPercent = seekBarTip.getProgress();
                boolean incluirPropina = false;
                if (checkBoxTip.isChecked()) {
                    incluirPropina = true;
                }

                double propina = 0;
                if (incluirPropina) {
                    propina = total * tipPercent / 100.0;
                }

                double totalFinal = total + propina;
                textViewTotal.setTextColor(Color.BLACK);
                textViewTotal.setText("Total: €" + totalFinal);

                int selectedId = radioGroupPayment.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedRadio = findViewById(selectedId);
                    String metodoPago = selectedRadio.getText().toString();
                    textViewPago.setText("Método de pago: " + metodoPago);
                } else {
                    textViewPago.setText("Método de pago: No seleccionado");
                }

                float estrellas = ratingBarService.getRating();
                textViewValoracion.setText("Calificación del servicio: " + estrellas + " estrellas");

                String camarero = autoCompleteWaiter.getText().toString();
                if (!camarero.isEmpty()) {
                    textViewCamarero.setText("Atendido por: " + camarero);
                } else {
                    textViewCamarero.setText("Camarero no especificado");
                }
            }
        });
    }
}