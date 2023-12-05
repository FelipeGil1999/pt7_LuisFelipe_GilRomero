package com.example.pt7_gilromero_luisfelipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Album {
    String name;

    public Album(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
