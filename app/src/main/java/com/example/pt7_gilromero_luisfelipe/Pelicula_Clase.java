package com.example.pt7_gilromero_luisfelipe;

import android.net.Uri;

public class Pelicula_Clase {

    private String Title;
    private String Actors;
    private String Poster;

    private String PLot;

    public Pelicula_Clase(String title, String actors, String poster, String plot) {
        Title = title;
        Actors = actors;
        Poster = poster;
        PLot = plot;
    }

    public String getTitle() {
        return Title;
    }

    public String getActors() {
        return Actors;
    }

    public String getPoster() {
        return Poster;
    }

    public String getPlot() {
        return PLot;
    }




}
