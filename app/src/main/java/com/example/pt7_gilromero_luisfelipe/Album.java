package com.example.pt7_gilromero_luisfelipe;

import java.util.ArrayList;

public class Album {
    private String artist;
    private ArrayList<String> songs;

    public Album(String artist, String songs) {
        this.artist = artist;
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setArtist(String name) {
        this.artist = name;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

}
