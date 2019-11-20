package com.example.demo.MavenDemp.fastjson;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastjsonDemo {

	public static void main(String[] args) {
		User user=new User();
		user.setId(10);
		user.setName("zhangsan\\lisi");
		user.setDate(new Date());
		String str=JSONObject.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss",
				SerializerFeature.WriteDateUseDateFormat);
		System.out.println(str.replace("\\\\", "\\"));
	}

}
