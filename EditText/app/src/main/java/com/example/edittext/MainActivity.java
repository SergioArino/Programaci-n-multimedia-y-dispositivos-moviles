package com.example.edittext;

import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);

        Spinner miSpinner = (Spinner) findViewById(R.id.miSpinner);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        String[] valores = {"Star wars", "otro", "tercero"};
        miSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        String[] opciones = {"opcion1", "opcion2", "opcion3", "opcion4", "opcion5"};
        AutoCompleteTextView textoleido = findViewById(R.id.miTexto);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, opciones);
        textoleido.setAdapter(adaptador);
        RadioGroup rg = findViewById(R.id.radiogroup);
        Button bt = findViewById(R.id.button);
        TextView t = findViewById(R.id.textview);
        TextView text = findViewById(R.id.textview2);
        TextView text2 = findViewById(R.id.textview3);
        RatingBar ratingBar = findViewById(R.id.Ratingbar);
        ProgressBar progressBar2 = findViewById(R.id.Progressbar2);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            int v = 0;

            @Override
            public void run() {
                if (v <= 100) {
                    progressBar2.setProgress(v += 5);
                    new Handler(Looper.getMainLooper()).postDelayed(this, 200);
                }
            }
        }, 200);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selec = rg.getCheckedRadioButtonId();

                if (selec!=-1){
                    RadioButton abc = findViewById(selec);
                    String opcion = abc.getText().toString();
                    t.setText(opcion);
                }else {
                    t.setText("No has seleccionado nada");
                }
            }
        });
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Has seleccionado el valor: " + parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("No has seleccionado nada");
            }
        });
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkbox1.setText("checked");
                } else{
                    checkbox1.setText("no checked");
                }
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textoleido.setText("Estado: encendido");
                }else {
                    textoleido.setText("Estado: apagado");
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text.setText("volumen: "+ progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                text2.setText("Calificacion: " + rating);
            }
        });

    }
}