package com.example.pt7_gilromero_luisfelipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;


public class Pelicula extends AppCompatActivity implements View.OnClickListener{
    private RequestQueue queue = null;
    private final List<Pelicula_Clase> elements = new ArrayList<>();
    EditText nombre;

    TextView title_p;

    TextView actors_p;

    TextView plot_p;

    ImageView poster_p;

    Button buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        nombre = findViewById(R.id.nombre);
        buscar = findViewById(R.id.buscar);
        buscar.setOnClickListener(this);

        title_p = findViewById(R.id.title_p);
        actors_p = findViewById(R.id.actors_p);
        plot_p = findViewById(R.id.plot_p);
        poster_p = findViewById(R.id.poster_p);

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
                            String poster = "";

                                Pelicula_Clase pelicula = new Pelicula_Clase(
                                        response.getString("Title"),
                                        response.getString("Actors"),
                                        response.getString("Poster"),
                                        response.getString("Plot")

                                );

                            title_p.setText(pelicula.getTitle());
                            actors_p.setText(pelicula.getActors());
                            plot_p.setText(pelicula.getPlot());
                            poster = pelicula.getPoster();

                            loadImage(poster);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Pelicula.this, "Error loading image: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void loadImage(String URL2) {

        ImageRequest imageRequest = new ImageRequest(
                URL2,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        poster_p.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER_INSIDE,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Pelicula.this, "Error loading image: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add requests to the Volley queue
        queue = Volley.newRequestQueue(this);
        queue.add(imageRequest);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.buscar){
            String Apikey = "d368dd8a";

            String nombre1 = nombre.getText().toString();
            nombre1 = nombre1.toLowerCase();
            nombre1 = nombre1.replace(" ", "%20");

            String link = "https://www.omdbapi.com/?t=" + nombre1 + "&apikey=" + Apikey;
            System.out.println(link);

            loadData(link);
        }
    }
}