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

	private static class ForRun {

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
}
