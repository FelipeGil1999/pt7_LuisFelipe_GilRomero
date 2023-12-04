package com.example.pt7_gilromero_luisfelipe;



import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONException;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    //Definimos variables
    private List<Disc> elements;


    //Constructor
    public MyRecyclerViewAdapter(List<Disc> elements){
        this.elements = elements;
    }


    //Metodo llamado para crear un viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el diseño de la vista de cada elemento en el RecyclerView
        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);

        // Crea y devuelve una nueva instancia de ViewHolder
        return new ViewHolder(viewElement);
    }

    // Método llamado cuando se actualiza el contenido de un ViewHolder con datos específicos
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Configura el contenido del ViewHolder en la posición especificada
        holder.getTxtElement().setText(elements.get(position).getName());
    }

    // Método que devuelve la cantidad total de elementos en el RecyclerView
    @Override
    public int getItemCount() {
        return elements.size();
    }

    // Clase interna que representa un ViewHolder para cada elemento en el RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textElement;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                // Configura un OnClickListener para el elemento de la vista
                @Override
                public void onClick(View v) {
                    // Al hacer clic, muestra el elemento en otra pantalla
                    ViewHolder.this.mostraElement(v);

                }
            });

            textElement = itemView.findViewById(R.id.item_song);

        }



        private void mostraElement(View v) throws JSONException {

            // Cridem la pantalla de mostrar personatge i li passem les dades
            Intent mostrarSongs = new Intent(v.getContext(), Songs.class);
            Disc disco = elements.get(getAdapterPosition());

            mostrarSongs.putExtra("name", disco.getName());

            //mostrarTeam.putExtra("id", ligue.getTeam_id());
            //mostrarTeam.putExtra("team_abbreviation", ligue.getTeam_abbreviation());
            //mostrarTeam.putExtra("name", ligue.getTeam_name());
            //mostrarTeam.putExtra("extension", extension);
            //mostrarTeam.putExtra("link", link);
            v.getContext().startActivity(mostrarSongs);

        }

        // Métodos getter para obtener las referencias a los elementos de texto
        public TextView getTxtElement() {
            return textElement;
        }

    }



}

