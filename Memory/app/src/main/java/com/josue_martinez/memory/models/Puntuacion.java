package com.josue_martinez.memory.models;

import com.josue_martinez.memory.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Puntuacion  extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private Integer puntos;
    @Required
    private String nombre;

    //constructor vacio
    public Puntuacion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Puntuacion(Integer puntos, String nombre) {
        this.id = MyApplication.puntuacionID.incrementAndGet();
        this.puntos = puntos;
        this.nombre = nombre;
    }

    public Puntuacion(Integer puntos) {
        this.puntos = puntos;
        this.nombre = "Anónimo";
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
