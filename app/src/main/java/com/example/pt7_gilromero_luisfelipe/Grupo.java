package com.example.pt7_gilromero_luisfelipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends AppCompatActivity {

    EditText grupo;

    private RequestQueue queue = null;
    private final List<Album> elements = new ArrayList<>();
    public List<Album> getElements() {
        return elements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(elements);

        RecyclerView listSongs = findViewById(R.id.list_songs);
        listSongs.setAdapter(adapter);

        grupo = findViewById(R.id.grupo);
        String nombre = grupo.getText().toString();
        nombre = nombre.toLowerCase();
        nombre = nombre.replace(" ", "%20");


        String link = "https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=8a263c45ed98a92790c525a6ee0d5c76&artist=" + nombre + "&album=Cross%20Road&format=json";

        if (hiHaConnexio())
            //Cargamos los datos de la url en el recycler listTeams
            loadData(listSongs, link);
        else
            Toast.makeText(getApplicationContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
    }

    private boolean hiHaConnexio() {
        boolean resultat = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // Comprovem la versió del dispositiu Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    resultat = true;
                }
            }
        } else { //versions anteriors d'Android
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                resultat = true;
            } else {
                resultat = false;
            }
        }

        return resultat;
    }

    private void loadData( RecyclerView listSongs, String url) {
        if ( queue == null )
            queue = Volley.newRequestQueue(this);
        //Definimos un JsonRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            elements.clear();
                            JSONArray trackArray = response.getJSONObject("album").getJSONObject("tracks").getJSONArray("track");
                            for (int i = 0; i < trackArray.length(); i++) {
                                JSONObject trackObject = trackArray.getJSONObject(i);
                                Album album = new Album(
                                        response.getJSONObject("album").getString("artist"),
                                        trackObject.getString("name")
                                );
                                elements.add(album);
                            }

                            listSongs.getAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Grupo.this, "Error loading image: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }


}