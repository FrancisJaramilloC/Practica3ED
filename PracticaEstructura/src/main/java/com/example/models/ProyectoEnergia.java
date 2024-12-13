package com.example.models;

import java.sql.Date;

import com.google.gson.Gson;

// ProyectoEnergia.java
public class ProyectoEnergia {
    private Integer id;
    private String nombre;
    private double energiaTotal;
    private Integer tiempoVida;
    private String fechaInicio;
    private String fechaFin;
    private double montoTotal;

    public ProyectoEnergia() {

    }

    public ProyectoEnergia(Integer id, String nombre, double energiaTotal, String fechaInicio,String fechaFin, Integer tiempoVida, double montoTotal) {
        this.id = id;
        this.nombre = nombre;
        this.energiaTotal = energiaTotal;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tiempoVida = tiempoVida;
        this.montoTotal = montoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getenergiaTotal() {
        return energiaTotal;
    }

    public void setenergiaTotal(double energiaTotal) {
        this.energiaTotal = energiaTotal;
    }

    public Integer getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(Integer tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getmontoTotal() {
        return montoTotal;
    }

    public void setmontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

}
