package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
 

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.computer.entity.Response;
import com.computer.entity.User;
import com.computer.net.Client;

public class WebServiceTest {
	
	static String url="http://172.28.27.26:8088/AskLeaveServer/user/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// 	 TestRegister();
//	  testlogin();
		TestqueryByUserType();
	}
	
	private static void TestqueryByUserType() throws ClientProtocolException, IOException {
		Map<String, String> params=new HashMap<String, String>();
 		params.put("userType", "1");
		String ss=Client.sendPost(url+"findByType", params);
		System.out.println(ss);
		Response  resp =JSONObject.parseObject(ss,Response.class);
	    List<User> list=JSONArray.parseArray(resp.getObject().toString(), User.class);
	      
	     System.out.println(list.size());
	}

	public static void testlogin() throws ClientProtocolException, IOException 
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "sunquan");
	 	params.put("password", "123456");
	 	params.put("userType", "0");
		String ss=Client.sendPost(url+"login", params);
		System.out.println(ss);
//		Response resp=JSONObject.parseObject(ss,Response.class);
//		User  one=JSONObject.toJavaObject((JSONObject)resp.getObject(), User.class);
//		System.out.println(one.toString());
	}
	public static void TestRegister() throws ClientProtocolException, IOException 
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "孙权孙权");
	 	params.put("password", "1243");
	 	params.put("userType", "0");
	 	params.put("stunumber", "201141842121");
	 	params.put("tel", "18207302292");
	 	params.put("sex", "男");
	 	params.put("reserve", "");
		String ss=Client.sendPost(url+"register", params);
		System.out.println(ss);
	}
	
	public static void testUserExist() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "sunquan");
		String ss=Client.sendGet(url+"isExistUserName", params);
		System.out.println(ss);
	}
	public static void TestDel() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", "4");
		String ss=Client.sendGet(url+"del", params);
		System.out.println(ss);
	}
	
	public static void testFindUserByType() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("userType", "1");
		String ss=Client.sendPost(url+"findByType", params);
		System.out.println(ss);
	}
	
	public static void TestUpdatePasswd() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", "1");
 		params.put("password", "12345");
 		params.put("oldPassword", "09999");
		String ss=Client.sendPost(url+"updatePassword", params);
		System.out.println(ss);
	}
	
	
}
