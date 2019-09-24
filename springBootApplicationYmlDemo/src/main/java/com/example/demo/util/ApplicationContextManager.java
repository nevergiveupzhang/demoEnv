package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
@Service
public class ApplicationContextManager implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static WebApplicationContext wac = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextManager.applicationContext = applicationContext;
	}

	public static <T> T getServiceByName(String beanName, Class<T> type) {
		return getWebContext() == null ? getApplicationContext().getBean(beanName, type)
				: getWebContext().getBean(beanName, type);
	}

	public static WebApplicationContext getWebContext() {
		if (wac != null)
			return wac;
		if (wac == null) {
			wac = ContextLoader.getCurrentWebApplicationContext();
		}

		return wac;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
