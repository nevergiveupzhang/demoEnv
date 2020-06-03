package com.example.demo.java.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Test {

	// private static Map<String,String> subjectTypes=new
	// HashMap<String,String>();
	// static {
	// subjectTypes.put("A", "001");
	// subjectTypes.put("B", "001");
	// subjectTypes.put("C", "001");
	// subjectTypes.put("D", "001");
	// subjectTypes.put("E", "001");
	// subjectTypes.put("F", "002");
	// subjectTypes.put("G", "002");
	// subjectTypes.put("H", "002");
	// subjectTypes.put("I", "001");
	// subjectTypes.put("J", "002");
	// }

	private static Map<String, String> subjectTypes = new HashMap<String, String>() {
		{
			put("A", "001");
			put("B", "001");
			put("C", "001");
			put("D", "001");
			put("E", "001");
			put("F", "002");
			put("G", "002");
			put("H", "002");
			put("I", "001");
			put("J", "002");
		}

	};

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		// Class.forName("com.mysql.jdbc.Driver");// 加载及注册JDBC驱动程序
		// String url =
		// "jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
		// Connection con = DriverManager.getConnection(url);
		// Statement stmt = con.createStatement();
		// String query = "select * from test";
		// ResultSet rs = stmt.executeQuery(query);
		// while (rs.next()) {
		// rs.getString(1);
		// rs.getInt(2);
		// }

		InputStream in = new FileInputStream("/user/wangzheng/test.txt");
		InputStream bin = new BufferedInputStream(in);
		byte[] data = new byte[128];
		while (bin.read(data) != -1) {
			// ...
		}
	}

}
