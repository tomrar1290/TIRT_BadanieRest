/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok;

import com.tomaszrarok.model.DaneBadanie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tomasz.rarok
 */
@Path("/process")
public class ProcessResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        saveInDatabase();
        return "Got it!";
    }

    private void saveInDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BadanieRestApi_pu");

        DaneBadanie values = new DaneBadanie();
        values.setAlgorithm("Example Algorithm");
        values.setSize_query(100);
        values.setTime_execution(1000);

        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();            
            em.persist(values);
            tx.commit();
            
            System.out.println("Person id: " + values.getId());
            
            Query query = em.createQuery("SELECT e FROM DaneBadanie e");
            
            System.err.println(query.getResultList().size());
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
