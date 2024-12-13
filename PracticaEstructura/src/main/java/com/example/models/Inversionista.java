package com.example.models;
import java.io.Serializable;

public class Inversionista {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String tipoDni;

    public Inversionista() {
    }
    
    public Inversionista(String apellido, String dni, Integer id, String nombre, String telefono, String sexo, String tipoDni) {
        this.apellido = apellido;
        this.dni = dni;
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoDni = tipoDni;
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

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String gettelefono() {
        return telefono;
    }
    public void settelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String gettipoDni() {
        return tipoDni;
    }

    public void settipoDni(String tipoDni) {
        this.tipoDni = tipoDni;
    }
    
    
    

   
    
}
