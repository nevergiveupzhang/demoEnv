package com.example.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	private static BufferedWriter bw;

	public static void init(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		bw = new BufferedWriter(new FileWriter(file));
	}
	
	public static void write(String content) throws IOException {
		bw.write(content);
	}
	
	public static void close() throws IOException {
		if(bw!=null) {
			bw.flush();
			bw.close();
		}
	}
}
