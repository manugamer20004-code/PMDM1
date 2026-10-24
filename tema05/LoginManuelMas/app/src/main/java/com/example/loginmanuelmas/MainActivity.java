package com.example.loginmanuelmas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {

    // Usuario válido
    private static final String CORREO_VALIDO = "manuel35@gmail.com";
    private static final String PASS_VALIDA = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Las vistas del diseño
        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etContrasena = findViewById(R.id.etContrasena);
        MaterialSwitch swRecordar = findViewById(R.id.swRecordar);
        Button btnContinuar = findViewById(R.id.btnContinuar);
        TextView tvMensaje = findViewById(R.id.tvMensaje);

        // Al pulsar Continuar
        btnContinuar.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString();
            String pass = etContrasena.getText().toString();

            // 1.4: si hay algún campo vacío, se avisa y no se comprueba nada más
            boolean hayVacios = false;
            if (correo.isEmpty()) {
                etCorreo.setError(getString(R.string.error_correo));
                etCorreo.requestFocus();      // para que se vea el globo con el mensaje
                hayVacios = true;
            }
            if (pass.isEmpty()) {
                etContrasena.setError(getString(R.string.error_contrasena));
                if (!correo.isEmpty()) {
                    etContrasena.requestFocus();   // solo si el correo sí está escrito
                }
                hayVacios = true;
            }
            if (hayVacios) {
                tvMensaje.setText("");
                return;
            }

            // 1.2 y 1.3: comprobación del usuario
            if (correo.equals(CORREO_VALIDO) && pass.equals(PASS_VALIDA)) {
                // Datos correctos: mensaje en verde
                tvMensaje.setTextColor(getColor(R.color.verde_ok));
                if (swRecordar.isChecked()) {
                    tvMensaje.setText(R.string.msg_correcto_recordar);
                } else {
                    tvMensaje.setText(R.string.msg_correcto);
                }
            } else {
                // Algún dato falla: mensaje en rojo
                tvMensaje.setTextColor(getColor(R.color.rojo_error));
                tvMensaje.setText(R.string.msg_incorrecto);
            }
        });
    }
}