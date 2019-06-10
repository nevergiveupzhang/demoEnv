package com.example.demo.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicMessageListener implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage=(TextMessage)message;        
        try {
            System.out.println("接收到TOPIC消息："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
