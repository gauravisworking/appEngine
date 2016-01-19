package com.test.helloWorld.common.vo;

public class DeviceTypeVO {

	private Long id = null;
	private String name = null;
	private String digitalPins = null;
	private String analogPins = null;
	private String pwmPins = null;
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
	public String getDigitalPins() {
		return digitalPins;
	}
	public void setDigitalPins(String digitalPins) {
		this.digitalPins = digitalPins;
	}
	public String getAnalogPins() {
		return analogPins;
	}
	public void setAnalogPins(String analogPins) {
		this.analogPins = analogPins;
	}
	public String getPwmPins() {
		return pwmPins;
	}
	public void setPwmPins(String pwmPins) {
		this.pwmPins = pwmPins;
	}
	

}
