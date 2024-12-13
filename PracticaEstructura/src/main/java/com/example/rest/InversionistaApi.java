package com.example.rest;

import java.util.HashMap;
import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controls.dao.services.InversionistaServices;
import com.example.models.Inversionista;

@Path("person")
public class InversionistaApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallPersons() {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices ps = new InversionistaServices();
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
    public Response save(HashMap<String, Object> map) {
        InversionistaServices ps = new InversionistaServices();
        ps.getInversionista().setNombre(map.get("nombre").toString());
        ps.getInversionista().setApellido(map.get("apellido").toString());
        ps.getInversionista().setDni(map.get("dni").toString());
        ps.getInversionista().settelefono(map.get("telefono").toString());
        ps.getInversionista().settipoDni(map.get("tipoDni").toString());
        HashMap<String, Object> res = new HashMap<>();

        try {
            ps.save();
            res.put("msg", "ok");
            res.put("data", "El Inversionista ha sido registrado");
            return Response.ok(res).build();
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }
    }

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices ps = new InversionistaServices();
        map.put("msg", "OK");
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices ps = new InversionistaServices();
        try {
            ps.setInversionista(ps.get(id));
        } catch (Exception e) {
            // Handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getInversionista());

        if (ps.getInversionista().getId() == null) {
            map.put("data", "Inversionista no encontrado");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            InversionistaServices ps = new InversionistaServices();
            ps.setInversionista(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getInversionista().setNombre(map.get("nombre").toString());
            ps.getInversionista().setApellido(map.get("apellido").toString());
            ps.getInversionista().setDni(map.get("dni").toString());
            ps.getInversionista().settelefono(map.get("telefono").toString());
            ps.getInversionista().settipoDni(map.get("tipoDni").toString());
            ps.update();
            res.put("msg", "ok");
            res.put("data", "El Inversionista ha sido actualizado");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }
    }

    @Path("/delete/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices is = new InversionistaServices();
        try {
            is.deleteInversionista(id);
            map.put("status", "OK");
            map.put("data", "Inversionista eliminado correctamente");
            return Response.ok(map).build();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

/** 
    @Path("/ordenarInversionistas/{metodo}/{tipo}/{atributo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenarInversionistas(@PathParam("metodo") Integer metodo, @PathParam("tipo") Integer tipo, @PathParam("atributo") String atributo) {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices ps = new InversionistaServices();

        try {

            map.put("msg", "Lista de proyectos ordenados");
            map.put("data", ps.ordenarInversionista(metodo, tipo, atributo));
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    } */
} 