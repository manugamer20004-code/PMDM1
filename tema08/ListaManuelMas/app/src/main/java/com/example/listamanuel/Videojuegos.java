package com.example.listamanuel;

public class Videojuegos {
    private String titulo;
    private String plataforma;
    private String imagen;

    public Videojuegos(String titulo, String plataforma, String imagen) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.imagen = imagen;
    }

    public String getTitulo() { return titulo; }

    public String getPlataforma() { return plataforma; }

    public String getImagen() { return imagen; }
}