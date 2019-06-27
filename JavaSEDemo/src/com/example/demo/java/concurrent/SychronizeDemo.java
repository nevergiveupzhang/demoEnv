package com.example.demo.java.concurrent;

public class SychronizeDemo {

	public static void main(String[] args) {
		ForRun target = new ForRun();
		new Thread(new Runnable() {

			@Override
			public void run() {
				target.exec();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				target.exec();
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				target.exec();
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				target.exec();
			}
		}).start();
	}

}
