/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.rest.serviceimpl;

import com.sun.corba.se.impl.util.Utility;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import javax.jms.JMSException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tanaguru.jms.service.api.JmsMessageSender;
import org.tanaguru.model.AuditModel;
import org.tanaguru.rest.service.AuditRestService;
import org.tanaguru.rest.util.AuditInputsParameters;
import org.tanaguru.rest.util.DataStore;
import org.tanaguru.rest.util.TrackAuditImpl;
import org.tanaguru.rest.util.UtilityCall;


/**
 *
 * @author mkebri
 */
@Service
@Path(DataStore.SERVICE_PATH)
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML, "application/json; charset=UTF-8"})
@Consumes("application/json; charset=UTF-8")
public class AuditRestServiceImpl implements AuditRestService {

    private static final Logger LOGGER = Logger.getLogger(AuditRestServiceImpl.class);

//    @Autowired
//    ParameterDataService parameterDataService;
//
//    @Autowired
//    private AuditService auditService;
//    public void setAuditService(AuditService auditService) {
//        this.auditService = auditService;
//    }
    @Autowired
    private JmsMessageSender jmsMessageSender;

    @Autowired
    TrackAuditImpl trackAuditImpl;

    @GET
    @Path("/test")
    @Produces({MediaType.TEXT_PLAIN})
    @Override
    public String greeting() {
        return "it works";
    }

    @GET
    @Path(DataStore.AUDIT_PAGE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String auditPage(@QueryParam(AuditInputsParameters.PAGE_URL) final String pageURL,
            @QueryParam(AuditInputsParameters.AUDIT_LEVEL) final String level) {
        //String tblMarker, String prTblMarker, String dcrImgMarker, String infImgMarker) {

        LOGGER.info("[AUDIT][IN] from Rest service : " + pageURL);
        //1- log in db message and type of audit
        //2- format message to objet audit

        LOGGER.info("Initialize parameters...");
        //get parameters from DB
        //     ParameterUtils.initParametersMap(parameterDataService);
        //get default set of parameters
        //    Set<Parameter> parameters = ParameterUtils.getDefaultParametersForPA();
        //set option values
        //       ParameterUtils.initializePAInputOptions("AW22;Ar", null, null, null, null, parameters);

        LOGGER.info("Launch audit page service...");

        //launch audit 
        //    Audit audit = auditService.auditPage(pageURL, parameters);
        //3- persist audit information on db observatoire	
        //   Long idAudit = audit.getId();
        //    LOGGER.info("Audit finished" + audit.getId());
        return DataStore.SUCCESS_RESULT;
    }

    /**
     * Method that sent message to Queue for launching the audit
     *
     * @param pageURL
     *
     */
    @GET
    @Path(DataStore.LAUNCH_AUDIT)
    @Produces(MediaType.APPLICATION_JSON)
    public String launchAuditPage(@QueryParam(AuditInputsParameters.PAGE_URL) final String pageURL) {

        AuditModel auditModel = new AuditModel(0, null, pageURL, pageURL);
        auditModel.setUrl("https://www.google.fr");
        auditModel.setIdCode(UtilityCall.getCode());
        try {
            // sendToAudit to default destination 
            jmsMessageSender.sendToAudit(auditModel);

        } catch (Exception e) {
            LOGGER.debug("Error sending message: " + e.getMessage());
        }

        //add the audit to track
//        trackAuditImpl.addAuditToHashmap(Long.MIN_VALUE, auditModel);

        return DataStore.SUCCESS_RESULT;
    }

    /**
     * Method that sent message to Queue for launching the audit
     *
     * @param pageURL
     *
     */
    @GET
    @Path(DataStore.LAUNCH_AUDIT2)
    @Produces(MediaType.APPLICATION_JSON)
    public String launchAuditPage(@QueryParam(AuditInputsParameters.PAGE_URL) final String pageURL,
            @QueryParam(AuditInputsParameters.AUDIT_LEVEL) final String level) {

        try {
            // sendToAudit to default destination 
            jmsMessageSender.send("https://www.google.fr");

        } catch (Exception e) {
            LOGGER.debug("Error sending message: " + e.getMessage());
        }

        return DataStore.SUCCESS_RESULT;
    }

    @Override
    public void auditPage(String pageURL, String level, String tblMarker, String prTblMarker, String dcrImgMarker, String infImgMarker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
//
//    @POST
//    @Path(value = "post1")
//    @Consumes(value = MediaType.APPLICATION_JSON)
//    public void doPost1(@Suspended final AsyncResponse asyncResponse, final AuditModel auditModel) {
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                asyncResponse.resume(doDoPost1(auditModel));
//            }
//        });
//    }

    @POST
    @Path(value = "/post")
    @Produces(value = MediaType.TEXT_PLAIN)
    @Consumes(value = MediaType.APPLICATION_JSON)
    private void doPost(@Suspended
            final AsyncResponse asyncResponse, @PathParam(value = "auditModel")
            final AuditModel auditModel) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doDoPost(auditModel));
            }
        });
    }

    private String doDoPost(AuditModel auditModel) {
        return "Received the auditModel !! ";
    }

    @POST
    @Path("/postMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMessageJson(@Suspended
    final AsyncResponse asyncResponse, final AuditModel auditModel) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doCreateMessageJson(auditModel));
            }
        });

    }

    private Response doCreateMessageJson(AuditModel auditModel) {
        String data = "Message Saved" + auditModel.getIdCode();
        auditModel.setState(DataStore.INITIAL_AUDIT_STATE);
        auditModel.setIdCode(UtilityCall.getCode());
        try {
            // sendToAudit to default destination 
            jmsMessageSender.sendToAudit(auditModel);

        } catch (Exception e) {
            LOGGER.debug("Error sending message: " + e.getMessage());
        }

        //add the audit to track
        trackAuditImpl.addAuditToHashmap(auditModel.getIdCode(), auditModel);
        
        if (trackAuditImpl.getAuditModel(auditModel.getIdCode()) !=null){
        while (!trackAuditImpl.getAuditModel(auditModel.getIdCode()).getState().equalsIgnoreCase(DataStore.COMPLETED_AUDIT_STATE))
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(AuditRestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(trackAuditImpl.getAuditModel(auditModel.getIdCode()).getMark());
        stringBuilder.append("#");
        stringBuilder.append(trackAuditImpl.getAuditModel(auditModel.getIdCode()).getPassed());
        stringBuilder.append("#");
        stringBuilder.append(trackAuditImpl.getAuditModel(auditModel.getIdCode()).getFailed());
        stringBuilder.append("#");
        stringBuilder.append(trackAuditImpl.getAuditModel(auditModel.getIdCode()).getPreQualified());
        stringBuilder.append("#");
        stringBuilder.append(trackAuditImpl.getAuditModel(auditModel.getIdCode()).getNotApplicable());
        stringBuilder.append("#");
        stringBuilder.append(trackAuditImpl.getAuditModel(auditModel.getIdCode()).getNotTested());
        
              
        
        data = stringBuilder.toString();
        
        trackAuditImpl.removeAuditFromHashmap(auditModel.getIdCode()); // remove auditModel from the track hashmap 
        
        return Response.status(200).entity(data).build();
    }
    
}
