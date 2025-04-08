package com.escruceria.activitymodule2_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etTelefono, etEmail, etDescripcion, etFechaNacimiento;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ingresar la información");
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDescripcion = findViewById(R.id.etDescripcion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        // Mostrar el calendario moderno al hacer clic en la fecha
        etFechaNacimiento.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Selecciona tu fecha de nacimiento")
                    .build();

            datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

            datePicker.addOnPositiveButtonClickListener(selection -> {
                // Convertir el Long a una fecha legible
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
                String fechaFormateada = sdf.format(new Date(selection));

                etFechaNacimiento.setText(fechaFormateada);
            });
        });

        // Recuperar datos si vienen desde Confirmar Datos Activity
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("nombre")) etNombre.setText(intent.getStringExtra("nombre"));
            if (intent.hasExtra("telefono")) etTelefono.setText(intent.getStringExtra("telefono"));
            if (intent.hasExtra("email")) etEmail.setText(intent.getStringExtra("email"));
            if (intent.hasExtra("descripcion")) etDescripcion.setText(intent.getStringExtra("descripcion"));
            if (intent.hasExtra("fecha")) etFechaNacimiento.setText(intent.getStringExtra("fecha"));
        }

        // Botón Siguiente
        btnSiguiente.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, ConfirmarDatosActivity.class);
            intent1.putExtra("nombre", etNombre.getText().toString());
            intent1.putExtra("telefono", etTelefono.getText().toString());
            intent1.putExtra("email", etEmail.getText().toString());
            intent1.putExtra("descripcion", etDescripcion.getText().toString());
            intent1.putExtra("fecha", etFechaNacimiento.getText().toString());
            startActivity(intent1);
        });
    }
}