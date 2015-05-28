package com.computer.entity;

/**
 * @author sq
 *  id states
 */
public class AState {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 审核状态 @ 标签一定要写
	 */
	private String states;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public AState() {}
	public AState(String states) {
		super();
		this.states = states;
	}
	
}
