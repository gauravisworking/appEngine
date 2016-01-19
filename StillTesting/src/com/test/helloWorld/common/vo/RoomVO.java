package com.test.helloWorld.common.vo;

import java.util.List;

public class RoomVO {

	private Long id=null;
	private String name=null;
	private Long houseFk = null;
	private List<BoardVO> boardList = null;
	
	public List<BoardVO> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}
	private List<PointVO> pointVOList = null;
	
	
	public List<PointVO> getPointVOList() {
		return pointVOList;
	}
	public void setPointVOList(List<PointVO> pointVOList) {
		this.pointVOList = pointVOList;
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
	public Long getHouseFk() {
		return houseFk;
	}
	public void setHouseFk(Long houseFk) {
		this.houseFk = houseFk;
	}
	
	
}
