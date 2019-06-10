package com.example.demo.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws IOException {
		String file = RandomAccessFileDemo.class.getResource("access.txt").getFile();
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		ByteBuffer bb=ByteBuffer.allocate(8);
		int hasRead=raf.getChannel().read(bb);
		System.out.println(new String(bb.array()));
//		String[] arrays = new String[] { "Hello Hadoop", "Hello Spark", "Hello Hive" };
//		raf.seek(raf.length());
//		raf.write("追加内容:\n".getBytes());
//		for (String arr : arrays) {
//			raf.write((arr + "\n").getBytes());
//		}

//		byte[] bbuf = new byte[4];
//		int hasRead = 0;
//		while ((hasRead = raf.read(bbuf)) > 0) {
//			System.out.println(new String(bbuf, 0, hasRead));
//		}
		
//		raf.seek(0);
//		raf.write(12);
//		raf.writeChar(101);
//		raf.seek(0);
//		System.out.println(raf.read());
//		System.out.println(raf.readChar());
//		
		char c=150;
		System.out.println(c);
//		raf.writeUTF("helloword");
//		raf.writeInt(20);
//		raf.close();
//		System.out.println(raf.readUTF());
//		System.out.println(raf.length());
//		System.out.println(raf.getFilePointer());
//		System.out.println(raf.readLine());
//		System.out.println(raf.readByte());
//		System.out.println(raf.readBoolean());
//		System.out.println(raf.getFilePointer());
	}
}
