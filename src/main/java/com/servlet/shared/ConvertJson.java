package com.servlet.shared;

import com.google.gson.Gson;

public class ConvertJson {
	public String toJsonString(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
	
}
