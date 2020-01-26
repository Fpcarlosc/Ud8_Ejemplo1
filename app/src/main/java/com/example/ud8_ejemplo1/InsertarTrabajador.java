package com.example.ud8_ejemplo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertarTrabajador extends AppCompatActivity {

    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_trabajador);

        nombre = findViewById(R.id.nombreEdit);
        Button button = findViewById(R.id.insertarBoton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();

                // Si el editText está vacío, cancelamos.
                if (TextUtils.isEmpty(nombre.getText())) {
                    setResult(RESULT_CANCELED, intent);
                }
                else {
                    String nombreTrabajador = nombre.getText().toString();

                    intent.putExtra("trabajador", nombreTrabajador);

                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}
