package com.example.reproductormanuelmas;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private SeekBar seekBar;
    private TextView tvTiempoActual, tvTiempoTotal;
    private Button btnPlay, btnPausa;
    private Button btnEfecto1, btnEfecto2, btnEfecto3, btnEfecto4;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateRunnable;

    private SoundPool soundPool;
    private int idEfecto1, idEfecto2, idEfecto3, idEfecto4;
    private int sonidosCargados = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        tvTiempoActual = findViewById(R.id.tvTiempoActual);
        tvTiempoTotal = findViewById(R.id.tvTiempoTotal);
        btnPlay = findViewById(R.id.btnPlay);
        btnPausa = findViewById(R.id.btnPausa);

        btnEfecto1 = findViewById(R.id.btnEfecto1);
        btnEfecto2 = findViewById(R.id.btnEfecto2);
        btnEfecto3 = findViewById(R.id.btnEfecto3);
        btnEfecto4 = findViewById(R.id.btnEfecto4);

        // Inicializar ExoPlayer con Uri android.resource
        player = new ExoPlayer.Builder(this).build();
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.brigantes);
        player.setMediaItem(MediaItem.fromUri(uri));
        player.prepare();

        // Escuchar cuando la canción esté lista
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == Player.STATE_READY) {
                    long duracionMs = player.getDuration();
                    seekBar.setMax((int) duracionMs);
                    tvTiempoTotal.setText(formatearTiempo(duracionMs));
                } else if (state == Player.STATE_ENDED) {
                    player.seekTo(0);
                    player.pause();
                }
            }
        });

        // Botón Play: reproduce o reinicia si ha terminado
        btnPlay.setOnClickListener(v -> {
            if (player != null) {
                if (player.getPlaybackState() == Player.STATE_ENDED) {
                    player.seekTo(0);
                }
                player.play();
            }
        });

        // Botón Pausa
        btnPausa.setOnClickListener(v -> {
            if (player != null) {
                player.pause();
            }
        });

        // Evento al soltar la barra de progreso
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar sb) {}

            @Override
            public void onStopTrackingTouch(SeekBar sb) {
                if (player != null) {
                    player.seekTo(sb.getProgress());
                }
            }
        });

        // Handler para mover la barra cada 500 ms
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                if (player != null && player.isPlaying()) {
                    long pos = player.getCurrentPosition();
                    seekBar.setProgress((int) pos);
                    tvTiempoActual.setText(formatearTiempo(pos));
                }
                handler.postDelayed(this, 500);
            }
        };

        configurarSoundPool();
    }

    private String formatearTiempo(long ms) {
        long segundos = ms / 1000;
        return String.format(Locale.getDefault(), "%02d:%02d", segundos / 60, segundos % 60);
    }

    private void configurarSoundPool() {
        AudioAttributes atributos = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(atributos)
                .build();

        soundPool.setOnLoadCompleteListener((sp, sampleId, status) -> {
            if (status == 0) {
                sonidosCargados++;
                if (sonidosCargados == 4) {
                    btnEfecto1.setEnabled(true);
                    btnEfecto2.setEnabled(true);
                    btnEfecto3.setEnabled(true);
                    btnEfecto4.setEnabled(true);
                }
            }
        });

        idEfecto1 = soundPool.load(this, R.raw.sonido1, 1);
        idEfecto2 = soundPool.load(this, R.raw.sonido2, 1);
        idEfecto3 = soundPool.load(this, R.raw.sonido3, 1);
        idEfecto4 = soundPool.load(this, R.raw.sonido4, 1);

        btnEfecto1.setOnClickListener(v -> soundPool.play(idEfecto1, 1f, 1f, 1, 0, 1f));
        btnEfecto2.setOnClickListener(v -> soundPool.play(idEfecto2, 1f, 1f, 1, 0, 1f));
        btnEfecto3.setOnClickListener(v -> soundPool.play(idEfecto3, 1f, 1f, 1, 0, 1f));
        btnEfecto4.setOnClickListener(v -> soundPool.play(idEfecto4, 1f, 1f, 1, 0, 1f));
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.post(updateRunnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.pause();
        }
        handler.removeCallbacks(updateRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}