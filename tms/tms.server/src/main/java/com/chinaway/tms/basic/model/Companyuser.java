package com.chinaway.tms.basic.model;

import java.io.Serializable;
import java.util.Date;
import com.chinaway.tms.utils.json.JsonUtil;

public class Companyuser implements Serializable {
	
	private static final long serialVersionUID = -8763212651178091811L;
	
	private Integer id = 0;//   主键
	
	
	public static void main(String args[]){
		Companyuser companyuser = new Companyuser();
		System.out.println(JsonUtil.obj2JsonStr(companyuser));
	}
}