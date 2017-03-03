/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.jms.service.api;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 *
 * @author mkebri
 */
public interface JmsMesageListener extends SessionAwareMessageListener<Message> {

    //private static Logger logger = LoggerFactory.getLogger(AuditListener.class);
    /**
     * Handle a message , within a JMS session.
     */
    @Override
    void onMessage(Message msg, Session session) throws JMSException;

   // void onMessage(ObjectMessage objectMessage, Session session) throws JMSException;

}
