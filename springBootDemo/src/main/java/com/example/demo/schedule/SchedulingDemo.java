package com.example.demo.schedule;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingDemo {
	@Value("${cron.domainStats}")
	private String cron;
	
	@Scheduled(cron="${cron.domainStats}")
	private void domainStatsTask() {
		System.out.println("task->"+cron);
		System.out.println(new File(SchedulingDemo.class.getResource("/").getPath()).getParentFile().getParentFile().getParent());
	}
}
