package com.example.pt7_gilromero_luisfelipe;

import org.json.JSONArray;
import org.json.JSONObject;

public class Disc {

    String name;
    int playcount;


    String url;
    JSONObject artist;

    JSONArray image;


    //Constructor
    public Disc(String name, int playcount, String url, JSONObject artist, JSONArray image) {
        this.name = name;
        this.playcount = playcount;
        this.url = url;
        this.artist = artist;
        this.image = image;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getPlaycount() {
        return playcount;
    }


    public String getUrl() {
        return url;
    }

    public JSONObject getArtist() {
        return artist;
    }

    public JSONArray getImage() {
        return image;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setArtist(JSONObject artist) {
        this.artist = artist;
    }

    public void setImage(JSONArray image) {
        this.image = image;
    }



}
