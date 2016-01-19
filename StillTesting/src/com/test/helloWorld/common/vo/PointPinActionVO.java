package com.test.helloWorld.common.vo;

public class PointPinActionVO {

	private Long id = null;
	private Long pointFk = null;
	private String perform = null;
	private String value = null;
	private String pinNumber = null;
	private String pinType = null;
	private Long deviceTypeFk = null;
	private Long actionFk = null;
	private Long boardFk = null;
	private Long deviceFk = null;
	private String actionName = null;
	
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Long getDeviceFk() {
		return deviceFk;
	}
	public void setDeviceFk(Long deviceFk) {
		this.deviceFk = deviceFk;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPointFk() {
		return pointFk;
	}
	public void setPointFk(Long pointFk) {
		this.pointFk = pointFk;
	}
	public String getPerform() {
		return perform;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getPinType() {
		return pinType;
	}
	public void setPinType(String pinType) {
		this.pinType = pinType;
	}
	public Long getDeviceTypeFk() {
		return deviceTypeFk;
	}
	public void setDeviceTypeFk(Long deviceTypeFk) {
		this.deviceTypeFk = deviceTypeFk;
	}
	public Long getActionFk() {
		return actionFk;
	}
	public void setActionFk(Long actionFk) {
		this.actionFk = actionFk;
	}
	public Long getBoardFk() {
		return boardFk;
	}
	public void setBoardFk(Long boardFk) {
		this.boardFk = boardFk;
	}
	
	
}
