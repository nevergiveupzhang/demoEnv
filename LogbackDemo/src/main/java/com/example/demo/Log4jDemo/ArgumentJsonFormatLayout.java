package com.example.demo.Log4jDemo;

import java.util.stream.Stream;

import org.slf4j.helpers.MessageFormatter;

import com.alibaba.fastjson.JSON;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ArgumentJsonFormatLayout extends MessageConverter  {
	 @Override
	    public String convert(ILoggingEvent event) {
	        try {
	            return MessageFormatter.arrayFormat(event.getMessage(), Stream.of(event.getArgumentArray())
	                .map(JSON::toJSONString).toArray()).getMessage();
	        } catch (Exception e) {
	            return event.getMessage();
	        }
	    }

}
