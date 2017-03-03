package org.tanaguru.jsm.service.senderimpl;
 
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
 
public class DemoMain {
 
  public static void main(String[] args) {
    // init spring context
    ApplicationContext ctx = new ClassPathXmlApplicationContext("jms-sender.xml");
         
    // get bean from context
    JmsMessageSenderImpl jmsMessageSender = (JmsMessageSenderImpl)ctx.getBean("jmsMessageSender");
         
    // sendToAudit to default destination 
    jmsMessageSender.send("https://www.google.fr");
         
    // sendToAudit to a code specified destination
//    Queue queue = new ActiveMQQueue("test");
//    jmsMessageSender.sendToAudit(queue, "http://");
   
    // close spring application context
    ((ClassPathXmlApplicationContext)ctx).close();
  }
 
}