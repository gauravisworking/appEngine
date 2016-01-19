package com.test.helloWorld.common.vo;

import java.util.ArrayList;
import java.util.List;

public class BoardVO {

	private Long id = null;
	private String name = null;
	private Long roomFk = null;
	private Long deviceTypeFk = null;
	private Long deviceFk = null;
	private List<PointVO> pointList = new ArrayList<>();
	
	public List<PointVO> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointVO> pointList) {
		this.pointList = pointList;
	}
	public Long getDeviceFk() {
		return deviceFk;
	}
	public void setDeviceFk(Long deviceFk) {
		this.deviceFk = deviceFk;
	}
	public Long getDeviceTypeFk() {
		return deviceTypeFk;
	}
	public void setDeviceTypeFk(Long deviceTypeFk) {
		this.deviceTypeFk = deviceTypeFk;
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
	public Long getRoomFk() {
		return roomFk;
	}
	public void setRoomFk(Long roomFk) {
		this.roomFk = roomFk;
	}
	
}
