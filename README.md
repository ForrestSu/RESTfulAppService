﻿# RESTfulAppService
This program based on RESTful Architecture ,It can provides data services to the android or IOS app. 

#How to use
 You can use it  via HTTP requests. We can get Response by request the url.
* 在浏览器中输入下面的url:(only test get request by this way)
eg:``http://localhost:8088/AskLeaveServer/user/isExistUserName?name=sunquan``
* 服务器返回json 格式的数据<br>
``{"description":"存在此用户","errorCode":0,"object":true,"operateResult":true}``

#Sample 
```Java
 static String url="http://localhost:8088/AskLeaveServer/user/";
 public static void testlogin() 
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "sq");
	 	params.put("password", "123456");
	 	params.put("userType", "0");
		String ss=Client.sendPost(url+"login", params);
		System.out.println(ss);
	}
	public static void TestRegister() 
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "sunquan");
	 	params.put("password", "123456");
	 	params.put("userType", "0");
	 	params.put("stunumber", "2011418421");
	 	params.put("tel", "18207302292");
	 	params.put("sex", "男");
	 	params.put("reserve", "");
		String ss=Client.sendPost(url+"register", params);
		System.out.println(ss);
	}
```
* 客户端按照下面的方法处理服务器端响应数据
```Java
    Response  resp =JSONObject.parseObject(ss,Response.class);
    User user=JSONObject.toJavaObject((JSONObject)resp.getObject(),User.class);
    // 如果返回的对象不止一个
    Response  resp =JSONObject.parseObject(ss,Response.class);
    List<User> list=JSONArray.parseArray(resp.getObject().toString(), User.class);
```
#DB Graph
![image](https://github.com/ForrestSu/RESTfulAppService/blob/master/docs/er.png)<br>
注：虚线箭头最后实现
#Author 
 Quan Sun,Qidong Tan

#Reference
理解RESTful架构-阮一峰
http://www.ruanyifeng.com/blog/2011/09/restful.html
