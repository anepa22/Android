package com.correoargentino.hostel.modules;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: Impositor
 */
@MessageDriven(
		name="Impositor",
		activationConfig = {@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				 			@ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Auto-acknowledge"),
				 			@ActivationConfigProperty(propertyName = "destination", propertyValue="jms/queue/hosttel/InposicionMsg")//Name:InputMsg JNDI Names:java:/jms/queue/InputMsg
				
		})
public class Impositor implements MessageListener {
	@Resource
    private MessageDrivenContext mdc;
	
    /**
     * Default constructor. 
     */
    public Impositor() {
        System.out.println("Hola...");
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
    		TextMessage msg = null;
    		if (message instanceof TextMessage) {
    			msg = (TextMessage) message;
    			System.out.println("MESSAGE BEAN: Message receiveds: " + msg.getText());
    			int i = 1/0;
    		} else {
    			System.out.println("Message of wrong type: " + message.getClass().getName());
    		}
    	} catch (JMSException e) {
    		e.printStackTrace();
    		mdc.setRollbackOnly();
    	} catch (Throwable te) {
    		mdc.setRollbackOnly();
    	}
    }
}
