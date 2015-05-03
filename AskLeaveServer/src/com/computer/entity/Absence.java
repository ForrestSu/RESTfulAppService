/**
 * author:tanqidong
 * create Time:2015年4月27日,上午9:05:16
 * description:
 * fileName:CommentInfo.java	
 */
package com.computer.entity;


import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Id;

import com.alibaba.fastjson.FastJsonType;

/**
 * @author sq id, uid,tid, sdate，edate ，reason,state, statemsg, reqdate reserve
 */
@FastJsonType
public class Absence {
	/**
	 * id
	 */
	@Id(auto = true)
	private Integer id;
	/**
	 * 用户uid
	 */
	private Integer uid;
	/**
	 * 用户tid
	 */
	private Integer tid;
	/**
	 * 请假开始时间
	 */
	private String sdate;
	/**
	 * 请假结束时间
	 */
	private String edate;
	/**
	 * 申请时间
	 */
	private String reqdate;

	@ColDefine(type = ColType.TEXT)
	private String reason;

	private String state;

	/**
	 * 批准状态
	 */
	@ColDefine(type = ColType.TEXT)
	private String tmsg;
	/**
	 * 保留字段
	 */
	private String reserve;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getReqdate() {
		return reqdate;
	}

	public void setReqdate(String reqdate) {
		this.reqdate = reqdate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	 

	public String getTmsg() {
		return tmsg;
	}

	public void setTmsg(String tmsg) {
		this.tmsg = tmsg;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
 
	public Absence(Integer uid, Integer tid, String sdate, String edate,
			String reqdate, String reason, String state, String tmsg,
			String reserve) {
		super();
		this.uid = uid;
		this.tid = tid;
		this.sdate = sdate;
		this.edate = edate;
		this.reqdate = reqdate;
		this.reason = reason;
		this.state = state;
		this.tmsg = tmsg;
		this.reserve = reserve;
	}

	/**
	 * 
	 */
	public Absence() {
		// TODO Auto-generated constructor stub
	}
}
