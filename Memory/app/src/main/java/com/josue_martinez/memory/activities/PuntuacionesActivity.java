package com.josue_martinez.memory.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.josue_martinez.memory.R;
import com.josue_martinez.memory.adapters.PuntuacionAdapter;
import com.josue_martinez.memory.models.Puntuacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//realm
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;



public class PuntuacionesActivity extends AppCompatActivity {
    Realm realm;
    RealmResults<Puntuacion> listaPuntuaciones;
    RecyclerView recyclerViewPuntuaciones;
    ArrayList<Puntuacion> listaPuntuacionesApi;
    TextView textViewPruebaApi;
    RequestQueue requestQueue;
    private static final String URL1 = "https://project-memorygame.herokuapp.com/puntuaciones";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        realm = Realm.getDefaultInstance();
        listaPuntuaciones = realm.where(Puntuacion.class).sort("puntos", Sort.DESCENDING).findAll();
        listaPuntuacionesApi = new ArrayList();

        recyclerViewPuntuaciones = (RecyclerView) findViewById(R.id.recyclerViewPuntuaciones);

        //API
        requestQueue = Volley.newRequestQueue(this);
        //stringRequest();
        jsonArrayRequest();


//esto era para el realm
/*        listaPuntuaciones.addChangeListener(new RealmChangeListener<RealmResults<Puntuacion>>() {
            @Override
            public void onChange(RealmResults<Puntuacion> puntuaciones) {

                puntuacionAdapter.notifyDataSetChanged();

            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private PuntuacionAdapter generarRecycler(ArrayList<Puntuacion> lista) {
        //lo primero ordeno la lista por puntos de manera descendente
        Comparator<Puntuacion> puntuacionPuntosComparator = Comparator.comparing(Puntuacion::getPuntos);
        Collections.sort(lista, puntuacionPuntosComparator.reversed());

        final PuntuacionAdapter puntuacionAdapter = new PuntuacionAdapter(lista);
        recyclerViewPuntuaciones.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerViewPuntuaciones.setAdapter(puntuacionAdapter);
        //linea decorativa
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewPuntuaciones.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewPuntuaciones.addItemDecoration(mDividerItemDecoration);
        return puntuacionAdapter;
    }

    private void stringRequest(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textViewPruebaApi.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);
    }
    private void jsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i = 0; i < size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                String nombre = jsonObject.getString("nombre");
                                Integer puntos = jsonObject.getInt("puntos");
                                //Creo la "puntuacion" temporal
                                Puntuacion puntuacionTmp = new Puntuacion(puntos, nombre);
                                //y la añado a la lista
                                listaPuntuacionesApi.add(puntuacionTmp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //Por ultimo la añado al recycler
                        final PuntuacionAdapter puntuacionAdapter = generarRecycler(listaPuntuacionesApi);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}