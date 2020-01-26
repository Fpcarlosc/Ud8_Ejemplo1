package com.example.ud8_ejemplo1.basedatos;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Definimos la base de datos con todas las entidades creadas (en nuestro caso una9, la versión
// y no exportamos el "schema".
@Database(entities = {Trabajador.class}, version = 1, exportSchema = false)
public abstract class BaseDatosRoom extends RoomDatabase {

    public abstract DaoTrabajador daoTrabajador();

    // Instancia de la Base de Datos.
    // La declaramos como volatile para asegurarnos que su lectura y escritura sea desde
    // la memoria principal y no desde la caché.
    private static volatile BaseDatosRoom INSTANCIA;

    // Definimos 4 hilos.
    private static final int NUM_HILOS = 4;

    //Ccreamos un atributo de tipo "ExecutorService" con el número de hilos definidos antes (4)
    // para realizar operaciones asíncronas en la base de datos sobre el hilo "background".
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_HILOS);

    // Método para crear/acceder a la base de datos (basedatos_trabajador).
    public static BaseDatosRoom obtenerBaseDatos(final Context context) {
        if (INSTANCIA == null) {
            synchronized (BaseDatosRoom.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            BaseDatosRoom.class, "basedatos_trabajador")
                            .build();
                }
            }
        }
        return INSTANCIA;
    }
}
