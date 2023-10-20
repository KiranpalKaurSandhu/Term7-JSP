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
import model.Customer;

import java.util.List;

@Path("/customer")
public class CustomerResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallselectcustomers")
    public String getAllSelectCustomers() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select c from Customer c");
        List<Customer> list = query.getResultList();
        JsonArray jsonArray = new JsonArray();
        for (Customer c : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("customerId", c.getCustomerId());
            jsonObject.addProperty("custFirstName", c.getCustFirstName());
            jsonObject.addProperty("custLastName", c.getCustLastName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
