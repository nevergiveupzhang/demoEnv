package com.example.demo.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
@Component
public class TopicProducer {
	@Autowired
    private JmsTemplate jmsTemplate;
	
	@Resource(name="topicDestination")  
    private Destination topicDestination;
	
	public void sendMessage(final String text) {
		jmsTemplate.send(topicDestination, new MessageCreator() {
            
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
            }
        });
	}
}
