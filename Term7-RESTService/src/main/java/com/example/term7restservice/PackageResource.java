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
import model.Package;

import java.util.List;

/*
    Author : Kiranpal Kaur
     Description: This class is a RESTful resource for managing packages. It provides endpoints for retrieving
     package information.
 */
@Path("/package")
public class PackageResource {

    /**
     * Retrieves selected package information from the database and returns it as JSON.
     * @return A JSON representation of selected package information.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallselectpackages")
    public String getAllSelectPackages() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select p from Package p");
        List<Package> list = query.getResultList();
        JsonArray jsonArray = new JsonArray();
        for (Package p : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("packageId", p.getPackageId());
            jsonObject.addProperty("pkgName", p.getPkgName());

            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    /*
    Author : Jade Bayot
    */
    /**
     * Retrieves selected package information from the database and returns it as JSON.
     * @return A JSON representation of selected package information.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallpackages")
    public String getAllPackages()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select p from Package p");
        List<Package> list = query.getResultList();

        Gson gson = new Gson();

        return gson.toJson(list);
    }}
