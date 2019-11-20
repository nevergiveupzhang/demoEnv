package com.example.demo.MavenDemp.fastjson;


import com.alibaba.fastjson.JSONObject;

public class FastjsonDemo2 {

	public static void main(String[] args) {
		String s="{\"code\":\"aa\\\\iiibb\"}";
		JSONObject jo=JSONObject.parseObject(s);
		System.out.println(jo.toJSONString());
		
		User user=new User();
		user.setId(10);
		user.setName("zhangsan\\lisi");
		String str=JSONObject.toJSONString(user);
		System.out.println(str);
	}

}
