package com.example.pt7_gilromero_luisfelipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Songs extends AppCompatActivity {

    private RequestQueue queue = null;
    ImageView image_disc;

    TextView songs;

    private final List<Album> elements = new ArrayList<>();
    public List<Album> getElements() {
        return elements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String image = intent.getStringExtra("image");
        final String artist = intent.getStringExtra("artist");

        image_disc = findViewById(R.id.image_disc);
        songs = findViewById(R.id.songs);

        String Apikey = "8a263c45ed98a92790c525a6ee0d5c76";

        loadImage(image);
        loadData("https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=" + Apikey + "&artist=" + artist + "&album=" + name + "&format=json");

    }


    //Metodo para cargar la imagen
    private void loadImage(String URL2) {

        ImageRequest imageRequest = new ImageRequest(
                URL2,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        image_disc.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER_INSIDE,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Songs.this, "Error loading image: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add requests to the Volley queue
        queue = Volley.newRequestQueue(this);
        queue.add(imageRequest);
    }

    private void loadData( String url) {
        if ( queue == null )
            queue = Volley.newRequestQueue(this);
        //Definimos un JsonRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray trackArray = response.getJSONObject("album").getJSONObject("tracks").getJSONArray("track");


                            for (int i = 0; i < trackArray.length(); i++) {
                                JSONObject trackObject = trackArray.getJSONObject(i);
                                String name = trackObject.getString("name");



                                Album song = new Album(name);

                                elements.add(song);
                            }



                        String concatenado = "";

                        for (int j = 0; j<elements.size(); j++){
                            concatenado = concatenado + elements.get(j).getName() + ",";
                        }
                        songs.setText(concatenado);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Songs.this, "Error loading image: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }
}