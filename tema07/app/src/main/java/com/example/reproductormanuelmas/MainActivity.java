package com.example.reproductormanuelmas;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private Button btnPlay, btnPausa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnPausa = findViewById(R.id.btnPausa);

        //Crear e inicializar ExoPlayer con la canción 
        player = new ExoPlayer.Builder(this).build();
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.brigantes);
        player.setMediaItem(MediaItem.fromUri(uri));
        player.prepare();

        //Acciones para reproductor básico
        btnPlay.setOnClickListener(v -> player.play());
        btnPausa.setOnClickListener(v -> player.pause());
    }
}