package com.josue_martinez.memory.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.josue_martinez.memory.models.Puntuacion;
import com.josue_martinez.memory.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class PuntuacionAdapter  extends RecyclerView.Adapter<PuntuacionAdapter.PuntuacionHolder> {
    private ArrayList<Puntuacion> listaPuntuaciones;

    public PuntuacionAdapter(ArrayList<Puntuacion> listaPuntuaciones){
        this.listaPuntuaciones = listaPuntuaciones;
    }

    @NonNull
    @Override
    public PuntuacionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new PuntuacionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PuntuacionHolder holder, final int position) {
        holder.assignData(listaPuntuaciones.get(position));
    }

    @Override
    public int getItemCount() {

        return listaPuntuaciones.size();
    }

    public class PuntuacionHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView puntuacion;

        public PuntuacionHolder(@NonNull  View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            puntuacion = (TextView) itemView.findViewById(R.id.textPuntuacion);
        }

        public void assignData( Puntuacion puntuacion) {
            this.nombre.setText(((puntuacion.getNombre())).toString());
            this.puntuacion.setText((puntuacion.getPuntos()).toString());
        }

    }
}
