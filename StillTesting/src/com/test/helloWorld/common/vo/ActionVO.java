package com.test.helloWorld.common.vo;

import java.util.ArrayList;
import java.util.List;

public class ActionVO {

	private Long id = null;
	private String name = null;
	private String pinType = null;
	private List<PointPinActionVO> pointPinActionList = new ArrayList<PointPinActionVO>();
	
	public List<PointPinActionVO> getPointPinActionList() {
		return pointPinActionList;
	}
	public void setPointPinActionList(List<PointPinActionVO> pointPinActionList) {
		this.pointPinActionList = pointPinActionList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinType() {
		return pinType;
	}
	public void setPinType(String pinType) {
		this.pinType = pinType;
	}
	
	
}
