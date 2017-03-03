package org.tanaguru.jsm.service.senderimpl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.tanaguru.jms.service.api.JmsMessageSender;
import org.tanaguru.model.AuditModel;

@Service
public class JmsMessageSenderImpl implements JmsMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * sendToAudit text to default destination
     *
     * @param text
     */
    @Override
    public void send(final String text) {

        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                //set ReplyTo header of Message, pretty much like the concept of email.
                message.setJMSReplyTo(new ActiveMQQueue("myQueue"));
                return message;
            }
        });
    }
    
    
    
    
     /**
     * sendToAudit ObjectMessage to default destination for launching the audit
     *
     * @param ObjectMessage
     */
    @Override
    public void sendToAudit(final AuditModel auditModel) {
        
        this.jmsTemplate.send("myQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                
                Message message = session.createObjectMessage(auditModel);
                //set ReplyTo header of Message, pretty much like the concept of email.
                message.setJMSReplyTo(new ActiveMQQueue("myQueue"));
                return message;
            }
        });
    }
    
    

    /**
     * Simplify the sendToAudit by using convertAndSend
     *
     * @param text
     */
    @Override
    public void sendText(final String text) {
        this.jmsTemplate.convertAndSend(text);
    }

    /**
     * Send text message to a specified destination
     *
     * @param dest
     * @param text
     */
    @Override
    public void send(final Destination dest, final String text) {

        this.jmsTemplate.send(dest, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                return message;
            }
        });
    }
}
