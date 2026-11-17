package com.example.listamanuel;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        // 1. Los datos: se leen desde res/raw/datos.json
        ArrayList<Videojuegos> videojuegos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(leerRaw(R.raw.datos));
            for (int i = 0; i < array.length(); i++) {
                JSONObject objeto = array.getJSONObject(i);
                String titulo = objeto.getString("titulo");
                String plataforma = objeto.getString("plataforma");
                String imagen = objeto.getString("imagen");

                videojuegos.add(new Videojuegos(titulo, plataforma, imagen));
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al leer el JSON", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "Error al leer datos.json", e);
        }

        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvLista.setAdapter(new AdaptadorClaseVideojuegos(videojuegos));
    }

    private String leerRaw(int idRecurso) throws IOException {
        try (InputStream entrada = getResources().openRawResource(idRecurso);
             BufferedReader lector = new BufferedReader(
                     new InputStreamReader(entrada, StandardCharsets.UTF_8))) {
            StringBuilder texto = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                texto.append(linea).append('\n');
            }
            return texto.toString();
        }
    }
}