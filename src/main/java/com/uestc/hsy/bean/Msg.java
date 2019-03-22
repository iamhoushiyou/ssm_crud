package com.uestc.hsy.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * return message about crud
 * @author Shiyou Houn
 *
 */
public class Msg {
    
	//state code 100-success 200-fail
	private int code;
	//message 
	private String msg;
	//  user data
	private Map<String,Object> extend = new HashMap<>();
	
	
	public static Msg success(){
		Msg result=new Msg();
		result.setCode(100);
		result.setMsg("resolve success!");
		return result;
	}
	public static Msg fail(){
		Msg result=new Msg();
		result.setCode(200);
		result.setMsg("resolve fail!");
		return result;
	}

    public Msg add(String key,Object value){
    	this.getExtend().put(key, value);
    	return this;
    }
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Map<String, Object> getExtend() {
		return extend;
	}


	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
}
