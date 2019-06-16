package com.example.demo.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="report")
public class ReportConfiguration {
	private Map<String, Map<String, Map<String, String[]>>> config;

	public Map<String, Map<String, Map<String, String[]>>> getConfig() {
		return config;
	}

	public void setConfig(Map<String, Map<String, Map<String, String[]>>> config) {
		this.config = config;
	}
}
