package com.example.loginmanuelmas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MenuActivity extends AppCompatActivity {

    // Clave con la que el login pasa el correo al menú
    public static final String EXTRA_CORREO = "correo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // La MaterialToolbar pasa a ser la barra superior de la actividad
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.titulo_menu); // evita que salga el nombre de la app

        // Saludo con el correo que llega del login
        String correo = getIntent().getStringExtra(EXTRA_CORREO);
        TextView tvSaludo = findViewById(R.id.tvSaludo);
        tvSaludo.setText(getString(R.string.saludo) + " " + correo);
    }
}
