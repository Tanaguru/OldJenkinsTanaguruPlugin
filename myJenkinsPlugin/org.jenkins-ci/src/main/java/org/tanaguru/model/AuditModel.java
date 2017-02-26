/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.model;

import java.io.Serializable;

/**
 *
 * @author mkebri
 */
public class AuditModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String idCode;   // implememnt random value initialisation 
    private String url;
    private String type;
    private String state;
    private String scenario;
    

    
    /**
     * default constructor
     */
    public AuditModel() {
    }
    
  
  /**
   * constructor 
   * @param id
   * @param idCode
   * @param url
   * @param type 
   */
    public AuditModel(int id, String idCode, String url, String type) {
        this.id = id;
        this.idCode = idCode;
        this.url = url;
        this.type = type;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
    
    

   

}

