package com.josue_martinez.memory.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.josue_martinez.memory.R;
import com.josue_martinez.memory.models.Puntuacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;


public class MedioActivity extends AppCompatActivity {
    //API
    private static final String URL1 = "https://project-memorygame.herokuapp.com/puntuaciones";
    //realm
    Realm realm;
    //Resultado de los registros de la tabla
    RealmResults<Puntuacion> realmPuntuaciones;

    //Intent
    Intent intentMain;

    private String dificultad;
    private int nivelDificultad = 3;
    TextView dificultadTextView;
    TextView timerTextView;
    int count;
    Timer T;

    //Imagenes
    ImageView imageView11, imageView12, imageView13, imageView14, imageView21, imageView22,
            imageView23, imageView24, imageView31, imageView32, imageView33, imageView34,
            imageView41, imageView42, imageView43, imageView44;

    //array para las imagenes
    Integer[] arrayCards = {101, 102, 103, 104, 105, 106, 107, 108, 201, 202, 203, 204, 205, 206, 207, 208};

    //source de las imagenes
    int image101, image102, image103, image104, image105, image106, image107, image108, image201,
            image202, image203, image204, image205, image206, image207, image208;

    //atributos para la gestion del juego
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio);

        //Obtengo la instancia del realm
        realm = Realm.getDefaultInstance();
        //realmPuntuaiones sera el resultado de la select de la base de datos
        realmPuntuaciones = realm.where(Puntuacion.class).findAll();

        //Intent Activities
        intentMain = new Intent(this,MainActivity.class);


        //busco imagenes
        buscoImagenes();

        //Pongo la imagen de las espadas
        asignoImagenEspadas();

        //Asigno un tag a cada view
        asignoTags();

        //cargar la imagen de cada carta (parte delantera)
        parteDelanteraCartasImagenes();

        //Desordeno las imagenes (para que sea aleatorio) <--
        Collections.shuffle(Arrays.asList(arrayCards));

        //Gestiono los botones de las imagenes
        botonesImagenes();

        //Modifico dificultad
        if (getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            dificultad = bundle.getString("dificultad");
        }
        dificultadTextView = (TextView) findViewById(R.id.dificultadTextView);
        dificultadTextView.setText(dificultad);

        //Busco mi timer
        timerTextView =(TextView) findViewById(R.id.timerTextView);
        count = 0;

        //Counter para el tiempo
        temporizador();

    }

    private void temporizador() {
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        timerTextView.setText(Integer.toString(count));
                        count++;
                    }
                });
            }
        }, 1000, 1000);
    }

    private void botonesImagenes() {
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView11, theCard);
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView12, theCard);
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView13, theCard);
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView14, theCard);
            }
        });
        imageView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView21, theCard);
            }
        });
        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView22, theCard);
            }
        });
        imageView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView23, theCard);
            }
        });
        imageView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView24, theCard);
            }
        });
        imageView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView31, theCard);
            }
        });
        imageView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView32, theCard);
            }
        });
        imageView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView33, theCard);
            }
        });
        imageView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView34, theCard);
            }
        });
        imageView41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView41, theCard);
            }
        });
        imageView42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView42, theCard);
            }
        });
        imageView43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView43, theCard);
            }
        });
        imageView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView44, theCard);
            }
        });
    }

    private void asignoTags() {
        imageView11.setTag("0");
        imageView12.setTag("1");
        imageView13.setTag("2");
        imageView14.setTag("3");
        imageView21.setTag("4");
        imageView22.setTag("5");
        imageView23.setTag("6");
        imageView24.setTag("7");
        imageView31.setTag("8");
        imageView32.setTag("9");
        imageView33.setTag("10");
        imageView34.setTag("11");
        imageView41.setTag("12");
        imageView42.setTag("13");
        imageView43.setTag("14");
        imageView44.setTag("15");
    }

    private void asignoImagenEspadas() {
        imageView11.setImageResource(R.drawable.espadas);
        imageView12.setImageResource(R.drawable.espadas);
        imageView13.setImageResource(R.drawable.espadas);
        imageView14.setImageResource(R.drawable.espadas);
        imageView21.setImageResource(R.drawable.espadas);
        imageView22.setImageResource(R.drawable.espadas);
        imageView23.setImageResource(R.drawable.espadas);
        imageView24.setImageResource(R.drawable.espadas);
        imageView31.setImageResource(R.drawable.espadas);
        imageView32.setImageResource(R.drawable.espadas);
        imageView33.setImageResource(R.drawable.espadas);
        imageView34.setImageResource(R.drawable.espadas);
        imageView41.setImageResource(R.drawable.espadas);
        imageView42.setImageResource(R.drawable.espadas);
        imageView43.setImageResource(R.drawable.espadas);
        imageView44.setImageResource(R.drawable.espadas);
    }

    private void buscoImagenes() {
        imageView11 = (ImageView) findViewById(R.id.imageButton1);
        imageView12 = (ImageView) findViewById(R.id.imageButton2);
        imageView13 = (ImageView) findViewById(R.id.imageButton3);
        imageView14 = (ImageView) findViewById(R.id.imageButton4);
        imageView21 = (ImageView) findViewById(R.id.imageButton5);
        imageView22 = (ImageView) findViewById(R.id.imageButton6);
        imageView23 = (ImageView) findViewById(R.id.imageButton7);
        imageView24 = (ImageView) findViewById(R.id.imageButton8);
        imageView31 = (ImageView) findViewById(R.id.imageButton9);
        imageView32 = (ImageView) findViewById(R.id.imageButton10);
        imageView33 = (ImageView) findViewById(R.id.imageButton11);
        imageView34 = (ImageView) findViewById(R.id.imageButton12);
        imageView41 = (ImageView) findViewById(R.id.imageButton13);
        imageView42 = (ImageView) findViewById(R.id.imageButton14);
        imageView43 = (ImageView) findViewById(R.id.imageButton15);
        imageView44 = (ImageView) findViewById(R.id.imageButton16);
    }

    private void logicaMemory(ImageView imageView, int card){
        //Aqui pongo la imagen que le corresponde a cada card
        if (arrayCards[card] == 101){
            imageView.setImageResource(image101);
        } else if(arrayCards[card] == 102){
            imageView.setImageResource(image102);
        } else if(arrayCards[card] == 103){
            imageView.setImageResource(image103);
        } else if(arrayCards[card] == 104){
            imageView.setImageResource(image104);
        } else if(arrayCards[card] == 105){
            imageView.setImageResource(image105);
        } else if(arrayCards[card] == 106){
            imageView.setImageResource(image106);
        } else if(arrayCards[card] == 107){
            imageView.setImageResource(image107);
        } else if(arrayCards[card] == 108){
            imageView.setImageResource(image108);
        } else if(arrayCards[card] == 201){
            imageView.setImageResource(image201);
        } else if(arrayCards[card] == 202){
            imageView.setImageResource(image202);
        } else if(arrayCards[card] == 203){
            imageView.setImageResource(image203);
        } else if(arrayCards[card] == 204){
            imageView.setImageResource(image204);
        } else if(arrayCards[card] == 205){
            imageView.setImageResource(image205);
        } else if(arrayCards[card] == 206){
            imageView.setImageResource(image206);
        } else if(arrayCards[card] == 207){
            imageView.setImageResource(image207);
        } else if(arrayCards[card] == 208){
            imageView.setImageResource(image208);
        }

        //Aqui compruebo que card está seleccionada y lo guardo temporalmente
        if (cardNumber == 1){
            firstCard = arrayCards[card];
            if (firstCard >  200){
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;
            imageView.setEnabled(false);
        }
        else if (cardNumber == 2){
            secondCard = arrayCards[card];
            if (secondCard >  200){
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            imageView11.setEnabled(false);
            imageView12.setEnabled(false);
            imageView13.setEnabled(false);
            imageView14.setEnabled(false);
            imageView21.setEnabled(false);
            imageView22.setEnabled(false);
            imageView23.setEnabled(false);
            imageView24.setEnabled(false);
            imageView31.setEnabled(false);
            imageView32.setEnabled(false);
            imageView33.setEnabled(false);
            imageView34.setEnabled(false);
            imageView41.setEnabled(false);
            imageView42.setEnabled(false);
            imageView43.setEnabled(false);
            imageView44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Aqui compruebo si las imagenes seleccionadas equivalen
                    calcular();
                }
            }, 1000);
        }
    }

    private void calcular(){
        if (firstCard == secondCard){
            //Si las imagenes son iguales las quito de la pantalla
            if(clickedFirst == 0){
                imageView11.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
                imageView12.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 2){
                imageView13.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 3){
                imageView14.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 4){
                imageView21.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 5){
                imageView22.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 6){
                imageView23.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 7){
                imageView24.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 8){
                imageView31.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 9){
                imageView32.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 10){
                imageView33.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 11){
                imageView34.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 12){
                imageView41.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 13){
                imageView42.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 14){
                imageView43.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 15){
                imageView44.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                imageView11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                imageView12.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 2){
                imageView13.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 3){
                imageView14.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 4){
                imageView21.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 5){
                imageView22.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 6){
                imageView23.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 7){
                imageView24.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 8){
                imageView31.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 9){
                imageView32.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 10){
                imageView33.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 11){
                imageView34.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 12){
                imageView41.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 13){
                imageView42.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 14){
                imageView43.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 15){
                imageView44.setVisibility(View.INVISIBLE);
            }
        }
        else{
            //Si las imagenes no son iguales les doy la "vuelta" a las cartas.
            asignoImagenEspadas();
        }

        imageView11.setEnabled(true);
        imageView12.setEnabled(true);
        imageView13.setEnabled(true);
        imageView14.setEnabled(true);
        imageView21.setEnabled(true);
        imageView22.setEnabled(true);
        imageView23.setEnabled(true);
        imageView24.setEnabled(true);
        imageView31.setEnabled(true);
        imageView32.setEnabled(true);
        imageView33.setEnabled(true);
        imageView34.setEnabled(true);
        imageView41.setEnabled(true);
        imageView42.setEnabled(true);
        imageView43.setEnabled(true);
        imageView44.setEnabled(true);

        //Compruebo si el juego ha acabado
        checkEnd();
    }
    private void checkEnd(){
        //si no aparece ninguna card en la pantalla:
        if (imageView11.getVisibility() == View.INVISIBLE &&
                imageView12.getVisibility() == View.INVISIBLE &&
                imageView13.getVisibility() == View.INVISIBLE &&
                imageView14.getVisibility() == View.INVISIBLE &&
                imageView21.getVisibility() == View.INVISIBLE &&
                imageView22.getVisibility() == View.INVISIBLE &&
                imageView23.getVisibility() == View.INVISIBLE &&
                imageView24.getVisibility() == View.INVISIBLE &&
                imageView31.getVisibility() == View.INVISIBLE &&
                imageView32.getVisibility() == View.INVISIBLE &&
                imageView33.getVisibility() == View.INVISIBLE &&
                imageView34.getVisibility() == View.INVISIBLE &&
                imageView41.getVisibility() == View.INVISIBLE &&
                imageView42.getVisibility() == View.INVISIBLE &&
                imageView43.getVisibility() == View.INVISIBLE &&
                imageView44.getVisibility() == View.INVISIBLE){
            String puntuacion = (timerTextView.getText().toString());
            onEnd(puntuacion);
        }
    }

    private void parteDelanteraCartasImagenes(){
        image101 = R.drawable.icon101;
        image102 = R.drawable.icon102;
        image103 = R.drawable.icon103;
        image104 = R.drawable.icon104;
        image105 = R.drawable.icon105;
        image106 = R.drawable.icon106;
        image107 = R.drawable.icon107;
        image108 = R.drawable.icon108;
        image201 = R.drawable.icon201;
        image202 = R.drawable.icon202;
        image203 = R.drawable.icon203;
        image204 = R.drawable.icon204;
        image205 = R.drawable.icon205;
        image206 = R.drawable.icon206;
        image207 = R.drawable.icon207;
        image208 = R.drawable.icon208;
    }
    public void onEnd(String puntuacion){
        //Cuando ganes el juego
        //Paro el reloj
        //T.cancel();
        winShowAlert("¡¡ENHORABUENA!!","Has ganado:", puntuacion);
    }
    private void winShowAlert(String title, String message, String puntuation) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null) {
            builder.setTitle(title);
        }
        if (title != null) {
            builder.setMessage(message);
        }
        //La cargo con el xml de context_menu_dificult
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.context_menu_finish, null);
        builder.setView(viewInflated);

        //Busco el editText del nombre
        final EditText nombreEditText = (EditText) viewInflated.findViewById((R.id.editTextName));
        final TextView puntuacionTextView = (TextView) viewInflated.findViewById(R.id.puntuacionTextView);

        //Calculo y pongo la puntuacion
        int puntuacionReal = (5000 / (Integer.parseInt(puntuation)) * nivelDificultad);
        puntuacionTextView.setText(Integer.toString(puntuacionReal));

        Editable name = nombreEditText.getText();
        //Integer puntuacion = Integer.parseInt(puntuacionTextView.getText().toString());

        builder.setNeutralButton("Enviar Datos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //Recojo la informacion y la almaceno en mi realm
/*                realm.beginTransaction();
                Puntuacion newPuntuacion = new Puntuacion(puntuacionReal, name.toString());
                realm.copyToRealm(newPuntuacion);
                realm.commitTransaction();*/

                //API
                postRequest(name.toString(), puntuacionReal);

                //Vuelvo al Activity principal
                startActivity(intentMain);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void postRequest(String nombre, Integer puntos) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            //input your API parameters: Aqui puntos y nombre
            jsonObject.put("puntos",puntos);
            jsonObject.put("nombre",nombre);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                URL1,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}