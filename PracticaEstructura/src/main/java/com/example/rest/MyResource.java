package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.InversionistaDao;
import com.example.controls.dao.services.InversionistaServices;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap mapa = new HashMap<>();
        InversionistaServices pd = new InversionistaServices();
        String aux = "";
        try {
            pd.getInversionista().setNombre("Pancho");
            pd.getInversionista().setApellido("Iglesias");
            pd.getInversionista().setDni("1234567890");
            pd.getInversionista().settelefono("0912345678");
            pd.getInversionista().settipoDni("Cedula");
            pd.save();
          
            aux = "La lista se encuentra vacia"+pd.listAll().isEmpty();
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
        mapa.put("msg","Ok");
        mapa.put("data", "test"+aux);
        return Response.ok(mapa).build();
    }
}
