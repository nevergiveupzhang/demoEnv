package com.example.demo.activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.task.Task;

public class ActivitiTest {
	private static ProcessEngine processEngine;

	private static void creatActivitiTask() {
		processEngine.getRepositoryService().createDeployment().addClasspathResource("MyProcess.bpmn")
				.addClasspathResource("MyProcess.png").deploy();
	}

	private static void initEngine() {
		processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
	}

	private static void testQingjia(String taskId) {
		processEngine.getTaskService().complete(taskId); // 查看act_ru_task表

	}

	private static void testFinishTask_manager() {
		processEngine.getTaskService().complete("5002"); // 查看act_ru_task数据表
	}

	private static void testFinishTask_Boss() {
		processEngine.getTaskService().complete("7502"); // 查看act_ru_task数据表
	}

	private static void testQueryTask() {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee("李老师").list();

		for (Task task : tasks) {
			System.out.println(task.getName());
		}
	}

	private static void startProcessInstance(String procdefId) {
		processEngine.getRuntimeService()
		.startProcessInstanceById(procdefId); //这个是查看数据库中act_re_procdef表
	}
	public static void main(String[] args) {
		initEngine();
		startProcessInstance("myProcess:3:20004");
//		testQingjia("15005");
	}

}
