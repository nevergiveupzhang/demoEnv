package com.example.demo;

import java.io.IOException;

public class SqlGenerateTool {

	public static void main(String[] args) throws IOException {
		FileUtil.init("C:\\Workspace\\Tmp\\user.sql");
		for(int i=1;i<=100000;i++) {
			FileUtil.write(i+","+"name"+StringUtil.getRandomString(2)+","+NumberUtil.getRandomNumber(20,30)+"\n");
		}
		FileUtil.close();
	}

}
