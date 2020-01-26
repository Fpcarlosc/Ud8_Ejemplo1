package com.example.ud8_ejemplo1.repositorio;

import android.app.Application;

import com.example.ud8_ejemplo1.basedatos.BaseDatosRoom;
import com.example.ud8_ejemplo1.basedatos.DaoTrabajador;
import com.example.ud8_ejemplo1.basedatos.Trabajador;

import java.util.List;

import androidx.lifecycle.LiveData;

// Repositorio para trabajar con el Dao
public class RepositorioTrabajador {

    // Los atributos de la clase serán uno de tipo DaoTrabajador para trabajar con la base de
    // datos a través de él y una lista de tipo LiveData con listas de trabajadores.
    private DaoTrabajador daoTrabajador;
    private LiveData<List<Trabajador>> todosTrabajadores;

    public RepositorioTrabajador(Application aplicacion) {
        BaseDatosRoom db = BaseDatosRoom.obtenerBaseDatos(aplicacion);
        daoTrabajador = db.daoTrabajador();
        todosTrabajadores = daoTrabajador.obtenerTrabajadoresOrdenados();
    }


    // Método para obtener todos los trabjadores.
    // Room ejecuta todas las consultas en un hilo separado.
    // LiveData nos notificará si un dato ha cambiado.
    public LiveData<List<Trabajador>> obtenerTodosTrabajadores() {
        return todosTrabajadores;
    }


    // Método para insertar un trabajador.
    // Ejecutamos la inserción del trabjador en la base de datos, en base a la inserción del mismo
    // en el Dao. Como la llamada se realiza en un hilo que no es de la IU, Room nos asegura
    // que no se está realizando ninguna operación en el hilo principal, de tal forma que bloquea
    // la IU.
    public void insertar(Trabajador trabajador) {
        BaseDatosRoom.databaseWriteExecutor.execute(() -> {
            daoTrabajador.insertar(trabajador);
        });
    }

    // Método para actualizar el nombre de un trabajador.
    public void actualizar(int id, String nombre) {
        BaseDatosRoom.databaseWriteExecutor.execute(() -> {
            daoTrabajador.actualizar(id, nombre);
        });
    }
}
