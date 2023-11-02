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

/*
    Author : Kiranpal Kaur
    Description: This class is a RESTful resource for managing agents. It provides endpoints for retrieving agent
    information and handling agent login.
 */
@Path("/agent")
public class AgentResource {

    /**
     * Retrieves all agents from the database and returns them as JSON.
     * @return A JSON representation of all agents.
     */
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

    /**
     * Authenticates an agent by checking the provided username and password.
     * @param jsonString JSON data containing agent login credentials.
     * @return A response indicating the success or failure of the login attempt.
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        Agent agent = gson.fromJson(jsonString, Agent.class);

        if (authenticate(agent.getUsername(), agent.getPassword())) {
            return Response.status(Response.Status.OK)
                    .entity("{ \"success\": true, \"message\": \"Login successful\" }")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{ \"success\": false, \"message\": \"Login failed\" }")
                    .build();
        }
    }

    /**
     * Authenticates an agent by verifying the provided username and password.
     * @param username The agent's username.
     * @param password The agent's password.
     * @return True if the authentication is successful, false otherwise.
     */
    private boolean authenticate(String username, String password) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

       // Agent agent = entityManager.find(Agent.class, username);
        //System.out.println(agent.getAgentId() + agent.getUsername() + agent.getPassword());

        // Use a query to find an Agent by username
        Query query = entityManager.createQuery("SELECT a FROM Agent a WHERE a.username = :username", Agent.class);
        query.setParameter("username", username);

        // Get the result
        List<Agent> agents = query.getResultList();

        if (agents.size() == 1) {
            Agent agent = agents.get(0);
            return agent.getPassword().equals(password);
        }

        return false;
    }
}

