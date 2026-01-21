package com.example.gestordeeventos;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificacionWorker extends Worker {

    private static final String CHANNEL_ID = "eventos_channel";

    public NotificacionWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        // Obtener datos enviados desde MainActivity mediante WorkManager
        String nombre = getInputData().getString("nombre");
        String fecha = getInputData().getString("fecha");
        String hora = getInputData().getString("hora");

        // Crear el canal de notificaciones (solo necesario en Android 8.0+)
        createNotificationChannel();

        // Construcción de la notificación
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.atletico_madrid)
                        .setContentTitle("Recordatorio: " + nombre)
                        .setContentText(fecha + " " + hora)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);

        // Obtener el servicio del sistema encargado de mostrar notificaciones
        NotificationManager nm =
                (NotificationManager) getApplicationContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);

        // Mostrar la notificación
        nm.notify((int) System.currentTimeMillis(), builder.build());

        return Result.success();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Crear el canal con su ID, nombre visible y nivel de importancia
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Canal Eventos",
                    NotificationManager.IMPORTANCE_HIGH
            );

            // Registrar el canal en el sistema
            NotificationManager nm =
                    (NotificationManager) getApplicationContext()
                            .getSystemService(Context.NOTIFICATION_SERVICE);

            nm.createNotificationChannel(channel);
        }
    }
}