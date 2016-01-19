package com.test.helloWorld.common.vo;

import java.util.ArrayList;
import java.util.List;

public class PointVO {

	private Long id = null;
	private String name = null;
	private Long boardFk = null;
	private Long pointTypeFk = null;
	private List<PointPinActionVO> pointPinActionList = new ArrayList<>();
	
	 
	public List<PointPinActionVO> getPointPinActionList() {
		return pointPinActionList;
	}
	public void setPointPinActionList(List<PointPinActionVO> pointPinActionList) {
		this.pointPinActionList = pointPinActionList;
	}
	public Long getPointTypeFk() {
		return pointTypeFk;
	}
	public void setPointTypeFk(Long pointTypeFk) {
		this.pointTypeFk = pointTypeFk;
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
	public Long getBoardFk() {
		return boardFk;
	}
	public void setBoardFk(Long boardFk) {
		this.boardFk = boardFk;
	}
	
	
}
