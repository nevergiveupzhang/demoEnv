package com.example.demo.java.concurrent;

public class ForRun {

	public synchronized void exec() {
		System.out.println(Thread.currentThread().getName()+"->ForRun.exec()");
		doOther();
	}
	
	private void doOther() {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(Thread.currentThread().getName()+"->ForRun.doOther()");
	}

}
