/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import sup.sms.entity.Message;

/**
 *
 * @author laurent
 */
@Stateless
public class MessageJmsService {
    @Resource(mappedName = "ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "queue/message")
    private Destination destination;

    /**
     * Send a message to the JMS queue
     * @param message
     * @throws JMSException 
     */
    public void sendSms(Message message) throws JMSException{
        Connection cnx = connectionFactory.createConnection();
        Session session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(destination);

        TextMessage jmsMessage = session.createTextMessage();
        jmsMessage.setText(message.getTransmitter() + "\n\r" + message.getMessage() + "\n\r" + message.getReceiver());
        producer.send(jmsMessage);			    
        cnx.close();
    }
}
