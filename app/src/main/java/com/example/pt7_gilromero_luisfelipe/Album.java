package com.example.pt7_gilromero_luisfelipe;

import org.json.JSONArray;

import java.util.ArrayList;

public class Album {
    private String artist;
    private String songs;

    public Album(String artist, String songs) {
        this.artist = artist;
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public String getSongs() {
        return songs;
    }

    public void setArtist(String name) {
        this.artist = name;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

}
