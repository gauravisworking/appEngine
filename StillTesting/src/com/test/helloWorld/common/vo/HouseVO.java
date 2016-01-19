package com.test.helloWorld.common.vo;

import java.util.List;

public class HouseVO {

	private String id=null;
	private String name = null;
	private String address1=null;
	private String address2=null;
	private String address = null;
	
	private String city = null;
	private String state = null;
	private String phoneNumber = null;
	private boolean leaf;
	private String text = null;
	
	private List<UserVO> userList = null;
	
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	private List<RoomVO> roomVOList = null;

	
	public List<RoomVO> getRoomVOList() {
		return roomVOList;
	}
	public void setRoomVOList(List<RoomVO> roomVOList) {
		this.roomVOList = roomVOList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.text = name;
	}
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
