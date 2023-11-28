package com.example.pt7_gilromero_luisfelipe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button pelicula;

    Button grupo_musical_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pelicula = findViewById(R.id.pelicula);
        pelicula.setOnClickListener(this);

        grupo_musical_button = findViewById(R.id.grupo_musical_button);
        grupo_musical_button.setOnClickListener(this);
    }

    @Override

    public void onClick(View view) {
        if (view.getId()==R.id.pelicula){
            Intent intent = new Intent(this, Pelicula.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.grupo_musical){
            Intent intent = new Intent(this, Grupo.class);
            startActivity(intent);
        }

    }
}