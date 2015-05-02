package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
 

import com.computer.net.Client;

public class StateWebServiceTest {
	
	static String url="http://127.0.0.1:8080/AskLeaveServer/state/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//testAddState();
		queryAllState();
	}
	//ok
	public static void testAddState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("states", "未审核");
		String ss=Client.sendPost(url+"add", params);
		System.out.println(ss);
	}
	
	//ok
	public static void TestDelstate() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", "5");
		String ss=Client.sendPost(url+"del", params);
		System.out.println(ss);
	}
	//ok
	public static void queryState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", "1");
		String ss=Client.sendPost(url+"query", params);
		System.out.println(ss);
	}
	//ok
	public static void queryAllState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
//		params.put("name", "sunquan");
		String ss=Client.sendPost(url+"queryall", params);
		System.out.println(ss);
	}
	//ok
	public static void testupdState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
 		params.put("id", "5");
 		params.put("states", "状态**-");
		String ss=Client.sendPost(url+"upd", params);
		System.out.println(ss);
	}
	
}
