package com.example.gestordeeventos;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ListView listaEventos;
    private Button btnAddEvento;

    private ArrayList<Evento> eventos;
    private ArrayList<String> eventosTexto;
    private ArrayAdapter<String> adapter;

    private String nombreEvento, fechaEvento, horaEvento;

    private static final String CHANNEL_ID = "eventos_channel";
    private static final String CHANNEL_NAME = "Canal Eventos";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQ_POST_NOTIFICATIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEventos = findViewById(R.id.listaEventos);
        btnAddEvento = findViewById(R.id.btnAddEvento);

        eventos = new ArrayList<>();
        eventosTexto = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                eventosTexto);

        listaEventos.setAdapter(adapter);

        createNotificationChannel();

        btnAddEvento.setOnClickListener(v -> mostrarDialogNombre());

        listaEventos.setOnItemClickListener((parent, view, position, id) ->
                mostrarToastPersonalizado(eventos.get(position)));
    }

    // PERMISOS DE NOTIFICACIÓN

    private boolean canPostNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestPostNotificationsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    REQ_POST_NOTIFICATIONS
            );
        }
    }

    // DIÁLOGOS

    private void mostrarDialogNombre() {
        EditText input = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Nuevo evento")
                .setMessage("Introduce el nombre del evento")
                .setView(input)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    nombreEvento = input.getText().toString();
                    mostrarDatePicker();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void mostrarDatePicker() {
        Calendar c = Calendar.getInstance();

        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            fechaEvento = dayOfMonth + "/" + (month + 1) + "/" + year;
            mostrarTimePicker();
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void mostrarTimePicker() {
        Calendar c = Calendar.getInstance();

        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            horaEvento = String.format("%02d:%02d", hourOfDay, minute);
            crearEvento();
        }, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();
    }

    // CREAR EVENTO

    private void crearEvento() {
        Evento evento = new Evento(nombreEvento, fechaEvento, horaEvento);
        eventos.add(evento);
        eventosTexto.add(nombreEvento + " - " + fechaEvento + " " + horaEvento);
        adapter.notifyDataSetChanged();

        // Notificación inmediata
        if (canPostNotifications()) {
            mostrarNotificacion(evento);
        } else {
            requestPostNotificationsPermission();
        }

        // Notificación con retraso de 5 segundos
        notificacionConRetraso(evento, 5);
    }

    // NOTIFICACIONES

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }

    private void mostrarNotificacion(Evento evento) {

        Intent intent = new Intent(this, MainActivity.class);

        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, flags);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.atletico_madrid)
                        .setContentTitle(evento.getNombre())
                        .setContentText(evento.getFecha() + " " + evento.getHora())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(NOTIFICATION_ID, builder.build());
    }

    // TOAST PERSONALIZADO

    private void mostrarToastPersonalizado(Evento evento) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_evento, null);

        TextView txt = layout.findViewById(R.id.txtToast);
        txt.setText(evento.getNombre() + "\n" +
                evento.getFecha() + " " + evento.getHora());

        Toast toast = new Toast(this);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    private void notificacionConRetraso(Evento evento, int segundos) {

        Data data = new Data.Builder()
                .putString("nombre", evento.getNombre())
                .putString("fecha", evento.getFecha())
                .putString("hora", evento.getHora())
                .build();

        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificacionWorker.class)
                        .setInitialDelay(segundos, TimeUnit.SECONDS)
                        .setInputData(data)
                        .build();

        WorkManager.getInstance(this).enqueue(request);
    }

}