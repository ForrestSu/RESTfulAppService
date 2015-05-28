/**
 * author:tanqidong
 * create Time:2015年4月27日,下午3:34:22
 * description:
 * fileName:User.java	
 */
package com.computer.entity;

 

/**
 * @author  
 * id  用户名， 密码，   用户类型， 学号，电话号码，sex，reserve，
 * id  userName  password userType stunumber tel sex reserve
 */
 
public class User {
 
	private Integer id;
 
	private String userName;
	private String password;
	
	/**
	 *用户类型 l  0 stu  1 teacher
	 */
	
	private Integer userType;
	/**
	 * 学号
	 */
	private String stunumber;
	/**
	 * 电话号码
	 */
	private String tel;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 注册时间
	 */
	private String regdate;
	
	/**
	 * 是否注销
	 */
	private int  valid;
	/**
	 *  保留
	 */
	private String reserve;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getStunumber() {
		return stunumber;
	}
	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public User(){};//空的构造方法
	 
	
	public User(String userName, String password, Integer userType,
			String stunumber, String tel, String sex, String regdate,Integer valid,
			String reserve) {
		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.stunumber = stunumber;
		this.tel = tel;
		this.sex = sex;
		this.regdate = regdate;
		this.valid=valid;
		this.reserve = reserve;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userName+password;
	}
}
