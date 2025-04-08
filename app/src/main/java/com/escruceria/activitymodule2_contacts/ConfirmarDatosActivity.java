package com.escruceria.activitymodule2_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmarDatosActivity extends AppCompatActivity {

    private TextView tvNombre, tvFecha, tvTelefono, tvEmail, tvDescripcion;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Confirmación de los datos ingresados");
        setContentView(R.layout.activity_confirmar_datos);

        tvNombre = findViewById(R.id.tvNombre);
        tvFecha = findViewById(R.id.tvFecha);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        btnEditar = findViewById(R.id.btnEditar);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String fecha = intent.getStringExtra("fecha");
        String telefono = intent.getStringExtra("telefono");
        String email = intent.getStringExtra("email");
        String descripcion = intent.getStringExtra("descripcion");

        setBoldLabel(tvNombre, "Nombre: ", nombre);
        setBoldLabel(tvFecha, "Fecha de nacimiento: ", fecha);
        setBoldLabel(tvTelefono, "Teléfono: ", telefono);
        setBoldLabel(tvEmail, "Email: ", email);
        setBoldLabel(tvDescripcion, "Descripción: ", descripcion);

        btnEditar.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("nombre", nombre);
            i.putExtra("fecha", fecha);
            i.putExtra("telefono", telefono);
            i.putExtra("email", email);
            i.putExtra("descripcion", descripcion);
            startActivity(i);
            finish();
        });
    }

    private void setBoldLabel(TextView textView, String label, String value) {
        SpannableStringBuilder builder = new SpannableStringBuilder(label + value);
        builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
}
