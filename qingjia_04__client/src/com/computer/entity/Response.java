package com.computer.entity;


public class Response <T>{
	
	private Boolean operateResult=true;
	private T object;
	private String description;
	private int errorCode;
	
	public Boolean getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(Boolean operateResult) {
		this.operateResult = operateResult;
	}
	
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
