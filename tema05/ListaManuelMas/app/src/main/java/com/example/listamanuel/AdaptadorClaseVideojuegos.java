package com.example.listamanuel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorClaseVideojuegos
        extends RecyclerView.Adapter<AdaptadorClaseVideojuegos.ViewHolder> {

    // La lista de datos que va a pintar
    private final ArrayList<Videojuegos> videojuegos;

    public AdaptadorClaseVideojuegos(ArrayList<Videojuegos> videojuegos) {
        this.videojuegos = videojuegos;
    }

    // El ViewHolder guarda las vistas de UNA fila
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo;
        TextView tvSubtitulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listavideojuegos, parent, false);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Videojuegos videojuego = videojuegos.get(position);
        holder.tvTitulo.setText(videojuego.getTitulo());
        holder.tvSubtitulo.setText(videojuego.getPlataforma());
        holder.ivImagen.setImageResource(videojuego.getImagen());
    }

    @Override
    public int getItemCount() {
        return videojuegos.size();
    }
}