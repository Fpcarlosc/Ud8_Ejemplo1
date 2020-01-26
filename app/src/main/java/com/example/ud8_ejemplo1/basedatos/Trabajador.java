package com.example.ud8_ejemplo1.basedatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Nombre de la tabla: tabla_trabajador
// Campos: id (Clave primaria, autogenerado y no null),
//         nombre (no null).
@Entity(tableName = "tabla_trabajador")
public class Trabajador {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    public Trabajador(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }
}
