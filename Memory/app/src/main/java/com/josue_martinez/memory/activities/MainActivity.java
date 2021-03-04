package com.josue_martinez.memory.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.josue_martinez.memory.R;

public class MainActivity extends AppCompatActivity {
    //Botones
    Button botonJugar;
    Button botonPuntuaciones;
    Button botonCreditos;

    //Intents
    Intent intentFacil;
    Intent intentMedio;
    Intent intentDificil;
    Intent intentPuntuacion;
    Intent intentCreditos;

    //String que indica la dificultad posteriormente en los activitis de las tablas
    String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intents (activities)
        intentFacil = new Intent(this,FacilActivity.class);
        intentMedio = new Intent(this,MedioActivity.class);
        intentDificil = new Intent(this,DificilActivity.class);
        intentPuntuacion = new Intent(this, PuntuacionesActivity.class);
        intentCreditos = new Intent(this, CreditosActivity.class);

        //Cargo los botones
        botonJugar =(Button)findViewById(R.id.jugarButton);
        botonPuntuaciones = (Button) findViewById(R.id.puntuacionesButton);
        botonCreditos = (Button) findViewById(R.id.creditosButton);

        //JUGAR
        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDificultSelection(getResources().getString(R.string.nuevajugada), getResources().getString(R.string.seleccionardificultad));
            }
        });

        //PUNTUACIONES
        botonPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPuntuacion);
            }
        });

        //CREDITOS
        botonCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentCreditos);
            }
        });
    }

    //Crear ventana para el context menu
    private void showAlertDificultSelection(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null) {
            builder.setTitle(title);
        }
        if (title != null) {
            builder.setMessage(message);
        }
        //La cargo con el xml de context_menu_dificult
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.context_menu_dificult,null);
        builder.setView(viewInflated);

        //Busca los radioButtons de la dificultad
        final RadioButton radFacil = (RadioButton) viewInflated.findViewById((R.id.radioButtonFacil));
        final RadioButton radMedio = (RadioButton) viewInflated.findViewById((R.id.radioButtonMedio));
        final RadioButton radDificil = (RadioButton) viewInflated.findViewById((R.id.radioButtonDificil));

        builder.setNeutralButton(R.string.jugar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //Recojo la eleccion:
                if (radFacil.isChecked()){
                    dificultad = getResources().getString(R.string.modofacil);
                    intentFacil.putExtra("dificultad",dificultad);
                    //Ir al activity Facil
                    startActivity(intentFacil);
                }
                else if(radMedio.isChecked()){
                    dificultad = getResources().getString(R.string.modomedio);
                    intentMedio.putExtra("dificultad",dificultad);
                    //Ir al activity Medio
                    startActivity(intentMedio);
                }
                else if(radDificil.isChecked()){
                    dificultad = getResources().getString(R.string.mododificil);
                    intentDificil.putExtra("dificultad",dificultad);
                    //Ir al activity Dificil
                    startActivity(intentDificil);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}