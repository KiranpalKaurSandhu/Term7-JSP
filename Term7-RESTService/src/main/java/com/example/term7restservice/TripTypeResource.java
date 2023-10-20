package com.example.term7restservice;

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
import model.Triptype;

import java.util.List;

@Path("/triptype")
public class TripTypeResource {
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
}
