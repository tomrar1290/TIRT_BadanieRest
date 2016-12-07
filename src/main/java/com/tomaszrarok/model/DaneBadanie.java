/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tomasz Rarok
 */
@Entity
@Table(name = "dane_badanie")
public class DaneBadanie  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public DaneBadanie(){
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "time_execution")
    private Integer time_execution;    
    
    @Column(name = "algorithm")
    private String algorithm;
    
    @Column(name = "size_query")
    private Integer size_query;

    //<editor-fold defaultstate="collapsed" desc="GetterSetter">
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the time_execution
     */
    public Integer getTime_execution() {
        return time_execution;
    }
    
    /**
     * @param time_execution the time_execution to set
     */
    public void setTime_execution(Integer time_execution) {
        this.time_execution = time_execution;
    }
    
    /**
     * @return the algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }
    
    /**
     * @param algorithm the algorithm to set
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    
    /**
     * @return the size
     */
    public Integer getSize_query() {
        return size_query;
    }
    
    /**
     * @param size the size to set
     */
    public void setSize_query(Integer size) {
        this.size_query = size;
    }
//</editor-fold>
}
