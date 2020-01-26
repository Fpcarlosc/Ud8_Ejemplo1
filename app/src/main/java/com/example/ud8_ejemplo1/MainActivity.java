package com.example.ud8_ejemplo1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ud8_ejemplo1.basedatos.Trabajador;
import com.example.ud8_ejemplo1.viewmodel.TrabajadorViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TrabajadorAdapter trabajadorAdapter;
    private TrabajadorViewModel trabajadorViewModel;
    int posisicionPulsada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, 1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        trabajadorAdapter = new TrabajadorAdapter(this);

        trabajadorAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos la posición pulsada del recyclerView para actualizarla o eliminarla.
                posisicionPulsada = recyclerView.getChildAdapterPosition(v);

                Trabajador t = trabajadorAdapter.lista.get(posisicionPulsada);

                Intent intent = new Intent(MainActivity.this, ActualizarTrabajador.class);

                intent.putExtra("id", t.getId());
                intent.putExtra("nombre", t.getNombre());

                startActivityForResult(intent, 0);
            }
        });

        recyclerView.setAdapter(trabajadorAdapter);

        // Obtenemos uno nuevo o ya existente ViewModel desde la clase ViewModelProviders.
        trabajadorViewModel = ViewModelProviders.of(this).get(TrabajadorViewModel.class);

        // Añadimos un observador al LiveData devuelto por el método obtenerTodosTrabajadores.
        // el método onchanged se ejecuta cuando el observador detecta un cambio y la actividad está
        // en segundo plano.
        trabajadorViewModel.obtenerTodosTrabajadores().observe(this, new Observer<List<Trabajador>>() {
            @Override
            public void onChanged(@Nullable final List<Trabajador> trabajadores) {
                // Actualizamos el cambio en el RecyclerView.
                trabajadorAdapter.anyadirALista(trabajadores);
            }
        });

        // Buscamos el FAB y configuramos su onClickListener
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertarTrabajador.class);

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Actualizamos el nombre, insertamos un trabjador o la respuesta es errónea.
        if (requestCode == 0 && resultCode == RESULT_OK) {
            trabajadorViewModel.actualizar(data.getIntExtra("id", 0), data.getStringExtra("nombre"));
        }
        else
            if(requestCode == 1 && resultCode == RESULT_OK) {
                Trabajador trabajador = new Trabajador(data.getStringExtra("trabajador"));
                trabajadorViewModel.insertar(trabajador);
            }
            else{
                Toast.makeText(getApplicationContext(), "Error en la respuesta.", Toast.LENGTH_LONG).show();
            }

    }
}
