package com.example.listamanuel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        // 1. Los datos: al menos 8 elementos
        ArrayList<Videojuegos> videojuegos = new ArrayList<>();
        videojuegos.add(new Videojuegos("Call of Duty: Mobile", "Móvil", R.drawable.call_of_duty_logo));
        videojuegos.add(new Videojuegos("Free Fire", "Móvil", R.drawable.free_fire_logo));
        videojuegos.add(new Videojuegos("Grand Theft Auto V", "PlayStation 5", R.drawable.gta__grand_theft_auto__logo));
        videojuegos.add(new Videojuegos("Minecraft", "PC", R.drawable.minecraft_logo));
        videojuegos.add(new Videojuegos("Mobile Legends", "Móvil", R.drawable.mobile_legends_logo));
        videojuegos.add(new Videojuegos("PUBG: Battlegrounds", "PC", R.drawable.pubg_battlegrounds_logo));
        videojuegos.add(new Videojuegos("Roblox", "PC", R.drawable.roblox_icon));
        videojuegos.add(new Videojuegos("Valorant", "PC", R.drawable.valorant_logo));

        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvLista.setAdapter(new AdaptadorClaseVideojuegos(videojuegos));
    }
}