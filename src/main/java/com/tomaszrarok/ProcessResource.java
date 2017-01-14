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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
 *
 * @author tomasz.rarok
 */
@Path("/process")
public class ProcessResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "it works";
    }
    
    @GET
    @Path("bubble/{size}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getBubble(@PathParam("size") Integer size) {
        long start = Calendar.getInstance().get(Calendar.MILLISECOND);
        
        //Action!!
        int [] array = generateRandomNumbers(size);
        int [] sorted = sortBubble(array);        
        
        long stop = Calendar.getInstance().get(Calendar.MILLISECOND);
        int timeDiff = (int)(stop - start);
        
        saveInDatabase("bubble", size, timeDiff);
        return "Requested time: "+String.valueOf(timeDiff);
    }
    
    @GET
    @Path("counting/{size}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCounting(@PathParam("size") Integer size) {
        long start = Calendar.getInstance().get(Calendar.MILLISECOND);
        
        //Action!!
        int [] array = generateRandomNumbers(size);
        int [] sorted = sortCounting(array);        
        
        long stop = Calendar.getInstance().get(Calendar.MILLISECOND);
        int timeDiff = (int)(stop - start);
        
        saveInDatabase("counting", size, timeDiff);
        return "Requested time: "+String.valueOf(timeDiff);
    }
    
    private int [] generateRandomNumbers(int amount){
        ArrayList<Integer> obj = new ArrayList<Integer>();
        for(int i=0;i<amount;i++){
            obj.add(i);
        }      
        Collections.shuffle(obj);
        
        int[] array = new int[obj.size()];
        for(int i = 0; i < obj.size(); i++) array[i] = obj.get(i);
        return array;
    }

    private void saveInDatabase(String name, Integer size, Integer time) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BadanieRestApi_pu");

        DaneBadanie values = new DaneBadanie();
        values.setAlgorithm(name);
        values.setSize_query(size);
        values.setTime_execution(time);

        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();            
            em.persist(values);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public static int[] sortBubble(int [] original){    
 
      int [] array = Arrays.copyOf(original, original.length);
      int remaining = array.length - 1;
      for(int x = 0; x < (array.length-1); x++) {
         for(int y = 0; y < (remaining); y++) {
            int tmp;
            if ( array[y] > array[y+1] ) {
              tmp =  array[y+1]; 
              array[y+1] = array[y];
              array[y] = tmp;
            }
         }
         remaining--;
      }
      return array;
    }
    public static int[] sortCounting(int[] array) {
    
    int[] aux = new int[array.length];
 
    int min = array[0];
    int max = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      } else if (array[i] > max) {
        max = array[i];
      }
    }
 
    int[] counts = new int[max - min + 1];
 
    for (int i = 0;  i < array.length; i++) {
      counts[array[i] - min]++;
    }
 
    counts[0]--;
    for (int i = 1; i < counts.length; i++) {
      counts[i] = counts[i] + counts[i-1];
    }

    for (int i = array.length - 1; i >= 0; i--) {
        aux[counts[array[i] - min]--] = array[i];
    }
 
    return aux;
  }
}
