package com.example.ud8_ejemplo1.basedatos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

// Dao con 3 operaciones SQL: obtener todos los trabajadores ordenados alfabéticamente, insertar
// un trabajador y actualizar el nombre de uno ya insertado.
@Dao
public interface DaoTrabajador {
    @Query("SELECT * from tabla_trabajador ORDER BY nombre ASC")
    LiveData<List<Trabajador>> obtenerTrabajadoresOrdenados();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertar(Trabajador trabajador);

    @Query("UPDATE tabla_trabajador SET nombre =:nombre WHERE id =:id;")
    void actualizar(int id, String nombre);
}
