package com.example.demo.MavenDemp.orgjson;

import org.json.XML;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class XmlHelper {

	public static void main(String[] args) {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"	<extensionElements>\r\n" + 
				"	<activiti:formProperty id=\"project_id\" name=\"项目id\" type=\"string\" required=\"true\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"project_code\" name=\"项目代码\" type=\"string\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"project_name\" name=\"项目名称\" highlight=\"true\" type=\"text_ik\" required=\"true\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"state\" name=\"项目状态\" type=\"string\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"datestart\" name=\"立项日期\" type=\"date\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"charge\" name=\"负责人\" type=\"letter_ik\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"funds\" name=\"资金\" type=\"string\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"subjectCategory\" name=\"学科门类\" type=\"string\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"projectLevel\" name=\"项目级别\" type=\"string\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"communitycode\" name=\"所属单位\" type=\"string\"></activiti:formProperty>\r\n" + 
				"	<activiti:formProperty id=\"teachAndResearchType\" name=\"教研分类\" type=\"string\"></activiti:formProperty>\r\n" + 
				"</extensionElements>";
		String jsonStr="{\r\n" + 
				"     \"groups\": [\r\n" + 
				"          {\r\n" + 
				"               \"key\": \"group1\",\r\n" + 
				"               \"group\": \"22\",\r\n" + 
				"               \"name\": \"部门初审\"\r\n" + 
				"          },\r\n" + 
				"          {\r\n" + 
				"               \"key\": \"group2\",\r\n" + 
				"               \"group\": \"21\",\r\n" + 
				"               \"name\": \"部门二审\"\r\n" + 
				"          },\r\n" + 
				"          {\r\n" + 
				"               \"key\": \"group3\",\r\n" + 
				"               \"group\": \"23_25\",\r\n" + 
				"               \"name\": \"学校初审\"\r\n" + 
				"          },\r\n" + 
				"          {\r\n" + 
				"               \"key\": \"group4\",\r\n" + 
				"               \"group\": \"24_26\",\r\n" + 
				"               \"name\": \"学校终审\"\r\n" + 
				"          }\r\n" + 
				"     ]\r\n" + 
				"}";
		String resultStr = XML.toJSONObject(xml.replace("activiti:formProperty", "formProperty")).toString();
		JSONObject propertyJson = JSONObject.parseObject(resultStr);
		JSONArray jsonArray = JSONObject.parseObject(propertyJson.getString("extensionElements")).getJSONArray("formProperty");;
		
		JSONObject processJson = JSONObject.parseObject(jsonStr);
		JSONArray groupArray = processJson.getJSONArray("groups");
		for (int i = 0; i < groupArray.size(); i ++) {
			JSONObject groupConfig = groupArray.getJSONObject(i);
			String key = groupConfig.getString("key");
			JSONObject groupRule = new JSONObject();
			groupRule.put("id", key + "Rule");
			groupRule.put("type", "string");
			groupRule.put("value", groupConfig.get("group"));
			jsonArray.add(groupRule);
			JSONObject group = new JSONObject();
			group.put("id", key);
			group.put("type", "groups");
			jsonArray.add(group);
			
			JSONObject groupName = new JSONObject();
			groupName.put("id", key + "Name");
			groupName.put("type", "string");
			groupName.put("value", groupConfig.get("name"));
			jsonArray.add(groupName);
		}
		System.out.println(jsonArray.toJSONString());
	}

}
