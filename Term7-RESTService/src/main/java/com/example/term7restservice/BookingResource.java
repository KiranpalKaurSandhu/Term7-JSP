package com.example.term7restservice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Booking;
import model.Customer;

import java.util.List;

/*
    Author : Kiranpal Kaur
    Description : This class is a RESTful resource for managing bookings. It provides various endpoints for retrieving,
    creating, updating, and deleting bookings. The endpoints use the JPA EntityManager for database interactions
    and Gson for JSON serialization.
 */
@Path("/booking")
public class BookingResource {

    /**
     * Retrieves all bookings from the database and returns them as JSON.
     * @return A JSON representation of all bookings.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getallbookings")
    public String getAllBookings() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select b from Booking  b ");
        List<Booking> list = query.getResultList();
        Gson gson = new Gson();

        return gson.toJson(list);
    }

    /**
     * Retrieves a specific booking by its ID and returns it as JSON.
     * @param bookingId The ID of the booking to retrieve.
     * @return A JSON representation of the specified booking.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getbooking/{ bookingid }")
    public String getBooking(@PathParam("bookingid") int bookingId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Booking booking = entityManager.find(Booking.class, bookingId);
        Gson gson = new Gson();

        return gson.toJson(booking);

    }
    /**
     * Creates or updates a booking based on the provided JSON data.
     * @param jsonString JSON data representing a booking.
     * @return A message indicating the success or failure of the operation.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("postbooking")
    public String postBooking(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Gson gson = new Gson();
        Booking booking = gson.fromJson(jsonString, Booking.class);
        entityManager.getTransaction().begin();
        Booking updatedBooking = entityManager.merge(booking);
        String message = null;

        if (updatedBooking != null)
        {
            entityManager.getTransaction().commit();
            message = "{\"message\": \"Booking updated successfully\" }";
        }
        else {
            entityManager.getTransaction().rollback();
            message = "{\"message\": \"Booking update failed\" }";
        }
        entityManager.close();
        return message;

    }

    /**
     * Inserts a new booking based on the provided JSON data.
     * @param jsonString JSON data representing a new booking.
     * @return A message indicating the success or failure of the operation.
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("putbooking")
    public String putBooking(String jsonString) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select b from Booking b");
        List<Booking> list = query.getResultList();
        int listSize = list.size();
        Gson gson = new Gson();
        Booking booking  = gson.fromJson(jsonString, Booking.class);
        entityManager.getTransaction().begin();
        entityManager.persist(booking);
        String message = null;

        //lookup query size after persist to see if size increased by 1
        query = entityManager.createQuery("select b from Booking b");
        List<Booking> newList = query.getResultList();
        int newListSize = newList.size();

        if (newListSize > listSize)
        {
            entityManager.getTransaction().commit();
            message = "{ \"message\": \"Booking inserted successfully\" }";
        }
        else
        {
            entityManager.getTransaction().rollback();
            message = "{ \"message\": \"Booking insert failed\" }";
        }

        entityManager.close();
        return message;
    }

    /**
     * Deletes a booking with the specified ID.
     * @param bookingId The ID of the booking to delete.
     * @return A message indicating the success or failure of the deletion.
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletebooking/{ bookingid }")
    public String deleteBooking(@PathParam("bookingid") int bookingId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        Booking booking = entityManager.find(Booking.class, bookingId);

        if (booking == null) {
            return "{\"message\": \"Booking not found\" }";
        }

        entityManager.getTransaction().begin();
        try {
            entityManager.remove(booking);
            entityManager.getTransaction().commit();
            return "{\"message\": \"Booking deleted successfully\" }";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "{\"message\": \"Booking delete failed: " + e.getMessage() + "\" }";
        }
    }

        /*
        Author: Jade Bayot
         */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getbookingbyagent/{ agentId }")
    public String getCustomer(@PathParam("agentId") int agentId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        String sql ="SELECT b from Booking b JOIN Customer c ON b.customerId = c.CustomerId where c.agentId= :agentId ORDER BY b.bookingId";
        TypedQuery<Booking> query = entityManager.createQuery(sql,Booking.class);
        query.setParameter("agentId", agentId);
        JsonArray jsonArray = new JsonArray();
        List<Booking> bookings = query.getResultList();

        for(Booking b : bookings)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookingId", b.getBookingId());
            jsonObject.addProperty("bookingNo", b.getBookingNo());
            jsonObject.addProperty("bookingDate", b.getBookingDate().toString());
            jsonObject.addProperty("travelerCount", b.getBookingId());
            jsonObject.addProperty("customerId", b.getCustomerId());
            jsonObject.addProperty("tripTypeId", b.getTripTypeId());
            jsonObject.addProperty("packageId", b.getPackageId());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }


}