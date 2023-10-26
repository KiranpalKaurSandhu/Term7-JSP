package com.example.term7restservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Agent;

import java.util.List;

@Path("/agent")
public class AgentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallagents")
    public String getAllAgents() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select a from Agent a");
        List<Agent> list = query.getResultList();

        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson gson = gb.create();
        //gson was updated to be able to convert lists to jsonArrays without needing to use type and typetoken
        entityManager.close();
        return gson.toJson(list);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(String jsonString) {
        // Parse the JSON input (jsonString) to extract the username and password.
        Gson gson = new Gson();
        Agent agent = gson.fromJson(jsonString, Agent.class);

        // Verify the username and password against your database or a source of user credentials.
        if (authenticate(agent.getUsername(), agent.getPassword())) {
            // If authentication is successful, return a success response.
            return Response.ok("{ \"success\": true, \"message\": \"Login successful\" }").build().toString();
        } else {
            // Return an error response for failed login attempts.
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{ \"success\": false, \"message\": \"Login failed\" }")
                    .build().toString();
        }
    }

    // Implement your authentication logic, e.g., querying the database for user credentials.
    private boolean authenticate(String username, String password) {
        // Replace this with your authentication logic, e.g., checking the database for the user.
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Agent agent = entityManager.find(Agent.class, username);

        if (agent != null && agent.getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
