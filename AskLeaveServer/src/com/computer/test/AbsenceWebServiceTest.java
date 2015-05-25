package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.computer.entity.State;
import com.computer.net.Client;

public class AbsenceWebServiceTest {

	static String url = "http://172.28.27.26:8088/AskLeaveServer/absence/";
	 
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
//		queryByUid();
//		queryByTid();
	 del();
	 //	addAbsence();
			//	  updAbsence();
	}

	//ok
	public static void addAbsence() throws ClientProtocolException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", "1");
		params.put("tid", "3");
		params.put("sdate", "2015-05-16");
		params.put("edate", "2015-05-16");
		params.put("reason", "我要快乐--");
		params.put("reserve", "");
		String ss = Client.sendPost(url + "add", params);
		System.out.println(ss);
	}

	//
	public static void updAbsence() throws ClientProtocolException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "3");
		params.put("uid", "2");
		params.put("tid", "4");
		params.put("sdate", "2015-05-16");
		params.put("edate", "2015-05-13");
		params.put("reason", "travel");
		params.put("state", State.states[3]);
		params.put("tmsg", "i am a teacher");
		params.put("reserve", "");
		String ss = Client.sendPost(url + "upd", params);
		System.out.println(ss);
	}

	// ok
	public static void queryByUid() throws ClientProtocolException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", "1");
		String ss = Client.sendPost(url + "queryByUid", params);
		System.out.println(ss);
	}

	// ok
	public static void queryByTid() throws ClientProtocolException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("tid", "4");
		String ss = Client.sendPost(url + "queryByTid", params);
		System.out.println(ss);
	}
	// ok
	public static void del() throws ClientProtocolException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		String ss = Client.sendPost(url + "del", params);
		System.out.println(ss);
	}

}
