package com.example.demo;

import java.io.IOException;
import java.util.Random;

public class SqlGenerateTool {

	public static void main(String[] args) throws IOException {
		FileUtil.init("E:\\Tmp\\user.sql");
		for(int i=1;i<=1000000;i++) {
//			FileUtil.write(i+","+new Random().nextInt(2)+","+StringUtil.getRandomString(20)+","+StringUtil.getRandomString(20)+","+StringUtil.getRandomString(20)+","+StringUtil.getRandomString(20)+","+StringUtil.getRandomString(20)+","+NumberUtil.getRandomNumber(20,30)+"\n");
			FileUtil.write(i+","+i+","+i+"\n");
		}
		FileUtil.close();
	}

}
