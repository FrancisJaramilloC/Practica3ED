package com.example.rest;

import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controls.dao.ProyectoEnergiaDao;
import com.example.controls.dao.services.InversionistaServices;
import com.example.controls.dao.services.ProyectoEnergiaServices;
import com.example.controls.exception.ListEmptyException;
import com.example.controls.tda.list.LinkedList;
import com.google.gson.Gson;
import com.example.models.*;

@Path("energia")
public class ProyectoEnergiaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyectoEnergia() {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        map.put("msg", "OK");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});

        }
        return Response.ok(map).build();
    } 
   
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        ProyectoEnergiaDao ps = new ProyectoEnergiaDao();
        try {
            ps.getProyectoEnergia().setNombre(map.get("nombre").toString());
            ps.getProyectoEnergia().setenergiaTotal(Double.parseDouble(map.get("energiaTotal").toString()));
            ps.getProyectoEnergia().setTiempoVida(Integer.parseInt(map.get("tiempoVida").toString()));
            ps.getProyectoEnergia().setFechaInicio(map.get("fechaInicio").toString());
            ps.getProyectoEnergia().setFechaFin(map.get("fechaFin").toString());
            ps.getProyectoEnergia().setmontoTotal(Double.parseDouble(map.get("montoTotal").toString()));
            ps.save();
            res.put("msg", "OK");
            res.put("data", "El Proyecto ha sido Registrado");
            return Response.ok(res).build();

            
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

    }
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoEnergia(@PathParam ("id")Integer  id) {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
       
        try {
            ps.setProyectoEnergia(ps.get(id));
        } catch (Exception e) {
            
        }
        map.put("msg", "Ok");
        map.put("data", ps.getProyectoEnergia());
        
        if (ps.getProyectoEnergia().getId() == null) {
            
            map.put("data", "Proyecto no encontrado");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();
        try {
            ProyectoEnergiaDao ps = new ProyectoEnergiaDao();
            ps.setProyectoEnergia(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getProyectoEnergia().setNombre(map.get("nombre").toString());
            ps.getProyectoEnergia().setenergiaTotal(Double.parseDouble(map.get("energiaTotal").toString()));
            ps.getProyectoEnergia().setTiempoVida(Integer.parseInt(map.get("tiempoVida").toString()));
            ps.getProyectoEnergia().setFechaInicio(map.get("fechaInicio").toString());
            ps.getProyectoEnergia().setFechaFin(map.get("fechaFin").toString());
            ps.getProyectoEnergia().setmontoTotal(Double.parseDouble(map.get("montoTotal").toString()));
            ps.update();
            res.put("msg", "OK");
            res.put("data", "El proyecto ha sido modificado");
            return Response.ok(res).build();
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();

        }

    }

    @Path("/sort")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortProyectos(HashMap<String, Object> sortParams) {
        HashMap<String, Object> res = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        LinkedList<ProyectoEnergia> proyectos = ps.listAll();
        
        String sortBy = sortParams.get("Clasificar por").toString().toLowerCase();
        String sortOrder = sortParams.get("Clasificar por orden").toString().toLowerCase();
        String sortMethod = sortParams.get("Clasificar por metodo").toString().toLowerCase();

        Comparator<ProyectoEnergia> comparator = null;
        switch(sortBy) {
            case "id":
                comparator = Comparator.comparing(ProyectoEnergia::getId);
                break;
            case "nombre":
                comparator = Comparator.comparing(ProyectoEnergia::getNombre);
                break;
            case "energiaTotal":
                comparator = Comparator.comparing(ProyectoEnergia::getenergiaTotal);
                break;
            case "tiempoVida":
                comparator = Comparator.comparing(ProyectoEnergia::getTiempoVida);
                break;
            default:
                res.put("msg", "Error");
                res.put("data", "Invalid sort criteria");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Orden Menor a 
        if (sortOrder.equals("MENOR")) {
            comparator = comparator.reversed();
        }

        // Metodos de ordenamiento
        switch(sortMethod) {
            case "quicksort":
                proyectos.quickSort(comparator);
                break;
            case "mergesort":
                proyectos.mergeSort(comparator);
                break;
            case "shellsort":
                proyectos.shellSort(comparator);
                break;
            default:
                res.put("msg", "Error");
                res.put("data", "Metodo Invalido");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        res.put("msg", "OK");
        res.put("data", proyectos.toArray());
        return Response.ok(res).build();
    }

    //Buscar
    
    @Path("/search")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProyectos(HashMap<String, Object> searchParams) throws IndexOutOfBoundsException, ListEmptyException {
        HashMap<String, Object> res = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        LinkedList<ProyectoEnergia> proyectos = ps.listAll();

        String searchBy = searchParams.get("Buscar por").toString().toLowerCase();
        String searchMethod = searchParams.get("Buscar metodo").toString().toLowerCase();
        String searchValue = searchParams.get("Buscar valor").toString().toLowerCase();

        LinkedList<ProyectoEnergia> results = new LinkedList<>();
        //Busqueda lineal
        if (searchMethod.equals("Busqueda lineal")) {
            for (ProyectoEnergia proyecto : proyectos.toArray()) {
                String valorComparar = "";
                switch (searchBy) {
                    case "nombre":
                        valorComparar = proyecto.getNombre().toLowerCase();
                        break;
                    case "id":
                        valorComparar = proyecto.getId().toString();
                        break;
                    case "tiempoVida":
                        valorComparar = proyecto.getTiempoVida().toString();
                        break;
                    default:
                        continue;
                }

                if (valorComparar.startsWith(searchValue)) {
                    results.add(proyecto);
                }
            } //Busqueda binaria
        } else if (searchMethod.equals("Busqueda binaria")) {
            Comparator<ProyectoEnergia> comparator = null;
            switch (searchBy) {
                case "nombre":
                    comparator = Comparator.comparing(p -> p.getNombre().toLowerCase());
                    break;
                case "id":
                    comparator = Comparator.comparing(ProyectoEnergia::getId);
                    break;
                case "tiempoVida":
                    comparator = Comparator.comparing(ProyectoEnergia::getTiempoVida);
                    break;
                default:
                    res.put("msg", "Error");
                    res.put("data", "Metodo de busqueda inexistente");
                    return Response.status(Status.BAD_REQUEST).entity(res).build();
            }
            proyectos.quickSort(comparator);

            // Primer Match (Busqueda Binaria)
            int index = encontrarPrimerMatch(proyectos.toArray(), searchValue, searchBy);
            if (index != -1) {
                while (index < proyectos.getSize()) {
                    ProyectoEnergia proyecto = proyectos.get(index);
                    String valorComparar = "";
                    switch (searchBy) {
                        case "nombre":
                            valorComparar = proyecto.getNombre().toLowerCase();
                            break;
                        case "id":
                            valorComparar = proyecto.getId().toString();
                            break;
                        
                        case "tiempoVida":
                            valorComparar = proyecto.getTiempoVida().toString();
                            break;
                    }

                    if (valorComparar.startsWith(searchValue)) {
                        results.add(proyecto);
                    } else {
                        break;
                    }
                    index++;
                }
            }
        }

        res.put("msg", "OK");
        res.put("data", results.toArray());
        return Response.ok(res).build();
    }

    // Método auxiliar para búsqueda binaria (Coincidencia)
    private int encontrarPrimerMatch(ProyectoEnergia[] arr, String prefix, String searchBy) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midValue = "";

            switch (searchBy) {
                case "nombre":
                    midValue = arr[mid].getNombre().toLowerCase();
                    break;
                case "id":
                    midValue = arr[mid].getId().toString();
                    break;
                case "tiempoVida":
                    midValue = arr[mid].getTiempoVida().toString();
                    break;
            }

            if (midValue.startsWith(prefix)) {
                result = mid;
                right = mid - 1; 
            } else if (midValue.compareTo(prefix) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}