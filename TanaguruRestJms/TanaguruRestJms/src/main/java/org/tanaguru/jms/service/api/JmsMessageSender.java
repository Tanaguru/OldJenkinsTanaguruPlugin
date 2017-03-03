/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.jms.service.api;

import javax.jms.Destination;
import org.tanaguru.model.AuditModel;

/**
 *
 * @author mkebri
 */
public interface JmsMessageSender {

    /**
     * sendToAudit text to default destination
     * @param text
     */
    void send(final String text);
    
    /**
     * sendToAudit Object to default destination
     * @param text
     */
    void sendToAudit(final AuditModel text);

    /**
     * Send text message to a specified destination
     * @param dest
     * @param text
     */
    void send(final Destination dest, final String text);

    /**
     * Simplify the sendToAudit by using convertAndSend
     * @param text
     */
    void sendText(final String text);
    
}
