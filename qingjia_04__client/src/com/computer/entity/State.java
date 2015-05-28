package com.computer.entity;

import java.util.HashMap;
import java.util.Map;

public class State {
	public static String[] states= { "其他状态", "未审核", "审核中", "已通过", "未通过" };
	public static Map<String, Integer> AllState = new HashMap<String, Integer>();

	public static int getStateIndex(String key) {
		if (AllState.size() == 0) {
          for(int i=0;i<states.length;i++)
        	  AllState.put(states[i], i);
		}
		return AllState.containsKey(key) ? AllState.get(key) : 0;
	}
}
