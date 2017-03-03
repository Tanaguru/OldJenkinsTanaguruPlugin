/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.jms.service.listenerimpl;

import org.apache.log4j.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.tanaguru.jms.service.api.JmsMesageListener;
import org.tanaguru.model.AuditModel;
import org.tanaguru.rest.util.DataStore;
import org.tanaguru.rest.util.TrackAuditImpl;

/**
 *
 * @author mkebri
 */
public class JmsMessageListenerImpl implements JmsMesageListener {

    private final static Logger LOGGER = Logger.getLogger(JmsMessageListenerImpl.class);

    @Autowired
    public TrackAuditImpl trackAuditImpl;

    /**
     * Handle a message , within a JMS session.
     */
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        LOGGER.info("## ===> : Fire OnMessage    (rest jms project)  ");

        /**
         * @TODO recupérer le message split id et mark mettre a jour l'l'objet
         * audit dans la fil hashmap
         */
        if (message instanceof TextMessage) {
            LOGGER.info("## ===> :  Message type TextMessage  ");

            String msg = ((TextMessage) message).getText();

            String[] tnz = msg.split("#");

            LOGGER.info("Id audit : " + tnz[0]);
            LOGGER.info("Mark Audit : " + tnz[1]);
            LOGGER.info("IdCodeAudit Audit : " + tnz[2]);

            LOGGER.info("Size of the hashmap: " + trackAuditImpl.getSize());

            AuditModel auditModel = null;
            if (trackAuditImpl.getAuditModel(tnz[2]) != null) {
                auditModel = trackAuditImpl.getAuditModel(tnz[2]);
                LOGGER.info("url from listener " + auditModel.getUrl());
            }
//           
            auditModel.setMark((double) Double.valueOf(tnz[1]));
            auditModel.setPassed((int) Integer.valueOf(tnz[3]));
            auditModel.setFailed((int) Integer.valueOf(tnz[4]));
            auditModel.setPreQualified((int) Integer.valueOf(tnz[5]));
            auditModel.setNotApplicable((int) Integer.valueOf(tnz[6]));
            auditModel.setNotTested((int) Integer.valueOf(tnz[7]));
            auditModel.setState(DataStore.COMPLETED_AUDIT_STATE);  // change state for let to the rest loop to now audit is ok 

            trackAuditImpl.addAuditToHashmap(tnz[2], auditModel);

        } else if (message instanceof ObjectMessage) {

            LOGGER.info("## ===> : Message type ObjectMessage ");
            AuditModel auditModel = null;
            auditModel = (AuditModel) ((ObjectMessage) message).getObject();
            LOGGER.info("## ===> :  Msg id =  " + message.getJMSMessageID());
            LOGGER.info("## ===> :  Audit url  =  " + auditModel.getUrl());

            // update the hashmap 
            //    trackAuditImpl.addAuditToHashmap(idAudit, auditModel);
        }
    }

}
