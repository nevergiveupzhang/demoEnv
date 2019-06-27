package com.example.demo.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="report")
public class ReportConfiguration {
	private List<IndexConfigModel> indexes;

	public List<IndexConfigModel> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<IndexConfigModel> indexes) {
		this.indexes = indexes;
	}
}