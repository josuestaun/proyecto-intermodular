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


public class DificilActivity extends AppCompatActivity {
    //API
    private static final String URL1 = "https://project-memorygame.herokuapp.com/puntuaciones";
    //realm
    Realm realm;
    //Resultado de los registros de la tabla
    RealmResults<Puntuacion> realmPuntuaciones;

    //Intent
    Intent intentMain;

    private String dificultad;
    private int nivelDificultad = 10;
    TextView dificultadTextView;
    TextView timerTextView;
    int count;
    Timer T;

    //Imagenes
    ImageView imageView11, imageView12, imageView13, imageView14, imageView15, imageView16,
            imageView21, imageView22, imageView23, imageView24, imageView25, imageView26,
            imageView31, imageView32, imageView33, imageView34, imageView35, imageView36,
            imageView41, imageView42, imageView43, imageView44, imageView45, imageView46,
            imageView51, imageView52, imageView53, imageView54, imageView55, imageView56,
            imageView61, imageView62, imageView63, imageView64, imageView65, imageView66;


    //array para las imagenes
    Integer[] arrayCards = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118,
            201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218};

    //source de las imagenes
    int image101, image102, image103, image104, image105, image106, image107, image108, image109, image110, image111,
            image112, image113, image114, image115, image116, image117, image118,
            image201, image202, image203, image204, image205, image206, image207, image208, image209, image210, image211,
            image212, image213, image214, image215, image216, image217, image218;

    //atributos para la gestion del juego
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificil);

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

        //Asigno un Tag a cada imageView
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
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView15, theCard);
            }
        });
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView16, theCard);
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
        imageView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView25, theCard);
            }
        });
        imageView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView26, theCard);
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
        imageView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView35, theCard);
            }
        });
        imageView36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView36, theCard);
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
        imageView45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView45, theCard);
            }
        });
        imageView46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView46, theCard);
            }
        });
        imageView51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView51, theCard);
            }
        });
        imageView52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView52, theCard);
            }
        });
        imageView53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView53, theCard);
            }
        });
        imageView54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView54, theCard);
            }
        });
        imageView55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView55, theCard);
            }
        });
        imageView56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView56, theCard);
            }
        });
        imageView61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView61, theCard);
            }
        });
        imageView62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView62, theCard);
            }
        });
        imageView63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView63, theCard);
            }
        });
        imageView64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView64, theCard);
            }
        });
        imageView65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView65, theCard);
            }
        });
        imageView66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                logicaMemory(imageView66, theCard);
            }
        });
    }

    private void asignoTags() {
        imageView11.setTag("0");
        imageView12.setTag("1");
        imageView13.setTag("2");
        imageView14.setTag("3");
        imageView15.setTag("4");
        imageView16.setTag("5");
        imageView21.setTag("6");
        imageView22.setTag("7");
        imageView23.setTag("8");
        imageView24.setTag("9");
        imageView25.setTag("10");
        imageView26.setTag("11");
        imageView31.setTag("12");
        imageView32.setTag("13");
        imageView33.setTag("14");
        imageView34.setTag("15");
        imageView35.setTag("16");
        imageView36.setTag("17");
        imageView41.setTag("18");
        imageView42.setTag("19");
        imageView43.setTag("20");
        imageView44.setTag("21");
        imageView45.setTag("22");
        imageView46.setTag("23");
        imageView51.setTag("24");
        imageView52.setTag("25");
        imageView53.setTag("26");
        imageView54.setTag("27");
        imageView55.setTag("28");
        imageView56.setTag("29");
        imageView61.setTag("30");
        imageView62.setTag("31");
        imageView63.setTag("32");
        imageView64.setTag("33");
        imageView65.setTag("34");
        imageView66.setTag("35");
    }

    private void asignoImagenEspadas() {
        imageView11.setImageResource(R.drawable.espadas);
        imageView12.setImageResource(R.drawable.espadas);
        imageView13.setImageResource(R.drawable.espadas);
        imageView14.setImageResource(R.drawable.espadas);
        imageView15.setImageResource(R.drawable.espadas);
        imageView16.setImageResource(R.drawable.espadas);
        imageView21.setImageResource(R.drawable.espadas);
        imageView22.setImageResource(R.drawable.espadas);
        imageView23.setImageResource(R.drawable.espadas);
        imageView24.setImageResource(R.drawable.espadas);
        imageView25.setImageResource(R.drawable.espadas);
        imageView26.setImageResource(R.drawable.espadas);
        imageView31.setImageResource(R.drawable.espadas);
        imageView32.setImageResource(R.drawable.espadas);
        imageView33.setImageResource(R.drawable.espadas);
        imageView34.setImageResource(R.drawable.espadas);
        imageView35.setImageResource(R.drawable.espadas);
        imageView36.setImageResource(R.drawable.espadas);
        imageView41.setImageResource(R.drawable.espadas);
        imageView42.setImageResource(R.drawable.espadas);
        imageView43.setImageResource(R.drawable.espadas);
        imageView44.setImageResource(R.drawable.espadas);
        imageView45.setImageResource(R.drawable.espadas);
        imageView46.setImageResource(R.drawable.espadas);
        imageView51.setImageResource(R.drawable.espadas);
        imageView52.setImageResource(R.drawable.espadas);
        imageView53.setImageResource(R.drawable.espadas);
        imageView54.setImageResource(R.drawable.espadas);
        imageView55.setImageResource(R.drawable.espadas);
        imageView56.setImageResource(R.drawable.espadas);
        imageView61.setImageResource(R.drawable.espadas);
        imageView62.setImageResource(R.drawable.espadas);
        imageView63.setImageResource(R.drawable.espadas);
        imageView64.setImageResource(R.drawable.espadas);
        imageView65.setImageResource(R.drawable.espadas);
        imageView66.setImageResource(R.drawable.espadas);
    }

    private void buscoImagenes() {
        imageView11 = (ImageView) findViewById(R.id.imageButton1);
        imageView12 = (ImageView) findViewById(R.id.imageButton2);
        imageView13 = (ImageView) findViewById(R.id.imageButton3);
        imageView14 = (ImageView) findViewById(R.id.imageButton4);
        imageView15 = (ImageView) findViewById(R.id.imageButton5);
        imageView16 = (ImageView) findViewById(R.id.imageButton6);
        imageView21 = (ImageView) findViewById(R.id.imageButton7);
        imageView22 = (ImageView) findViewById(R.id.imageButton8);
        imageView23 = (ImageView) findViewById(R.id.imageButton9);
        imageView24 = (ImageView) findViewById(R.id.imageButton10);
        imageView25 = (ImageView) findViewById(R.id.imageButton11);
        imageView26 = (ImageView) findViewById(R.id.imageButton12);
        imageView31 = (ImageView) findViewById(R.id.imageButton13);
        imageView32 = (ImageView) findViewById(R.id.imageButton14);
        imageView33 = (ImageView) findViewById(R.id.imageButton15);
        imageView34 = (ImageView) findViewById(R.id.imageButton16);
        imageView35 = (ImageView) findViewById(R.id.imageButton17);
        imageView36 = (ImageView) findViewById(R.id.imageButton18);
        imageView41 = (ImageView) findViewById(R.id.imageButton19);
        imageView42 = (ImageView) findViewById(R.id.imageButton20);
        imageView43 = (ImageView) findViewById(R.id.imageButton21);
        imageView44 = (ImageView) findViewById(R.id.imageButton22);
        imageView45 = (ImageView) findViewById(R.id.imageButton23);
        imageView46 = (ImageView) findViewById(R.id.imageButton24);
        imageView51 = (ImageView) findViewById(R.id.imageButton25);
        imageView52 = (ImageView) findViewById(R.id.imageButton26);
        imageView53 = (ImageView) findViewById(R.id.imageButton27);
        imageView54 = (ImageView) findViewById(R.id.imageButton28);
        imageView55 = (ImageView) findViewById(R.id.imageButton29);
        imageView56 = (ImageView) findViewById(R.id.imageButton30);
        imageView61 = (ImageView) findViewById(R.id.imageButton31);
        imageView62 = (ImageView) findViewById(R.id.imageButton32);
        imageView63 = (ImageView) findViewById(R.id.imageButton33);
        imageView64 = (ImageView) findViewById(R.id.imageButton34);
        imageView65 = (ImageView) findViewById(R.id.imageButton35);
        imageView66 = (ImageView) findViewById(R.id.imageButton36);
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
        } else if(arrayCards[card] == 109){
            imageView.setImageResource(image109);
        } else if(arrayCards[card] == 110){
            imageView.setImageResource(image110);
        } else if(arrayCards[card] == 111){
            imageView.setImageResource(image111);
        } else if(arrayCards[card] == 112){
            imageView.setImageResource(image112);
        } else if(arrayCards[card] == 113){
            imageView.setImageResource(image113);
        } else if(arrayCards[card] == 114){
            imageView.setImageResource(image114);
        } else if(arrayCards[card] == 115){
            imageView.setImageResource(image115);
        } else if(arrayCards[card] == 116){
            imageView.setImageResource(image116);
        } else if(arrayCards[card] == 117){
            imageView.setImageResource(image117);
        } else if(arrayCards[card] == 118){
            imageView.setImageResource(image118);
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
        } else if(arrayCards[card] == 209){
            imageView.setImageResource(image209);
        } else if(arrayCards[card] == 210){
            imageView.setImageResource(image210);
        } else if(arrayCards[card] == 211){
            imageView.setImageResource(image211);
        } else if(arrayCards[card] == 212){
            imageView.setImageResource(image212);
        } else if(arrayCards[card] == 213){
            imageView.setImageResource(image213);
        } else if(arrayCards[card] == 214){
            imageView.setImageResource(image214);
        } else if(arrayCards[card] == 215){
            imageView.setImageResource(image215);
        } else if(arrayCards[card] == 216){
            imageView.setImageResource(image216);
        } else if(arrayCards[card] == 217){
            imageView.setImageResource(image217);
        } else if(arrayCards[card] == 218){
            imageView.setImageResource(image218);
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
            imageView15.setEnabled(false);
            imageView16.setEnabled(false);
            imageView21.setEnabled(false);
            imageView22.setEnabled(false);
            imageView23.setEnabled(false);
            imageView24.setEnabled(false);
            imageView25.setEnabled(false);
            imageView26.setEnabled(false);
            imageView31.setEnabled(false);
            imageView32.setEnabled(false);
            imageView33.setEnabled(false);
            imageView34.setEnabled(false);
            imageView35.setEnabled(false);
            imageView36.setEnabled(false);
            imageView41.setEnabled(false);
            imageView42.setEnabled(false);
            imageView43.setEnabled(false);
            imageView44.setEnabled(false);
            imageView45.setEnabled(false);
            imageView46.setEnabled(false);
            imageView51.setEnabled(false);
            imageView52.setEnabled(false);
            imageView53.setEnabled(false);
            imageView54.setEnabled(false);
            imageView55.setEnabled(false);
            imageView56.setEnabled(false);
            imageView61.setEnabled(false);
            imageView62.setEnabled(false);
            imageView63.setEnabled(false);
            imageView64.setEnabled(false);
            imageView65.setEnabled(false);
            imageView66.setEnabled(false);

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
                imageView15.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 5){
                imageView16.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 6){
                imageView21.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 7){
                imageView22.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 8){
                imageView23.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 9){
                imageView24.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 10){
                imageView25.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 11){
                imageView26.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 12){
                imageView31.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 13){
                imageView32.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 14){
                imageView33.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 15){
                imageView34.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 16){
                imageView35.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 17){
                imageView36.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 18){
                imageView41.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 19){
                imageView42.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 20){
                imageView43.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 21){
                imageView44.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 22){
                imageView45.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 23){
                imageView46.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 24){
                imageView51.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 25){
                imageView52.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 26){
                imageView53.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 27){
                imageView54.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 28){
                imageView55.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 29){
                imageView56.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 30){
                imageView61.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 31){
                imageView62.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 32){
                imageView63.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 33){
                imageView64.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 34){
                imageView65.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 35){
                imageView66.setVisibility(View.INVISIBLE);
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
                imageView15.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 5){
                imageView16.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 6){
                imageView21.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 7){
                imageView22.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 8){
                imageView23.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 9){
                imageView24.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 10){
                imageView25.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 11){
                imageView26.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 12){
                imageView31.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 13){
                imageView32.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 14){
                imageView33.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 15){
                imageView34.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 16){
                imageView35.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 17){
                imageView36.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 18){
                imageView41.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 19){
                imageView42.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 20){
                imageView43.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 21){
                imageView44.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 22){
                imageView45.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 23){
                imageView46.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 24){
                imageView51.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 25){
                imageView52.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 26){
                imageView53.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 27){
                imageView54.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 28){
                imageView55.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 29){
                imageView56.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 30){
                imageView61.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 31){
                imageView62.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 32){
                imageView63.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 33){
                imageView64.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 34){
                imageView65.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 35){
                imageView66.setVisibility(View.INVISIBLE);
            }
        }
        else{
            //Si las imagenes no son iguales les doy la "vuelta" a las cartas.
            asignoImagenEspadas();
        }

        //Activo las imagenes
        activarImages();

        //Compruebo si el juego ha acabado
        checkEnd();
    }

    private void activarImages() {
        imageView11.setEnabled(true);
        imageView12.setEnabled(true);
        imageView13.setEnabled(true);
        imageView14.setEnabled(true);
        imageView15.setEnabled(true);
        imageView16.setEnabled(true);
        imageView21.setEnabled(true);
        imageView22.setEnabled(true);
        imageView23.setEnabled(true);
        imageView24.setEnabled(true);
        imageView25.setEnabled(true);
        imageView26.setEnabled(true);
        imageView31.setEnabled(true);
        imageView32.setEnabled(true);
        imageView33.setEnabled(true);
        imageView34.setEnabled(true);
        imageView35.setEnabled(true);
        imageView36.setEnabled(true);
        imageView41.setEnabled(true);
        imageView42.setEnabled(true);
        imageView43.setEnabled(true);
        imageView44.setEnabled(true);
        imageView45.setEnabled(true);
        imageView46.setEnabled(true);
        imageView51.setEnabled(true);
        imageView52.setEnabled(true);
        imageView53.setEnabled(true);
        imageView54.setEnabled(true);
        imageView55.setEnabled(true);
        imageView56.setEnabled(true);
        imageView61.setEnabled(true);
        imageView62.setEnabled(true);
        imageView63.setEnabled(true);
        imageView64.setEnabled(true);
        imageView65.setEnabled(true);
        imageView66.setEnabled(true);
    }

    private void checkEnd(){
        //si no aparece ninguna card en la pantalla:
        if (imageView11.getVisibility() == View.INVISIBLE &&
                imageView12.getVisibility() == View.INVISIBLE &&
                imageView13.getVisibility() == View.INVISIBLE &&
                imageView14.getVisibility() == View.INVISIBLE &&
                imageView15.getVisibility() == View.INVISIBLE &&
                imageView16.getVisibility() == View.INVISIBLE &&
                imageView21.getVisibility() == View.INVISIBLE &&
                imageView22.getVisibility() == View.INVISIBLE &&
                imageView23.getVisibility() == View.INVISIBLE &&
                imageView24.getVisibility() == View.INVISIBLE &&
                imageView25.getVisibility() == View.INVISIBLE &&
                imageView26.getVisibility() == View.INVISIBLE &&
                imageView31.getVisibility() == View.INVISIBLE &&
                imageView32.getVisibility() == View.INVISIBLE &&
                imageView33.getVisibility() == View.INVISIBLE &&
                imageView34.getVisibility() == View.INVISIBLE &&
                imageView35.getVisibility() == View.INVISIBLE &&
                imageView36.getVisibility() == View.INVISIBLE &&
                imageView41.getVisibility() == View.INVISIBLE &&
                imageView42.getVisibility() == View.INVISIBLE &&
                imageView43.getVisibility() == View.INVISIBLE &&
                imageView44.getVisibility() == View.INVISIBLE &&
                imageView45.getVisibility() == View.INVISIBLE &&
                imageView46.getVisibility() == View.INVISIBLE &&
                imageView51.getVisibility() == View.INVISIBLE &&
                imageView52.getVisibility() == View.INVISIBLE &&
                imageView53.getVisibility() == View.INVISIBLE &&
                imageView54.getVisibility() == View.INVISIBLE &&
                imageView55.getVisibility() == View.INVISIBLE &&
                imageView56.getVisibility() == View.INVISIBLE &&
                imageView61.getVisibility() == View.INVISIBLE &&
                imageView62.getVisibility() == View.INVISIBLE &&
                imageView63.getVisibility() == View.INVISIBLE &&
                imageView64.getVisibility() == View.INVISIBLE &&
                imageView65.getVisibility() == View.INVISIBLE &&
                imageView66.getVisibility() == View.INVISIBLE){
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
        image109 = R.drawable.icon109;
        image110 = R.drawable.icon110;
        image111 = R.drawable.icon111;
        image112 = R.drawable.icon112;
        image113 = R.drawable.icon113;
        image114 = R.drawable.icon114;
        image115 = R.drawable.icon115;
        image116 = R.drawable.icon116;
        image117 = R.drawable.icon117;
        image118 = R.drawable.icon118;
        image201 = R.drawable.icon201;
        image202 = R.drawable.icon202;
        image203 = R.drawable.icon203;
        image204 = R.drawable.icon204;
        image205 = R.drawable.icon205;
        image206 = R.drawable.icon206;
        image207 = R.drawable.icon207;
        image208 = R.drawable.icon208;
        image209 = R.drawable.icon209;
        image210 = R.drawable.icon210;
        image211 = R.drawable.icon211;
        image212 = R.drawable.icon212;
        image213 = R.drawable.icon213;
        image214 = R.drawable.icon214;
        image215 = R.drawable.icon215;
        image216 = R.drawable.icon216;
        image217 = R.drawable.icon217;
        image218 = R.drawable.icon218;
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