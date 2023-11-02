package com.example.term7restservice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Customer;
import model.Package;
import model.Triptype;

import java.util.List;

/*
    Author : Kiranpal Kaur
    Description : This class is a RESTful resource for managing customers. It provides endpoints for retrieving
    customer information.
 */
@Path("/customer")
public class CustomerResource {

    /**
     * Retrieves selected customer information from the database and returns it as JSON.
     * @return A JSON representation of selected customer information.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallselectcustomers")
    public String getAllSelectCustomers()
    {
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

    /*
    Author : Jade Bayot
    */
    /**
     * Retrieves selected customer information from the database and returns it as JSON.
     * @return A JSON representation of selected customer information.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallcustomers")
    public String getAllCustomers()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select c from Customer c");
        List<Customer> list = query.getResultList();

        Gson gson = new Gson();

        return gson.toJson(list);
    }

    /*
        Author: Jade Bayot
         */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcustomerbyagent/{ agentId }")
    public String getCustomer(@PathParam("agentId") int agentId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        String sql = "SELECT c from Customer c where c.agentId = :agentId";
        TypedQuery<Customer> query = entityManager.createQuery(sql,Customer.class);
        query.setParameter("agentId", agentId);

        List<Customer> customers = query.getResultList();

        JsonArray jsonArray = new JsonArray();
        for(Customer c : customers)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("customerId", c.getCustomerId());
            jsonObject.addProperty("custFirstName", c.getCustFirstName());
            jsonObject.addProperty("custLastName", c.getCustLastName());
            jsonObject.addProperty("agentId", c.getAgentId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }
}
