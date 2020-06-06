package com.example.demo;

import java.io.IOException;

public class SqlGenerateTool {

	public static void main(String[] args) throws IOException {
		FileUtil.init("C:\\Workspace\\Tmp\\user.sql");
		for(int i=1;i<=1000000;i++) {
			FileUtil.write(i+","+StringUtil.getRandomString(6)+","+StringUtil.getRandomString(6)+","+StringUtil.getRandomString(6)+","+StringUtil.getRandomString(6)+","+StringUtil.getRandomString(6)+","+NumberUtil.getRandomNumber(20,30)+"\n");
//			FileUtil.write(i+","+StringUtil.getRandomString(6)+"\n");
		}
		FileUtil.close();
	}

}
