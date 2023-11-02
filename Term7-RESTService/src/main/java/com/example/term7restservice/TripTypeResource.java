package com.example.term7restservice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Customer;
import model.Triptype;

import java.util.List;

/*
    Author : Kiranpal Kaur
    Decription : This class is a RESTful resource for managing trip types. It provides endpoints for retrieving
    trip type information.
 */
@Path("/triptype")
public class TripTypeResource {

    /**
     * Retrieves all trip type information from the database and returns it as JSON.
     * @return A JSON representation of all trip type information.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallselecttriptype")
    public String getAllSelectTripType() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select t from Triptype t");
        List<Triptype> list = query.getResultList();
        JsonArray jsonArray = new JsonArray();
        for (Triptype t : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("tripTypeName", t.getTTName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    /*
    Author : Jade Bayot
    */
    /**
     * Retrieves all trip type information from the database and returns it as JSON.
     * @return A JSON representation of all trip type information.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getalltriptypes")
    public String getAllTripType()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select t from Triptype t");
        List<Triptype> list = query.getResultList();

        Gson gson = new Gson();

        return gson.toJson(list);
    }
}
