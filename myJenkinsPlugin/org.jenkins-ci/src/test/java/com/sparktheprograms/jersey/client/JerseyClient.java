 package com.sparktheprograms.jersey.client;
 
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import org.tanaguru.model.AuditModel;
//import com.sparktheprograms.jersey.Message;

public class JerseyClient {
       public static final String baseUri = "http://localhost:8080/rest/service";
       private Client client = null;
       private WebTarget target = null;
      
       public JerseyClient() {
              client = ClientBuilder.newClient();
              target = client.target(baseUri);
       }
      
       public void reloadUri() {
              target = null;
              target = client.target(baseUri);
       }
      
       public void getRequest() {
              target = target.path("/test");
              // GET Request from Jersey Client
              Response response =  target.request(MediaType.TEXT_PLAIN) 
                                   .get(Response.class); 
              
              System.out.println("Status de la requet :" +response.getStatus() );
              
              if(response.getStatus() == 200) {
                  
                     String message = response.readEntity(new GenericType<String>() {});
                     System.out.println(message);
                     System.out.println("Execution avec succès !!!!");
              }
       }
      
       public void postRequest() {
              reloadUri();
             String input ="{\"id\":\"12\",\"idCode\":\"ZF123\", \"url\":\"myurl\",\"type\":\"RestApiJms\",\"state\":\"Initialization\",\"scenario\":\"my scenario\"}";
              target = target.path("/postMessage");

              // POST Request from Jersey Client
              Response response = target.request(MediaType.APPLICATION_JSON)
               .post(Entity.entity(input, MediaType.APPLICATION_JSON),Response.class);
             
              System.out.println(response);
              if(response.getStatus() == 200) {
                  
                     String message = response.readEntity(new GenericType<String>() {});
                     System.out.println(message);
                     System.out.println("post success");
              }
       }
      
       public void postRequestUsingGson() {
              reloadUri();
              target = target.path("/postMessage");
              Gson gson = new Gson();
              AuditModel auditModel = new AuditModel();
              auditModel.setId(19);
              auditModel.setIdCode("ZF1919");
              String input = gson.toJson(auditModel);

              //POST Request from jersey Client Using GSON
              Response response = target.request(MediaType.APPLICATION_JSON)
               .post(Entity.entity(input, MediaType.APPLICATION_JSON),Response.class);
             
              System.out.println(response);
              if(response.getStatus() == 200) {
                  
                     String message = response.readEntity(new GenericType<String>() {});
                     System.out.println(message);
                     System.out.println("post request using Json is Success");
              }
       }
      
       public void putRequest() {
              reloadUri();
              target = target.path("putMessage");
              String input ="{\"site\":\"www.9threes.com\",\"message\":\"is new domain\"}";
              
              //PUT Request from Jersey Client Example
              Response response = target.request(MediaType.APPLICATION_JSON)
               .put(Entity.entity(input, MediaType.APPLICATION_JSON),Response.class);
             
              System.out.println(response);
              if(response.getStatus() == 200) {
                     System.out.println("put request using Json is Success");
              }
       }
      
       public static void main(String args[]) throws Exception {
              try {
                     JerseyClient jerseyClient = new JerseyClient();
                     jerseyClient.getRequest();
                   //  jerseyClient.postRequest();
                     jerseyClient.postRequestUsingGson();
//                     jerseyClient.putRequest();
              } catch(Exception e) {
                     System.out.println(e);
              }
       }

}