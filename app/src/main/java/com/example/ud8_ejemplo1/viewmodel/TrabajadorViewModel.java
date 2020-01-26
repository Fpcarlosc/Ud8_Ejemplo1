package com.example.ud8_ejemplo1.viewmodel;

import android.app.Application;

import com.example.ud8_ejemplo1.basedatos.Trabajador;
import com.example.ud8_ejemplo1.repositorio.RepositorioTrabajador;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TrabajadorViewModel extends AndroidViewModel {

    // Los atributos de la clase serán uno de tipo RespositorioTrabajador para trabajar con la base de
    // datos a través de él y una lista de tipo LiveData con listas de trabajadores.
    private RepositorioTrabajador repositorioTrabajador;
    private LiveData<List<Trabajador>> todosTrabajadores;

    public TrabajadorViewModel(Application aplicacion) {
        super(aplicacion);
        repositorioTrabajador = new RepositorioTrabajador(aplicacion);

        // Obtenemos todos los trabajadores del repositorio.
        todosTrabajadores = repositorioTrabajador.obtenerTodosTrabajadores();
    }

    // Método para obtener todos los trabajadores.
    public LiveData<List<Trabajador>> obtenerTodosTrabajadores() {
        return todosTrabajadores;
    }

    // Método para insertar un trabajador a través del repositorio.
    public void insertar(Trabajador trabajador) {
        repositorioTrabajador.insertar(trabajador);
    }

    // Método para actualizar el nombre de un trabajador a través del repositorio.
    public void actualizar(int id, String nombre) {
        repositorioTrabajador.actualizar(id, nombre);
    }
}
