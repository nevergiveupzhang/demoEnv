package com.example.demo.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileReadDemo {

	public static void main(String[] args) {
		String file = RandomAccessFileDemo.class.getResource("access.txt").getFile();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			System.out.println("RandomAccessFile的文件指针初始位置:" + raf.getFilePointer());
			raf.seek(0);
			byte[] bbuf = new byte[1024];
			int hasRead = 0;
			while ((hasRead = raf.read(bbuf)) > 0) {
				System.out.print(new String(bbuf, 0, hasRead));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (raf != null) {
					raf.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
