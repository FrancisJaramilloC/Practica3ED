package com.example.controls.dao;

import java.util.function.ToIntBiFunction;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Inversionista;




public class InversionistaDao extends AdapterDao<Inversionista> {
    private Inversionista Inversionista;
    private LinkedList listAll;
    
    public InversionistaDao() {
        super(Inversionista.class);
    }

    public Inversionista getInversionista() {
        if (Inversionista == null) {
            Inversionista = new Inversionista();
        }
        return this.Inversionista;
    }

    public void setInversionista(Inversionista Inversionista) {
        this.Inversionista = Inversionista;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        Inversionista.setId(id);
        this.persist(this.Inversionista);
        this.listAll = listAll();
        return true;
    }
    
    public Boolean update() throws Exception {
        this.merge(getInversionista(), getInversionista().getId() -1);
        this.listAll = listAll();
        return true;
    }

    public void deleteInversionista(Integer id) throws Exception {
        this.delete(id);
    }

/** 
    public Inversionista[] ordenarInversionista (Comparator<E> comparator, Integer metodo, Integer tipo, String atributo) throws Exception {
    LinkedList<Inversionista> list = listAll();
        switch (metodo) {
            case 0:
                list.quickSort(metodo, tipo, atributo);
                break;
            case 1:
                list.mergeSort(metodo, tipo, atributo);
                break;
            case 2:
                list.shellSort(metodo, tipo, atributo);
                break;       
            default:
                System.out.println("Metodo de ordenamiento no valido");
                break;
        }
        return list.toArray();
    }
    */
/** 
    public Inversionista[] buscarInversionista(String atributo, String valor) throws Exception {
    LinkedList<Inversionista> list = listAll();
    */

}
