package com.example.demo.Log4jDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonFormatLogbackDemo 
{
	private static Logger LOGGER = LoggerFactory.getLogger(JsonFormatLogbackDemo.class);;
    public static void main( String[] args )
    {
        LOGGER.info("{}", new User(1,"zhangsan"));
    }
}
