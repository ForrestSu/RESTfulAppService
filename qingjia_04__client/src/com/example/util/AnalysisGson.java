package com.example.util;

import com.alibaba.fastjson.JSONObject;
import com.computer.entity.Response;
import com.computer.entity.User;

public class AnalysisGson {
     
	  public User parseLogin(String result){
		  
		  Response resp = JSONObject.parseObject(result,Response.class);
		  if(resp.getOperateResult()==false)
		  {
			  return null;
		  }
		  User one=JSONObject.toJavaObject((JSONObject)resp.getObject(), User.class);
		  return one;
	  }
}
