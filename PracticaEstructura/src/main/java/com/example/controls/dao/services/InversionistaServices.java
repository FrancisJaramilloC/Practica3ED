package com.example.controls.dao.services;

import com.example.controls.dao.InversionistaDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Inversionista;


public class InversionistaServices {
    private InversionistaDao obj;

    public InversionistaServices() {
        obj = new InversionistaDao();
    }
    
     public Inversionista getInversionista() {
        return obj.getInversionista();
    }
   
    public void setInversionista(Inversionista Inversionista) {
        obj.setInversionista(Inversionista);
    }
    
    public Boolean save() throws Exception {
        return obj.save();
    }
    
    public Boolean update() throws Exception {
        return obj.update();
    }

    public void deleteInversionista(Integer id) throws Exception {
        obj.deleteInversionista(id);
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Inversionista get(Integer id) throws Exception {
        return obj.get(id);
    }
/** 
    public Inversionista[] ordenarInversionista(Integer metodo, Integer tipo, String atributo) throws Exception {
        return this.obj.ordenarInversionista(metodo, tipo, atributo);
    } */
/** 
    public Inversionista[] buscarInversionista(String atributo, String valor) throws Exception {
        return this.obj.buscar(atributo, valor);
    }*/
}
