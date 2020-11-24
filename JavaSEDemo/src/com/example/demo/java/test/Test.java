package com.example.demo.java.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.java.entity.User;
import jdk.internal.util.xml.impl.Input;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {

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

//		InputStream in = new FileInputStream("/user/wangzheng/test.txt");
//		InputStream bin = new BufferedInputStream(in);
//		byte[] data = new byte[128];
//		while (bin.read(data) != -1) {
//			// ...
//		}
//		StringJoiner sj=new StringJoiner(";");
//		sj.add(null);
//		System.out.println(sj.toString());
		
		
//		System.out.println("658_dc_description_expenditureAmount_null".split("_")[4]);
//		EnumTest.getTypeByName("1").print();
		
//		List<String> list=new ArrayList<String>();
//		String s=null;
//		Object o=s;
//		String s2=(String) o;

//		Class s = Class.forName("com.example.demo.java.test.First");
//		System.out.println(Second.class.isAssignableFrom(s));

//		User user = new User();
//		user.setId(1);
//		user.setName("zhangsan");
//		System.out.println(JSONObject.toJSONString(user));

//		String s ="{\n" +
//				"\"attachment\":[],\n" +
//				"\n" +
//				"\"member\":[{\"key\":\"872782671\",\"orderHid\":\"\",\"order\":\"1\",\"type\":\"001\",\"name\":\"张红\",\"userId\":\"5\",\"idCode\":\"230107196809220228\",\"communitycode\":\"1\",\"communitycodeHid\":\"\",\"relationdepartmentcode\":\"1\",\"relationdepartmentcodeHid\":\"\",\"birth\":\"\",\"degree\":\"\",\"title\":\"\",\"mainJob\":\"\",\"workLoad\":\"\",\"title_txt\":\"\",\"menberComm_txt\":\"校办公室\",\"menberCommReal_txt\":\"校办公室\",\"order_txt\":\"第一成员(负责人)\",\"type_txt\":\"本校教师\",\"degree_txt\":\"\",\"isFirst\":1,\"status\":1}],\n" +
//				"\n" +
//				"\"achievement\":[],\n" +
//				"\n" +
//				"\"metadata\":[{\"name\":\"397_project_description_unitType_zh\",\"value\":\"1\"},{\"name\":\"391_project_type_teachAndResearchType_zh\",\"value\":\"01\"},{\"name\":\"250_project_type_subjectType_zh\",\"value\":\"001\"},{\"name\":\"242_project_name_projectName_zh\",\"value\":\"测试预算科目1\"},{\"name\":\"243_project_code_projectCode_zh\",\"value\":\"测试预算科目1\"},{\"name\":\"617_project_type_projectCategory_zh\",\"value\":\"\"},{\"name\":\"392_project_Project_Level_zh\",\"value\":\"1\"},{\"name\":\"252_project_Project_Source_zh\",\"value\":\"4\"},{\"name\":\"254_project_Project_Type_zh\",\"value\":\"请选择\"},{\"name\":\"393_project_Project_UndertakingUnit_zh\",\"value\":\"测试预算科目1\"},{\"name\":\"288_project_UnitRanking_UnitRanking_zh\",\"value\":\"1\"},{\"name\":\"290_project_ProjectStatus_ProjectStatus_zh\",\"value\":\"001\"},{\"name\":\"291_project_SetupDate_SetupDate_zh\",\"value\":\"2019-09-12\"},{\"name\":\"455_project_project_Year_zh\",\"value\":\"2019\"},{\"name\":\"292_project_PlanDate_PlanDate_zh\",\"value\":\"2020-09-11\"},{\"name\":\"459_project_PlanEndDate_PlanEndDate_zh\",\"value\":\"2020-09-11\"},{\"name\":\"489_project_RealEndDate_RealEndDate_zh\",\"value\":\"\"},{\"name\":\"470_project_major_first_zh\",\"value\":\"\"},{\"name\":\"471_project_major_second_zh\",\"value\":\"\"},{\"name\":\"472_project_major_third_zh\",\"value\":\"\"},{\"name\":\"245_project_economicIndustry_first_zh\",\"value\":\"\"},{\"name\":\"295_project_ScienceTechnologySubjectBig_ScienceTechnologySubjectBig_zh\",\"value\":\"\"},{\"name\":\"246_project_socioEconomicObjectives_first_zh\",\"value\":\"\"},{\"name\":\"244_project_CooperationForm_ProjectCooperationForm_zh\",\"value\":\"\"},{\"name\":\"247_project_ProjectOrganizationalForm_ProjectOrganizationalForm_zh\",\"value\":\"\"},{\"name\":\"488_project_projectSource_report_zh\",\"value\":\"请选择\"},{\"name\":\"256_project_Project_Charge_zh\",\"value\":\"张红\"},{\"name\":\"315_project_department_department_zh\",\"value\":\"1\"},{\"name\":\"257_project_Project_JobCode_zh\",\"value\":\"5\"},{\"name\":\"285_project_TotalFund_TotalFund_zh\",\"value\":\"150\"},{\"name\":\"265_project_projectFunder_ProjectFunder_zh\",\"value\":\"150\"},{\"name\":\"267_project_FundsbySelf_FundsbySelf_zh\",\"value\":\"\"},{\"name\":\"268_project_AppendFund_AppendFund_zh\",\"value\":\"\"},{\"name\":\"269_project_OtherFund_OtherFund_zh\",\"value\":\"\"},{\"name\":\"projectIdList\",\"value\":\"\"},{\"name\":\"314_project_ProjectBrief_ProjectBrief_zh\",\"value\":\"\"},{\"name\":\"310_project_description_Remark_zh\",\"value\":\"\"}],\n" +
//				"\n" +
//				"\"budgetTemp\":[\n" +
//				"{\"id\":33,\"code\":\"u_33\",\"name\":\" 直接费用\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"0\",\"level\":1,\"orderId\":null,\"showCode\":\" 直接费用\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":true,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null},\n" +
//				"{\"id\":35,\"code\":\"u_35\",\"name\":\" 设备费\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"u_33\",\"level\":2,\"orderId\":null,\"showCode\":\" 设备费\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":true,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null},{\"id\":36,\"code\":\"u_36\",\"name\":\"设备购置费\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"u_35\",\"level\":3,\"orderId\":null,\"showCode\":\"设备购置费\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":false,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null},{\"id\":37,\"code\":\"u_37\",\"name\":\"设备试制费\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"u_35\",\"level\":3,\"orderId\":null,\"showCode\":\"设备试制费\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":false,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null},{\"id\":38,\"code\":\"u_38\",\"name\":\"设备改造与租赁费\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"u_35\",\"level\":3,\"orderId\":null,\"showCode\":\"设备改造与租赁费\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":false,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null},{\"id\":39,\"code\":\"u_39\",\"name\":\"材料费\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"u_33\",\"level\":2,\"orderId\":null,\"showCode\":\"材料费\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":false,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null},{\"id\":34,\"code\":\"u_34\",\"name\":\" 间接费用\",\"nameEN\":null,\"nameTW\":null,\"parentCode\":\"0\",\"level\":1,\"orderId\":null,\"showCode\":\" 间接费用\",\"isdelete\":0,\"type\":\"20\",\"source\":\"1\",\"description\":\"\",\"project_limit\":null,\"economicId\":\"\",\"hasChild\":false,\"versionState\":null,\"projectId\":null,\"isYearByYear\":\"1\",\"projectSource\":\"4\",\"projectcategory\":\"\",\"year\":\"2019\",\"isDeleteVersion\":null}],\n" +
//				"\n" +
//				"\"budgetData\":[\n" +
//				"{\"key\":\"u_36-2020-zx\",\"value\":\"10\",\"state\":\"1\"},\n" +
//				"{\"key\":\"u_37-2020-zx\",\"value\":\"20\",\"state\":\"1\"},\n" +
//				"{\"key\":\"u_38-2020-zx\",\"value\":\"30\",\"state\":\"1\"},\n" +
//				"{\"key\":\"u_39-2020-zx\",\"value\":\"40\",\"state\":\"1\"},\n" +
//				"{\"key\":\"u_34-2020-zx\",\"value\":\"50\",\"state\":\"1\"}\n" +
//				"],\n" +
//				"\n" +
//				"\"changeStepList\":[{\"taskId\":3,\"itemIdList\":[\"mem872782671\"],\"count\":1},{\"taskId\":2,\"itemIdList\":[\"budu_36-2020-zx\",\"budu_37-2020-zx\",\"budu_38-2020-zx\",\"budu_39-2020-zx\",\"budu_34-2020-zx\"],\"count\":1}]}";
//		LinkedHashMap u = JSONObject.parseObject(s, LinkedHashMap.class);
//		System.out.println(u);


//		Second s = new Second();
//		s.f();

//		LinkedList<String> ll = new LinkedList<>();
//		ll.add("a");
//		System.out.println(ll.pollLast());
//		System.out.println(ll.pollLast());
//		System.out.println(ll.pollLast());

//		String s = "aa\r\nbb\r\ncc";
//		s = match(s,"aa([\\s\\S]*)cc");
//		System.out.println(s);

//		String s = "（1）顶顶顶顶（2）反反复复";
//		s.replace("(\\d+)","");

		String s = "item\\aa\\a.jpg";
		System.out.println(s.substring(s.lastIndexOf(".")+1));

//		for(char start='a';start<='z';start++){
//			System.out.println(start);
//		}

//		System.out.println(String.valueOf(""+'a'+'b'));

//		File file = new File("D:\\programs\\mysql-5.7.23-winx64\\data");
//		String s = file.getPath();
//		System.out.println(s);

//		System.out.println(trimStart("abcd_dddjfjls","abc"));


//		File file = new File("E:\\pms_config\\upload\\item\\attachment\\2020110220255983266.pdf");
//		String filePath = file.getPath();
//		String fileName = file.getName();
//		String localDir = new File("E:\\pms_config\\upload").getPath();
//
//		String relativeFilePath = trimStart(filePath,localDir);
//		String resourceRelativePath = trimEnd(relativeFilePath,fileName);
//
//		String result = resourceRelativePath.substring(1,resourceRelativePath.lastIndexOf(File.separator)).replace(File.separator,"-");
//
//		System.out.println(result);

//		Set<String> set = new HashSet<>();
//		set.add("A");
//		set.add("B");
//
//		set.stream().map(s -> s.toLowerCase()).distinct().collect(Collectors.toSet());
//
//		set.stream().forEach(System.out::println);

//		System.out.println(File.separator);
//
//		File file = new File("E:/Tmp/testUpload");
//		System.out.println(file.getPath());
//
//		File inputFile = new File("E:/Tmp/testUpload/testUpload.zip");
//		InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
//		ZipInputStream zin = new ZipInputStream(in, Charset.forName("gbk"));
//		ZipEntry ze;
//		while((ze = zin.getNextEntry()) != null ){
//			System.out.println(ze.getName());
//		}

//		checkArgs(1,"2");


	}




	private static <T> void checkArgs(T ... args) {
		if(args.length == 0){
			return;
		}
		for(T arg : args){
			Objects.requireNonNull(arg);
		}
	}

	public static String trimStart(String originalStr, String prefix) {
		if (originalStr == null || originalStr.length() == 0 || prefix == null || prefix.length() == 0) {
			return originalStr;
		}

		if (originalStr.startsWith(prefix)) {
			return (originalStr.substring(prefix.length()));
		}
		return originalStr;
	}

	public static String trimEnd(String originalStr, String suffix) {
		if (originalStr == null || originalStr.length() == 0 || suffix == null || suffix.length() == 0) {
			return originalStr;
		}

		if (originalStr.endsWith(suffix)) {
			return (originalStr.substring(0,originalStr.length()-suffix.length()));
		}
		return originalStr;
	}
	private static String match(String content,String reg){
		String fieldValue="";
		Pattern pattern = Pattern.compile(reg);// 匹配的模式
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			int i = 1;
			fieldValue=matcher.group(i);
			i++;
		}
		return fieldValue;
	}

	private int a(int b){
		return 0;
	}


}
