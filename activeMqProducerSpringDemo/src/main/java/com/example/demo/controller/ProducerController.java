package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.activemq.QueueProducer;
import com.example.demo.activemq.TopicProducer;

@Controller
public class ProducerController {
	@Autowired
	QueueProducer producer;
	@Autowired
	TopicProducer topicProducer;
	
	@RequestMapping("produce")
	@ResponseBody
	public String produce(String data) {
		System.out.println("begin to produce "+data);
		producer.sendTextMessage(data);
		topicProducer.sendMessage(data);
		return data;
	}
	
	
}
