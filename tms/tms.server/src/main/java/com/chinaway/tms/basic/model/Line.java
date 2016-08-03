package com.chinaway.tms.basic.model;

import java.io.Serializable;
import java.util.Date;

import com.chinaway.tms.utils.json.JsonUtil;

public class Line implements Serializable {
	
	private static final long serialVersionUID = 7009433621776630334L;
	
	private Integer id=0;    //   主键
	
	@Override
	public String toString() {
		return "Line [id=" + id + ", pdeptname=" + pdeptname + ", deptname=" + deptname + ", code=" + code + ", name="
				+ name + ", startsite=" + startsite + ", endsite=" + endsite + ", halfwaysite=" + halfwaysite
				+ ", runtime=" + runtime + ", mileage=" + mileage + ", vehicleline=" + vehicleline + ", updatetime="
				+ updatetime + ", fromcode=" + fromcode + ", linetype=" + linetype + ", transtype=" + transtype
				+ ", transmoney=" + transmoney + ", operates=" + operates + ", roadtoll=" + roadtoll + "]";
	}
	
	public static void main(String args[]){
		Line line = new Line();
		System.out.println(JsonUtil.obj2JsonStr(line));
	}
	
}
