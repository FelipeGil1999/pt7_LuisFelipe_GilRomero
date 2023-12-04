package com.example.pt7_gilromero_luisfelipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Album {

    private JSONObject stremeable;
    private int duration;

    private String url;
    private String name;

    private JSONObject attr;

    private JSONObject artist;


    //Constructor
    public Album(JSONObject stremeable, int duration, String url, String name, JSONObject attr, JSONObject artist) {
        this.stremeable = stremeable;
        this.duration = duration;
        this.url = url;
        this.name = name;
        this.attr = attr;
        this.artist = artist;
    }

    //Getters
    public JSONObject getStremeable() {
        return stremeable;
    }

    public int getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public JSONObject getAttr() {
        return attr;
    }

    public JSONObject getArtist() {
        return artist;
    }

    //Setters

    public void setStremeable(JSONObject stremeable) {
        this.stremeable = stremeable;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttr(JSONObject attr) {
        this.attr = attr;
    }

    public void setArtist(JSONObject artist) {
        this.artist = artist;
    }



}
