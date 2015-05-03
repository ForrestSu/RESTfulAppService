package com.computer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.computer.entity.Response;
import com.computer.entity.User;
import com.computer.util.Log;
import com.computer.util.SqTools;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	private static final Class<?> userClass=User.class;
	protected Log log=Log.getLog(userClass);
	
	@Override
	public Response<Integer> updatePassword(Integer id, String password,
			String oldPassword) {
		// TODO Auto-generated method stub
		Response<Integer> resp=new Response<Integer>();
		User user=(User) db.findById(userClass, id);
		
		 if(user==null)
		 {
			 log.i("用户不存在");
			 resp.setOperateResult(false);
			 resp.setObject(-1);
			 resp.setDescription("此用户不存在");
			 
			 return resp;
		 }
		 
		 if(!user.getPassword().equals(oldPassword))
		 {
			 log.i("旧密码错误");
			 resp.setOperateResult(false);
			 resp.setObject(-1);
			 resp.setDescription("旧密码错误");
			 return resp;
		 }
		 if(oldPassword==null||oldPassword.length()<4)
		 {
			 log.i("旧密码太短");
			 resp.setOperateResult(false);
			 resp.setObject(-1);
			 resp.setDescription("旧密码太短");
			 return resp;
		 }
		 
			user.setPassword(password);
			 
			int updateResult= db.updateById(user);
			
			if(updateResult<=0)
			{
				 log.i("数据库修改失败");
				 resp.setOperateResult(false);
				 resp.setObject(updateResult);
				 resp.setDescription("服务器异常，请稍后再试");
			}
			else
			{
				 log.i("修改成功");
				 resp.setOperateResult(true);
				 resp.setObject(updateResult);
				 resp.setDescription("修改密码成功");
			}
			return resp;
	}

	@Override
	public Response<Boolean> isExistUserName(String name) {
		// TODO Auto-generated method stub
		Response<Boolean> resp=new Response<Boolean>();
		User user=new User();
		user.setUserName(name);
		user.setValid(0);
		user=(User) db.findObject(user, null);
		if(user==null)
		{
			resp.setDescription("不存在此用户");
			resp.setObject(false);
		}
		else
		{
			resp.setDescription("存在此用户");
			resp.setObject(true);
		 
		}
		 return resp;
	}

	@Override
	public Response<User> login(String name, String password, Integer userType) {
		// TODO Auto-generated method stub
		
		Response<User> resp=new Response<User>();
		User user=new User();
		user.setUserName(name);
		user.setValid(0);
		user=(User) db.findObject(user, null);
		if(user==null)
		{
			resp.setDescription("不存在此用户");
			resp.setOperateResult(false);
			return resp;
		}
		
		if(user.getPassword().equals(password))
		{
			resp.setDescription("登录成功");
			resp.setObject(user);
			 resp.setOperateResult(true);
		}
		else
		{
			resp.setDescription("用户密码错误");
			 resp.setOperateResult(false);
		}
		return resp;
	}
	
	@Override
	public Response<User> register(String name, String password,
			Integer userType, String stunumber, String tel, String sex,
			String reserve) {
		Response<User> resp=new Response<User>();
		if(name==null||name.length()<2){
			log.i("用户名太短");
			resp.setDescription("用户名太短,至少为2位");
			resp.setOperateResult(false);
			return resp;
		}
		if(password==null||password.length()<4){
			log.i("密码太短");
			resp.setDescription("密码太短,至少为4位");
			resp.setOperateResult(false);
			return resp;
		}
		if(tel==null||SqTools.matchPhoneNumber(tel)==false){
			log.i("电话号码不合法");
			resp.setDescription("电话号码不合法");
			resp.setOperateResult(false);
			return resp;
		}
		User user=new User();
		user.setUserName(name);
		user.setValid(0);
		user=(User) db.findObject(user, null);
		if(user!=null)
		{
			log.i("用户名:"+name+"已被注册");
			resp.setDescription("用户名:"+name+"已被注册");
			resp.setOperateResult(false);
			return resp;
		}	
		String regdate=SqTools.getCurrentDate("yyyy-MM-dd HH:mm");
		user=new User(name, password, userType, stunumber, tel, sex, regdate, 0, reserve);
		user= (User) db.insert(user);
		 if(user==null)
		 {
			 log.i("数据库<user>插入失败");
			 resp.setDescription("服务器异常，无法注册，请稍后再试");
				resp.setOperateResult(false);
		 }
		 else
		 { 
			 resp.setDescription("注册成功");
			 resp.setOperateResult(true);
			 resp.setObject(user);
		 }
		return resp;
	}

	@Override
	public Response<List<User>> findByType(Integer userType) {
		Response<List<User>> resp=new Response<List<User>>();
		User user=new User();
		user.setUserType(userType);
		user.setValid(0);
		List<User> list=(List<User>)db.findObjects(user, null); 
		 if(list==null)
		 {
			 log.i("按类型查询用户记录0条！");
			 resp.setDescription("没有查到userType="+userType+"记录");
			 resp.setOperateResult(false);
		 }
		 else
		 { 
			 resp.setDescription("查询该类型用户记录");
			 resp.setOperateResult(true);
			 resp.setObject(list);
		 }
		return resp;
	}

	@Override
	public Response<Boolean> del(Integer id) {
		Response<Boolean> resp=new Response<Boolean>();
		User user=(User) db.findById(userClass, id);
		if(user==null)
		{
			 log.i("此id用户不存在");
			 resp.setOperateResult(false);
			 resp.setObject(false);
			 resp.setDescription("此id用户不存在");
			 return resp;
		}
		user.setValid(1);
		int updateResult= db.updateById(user);
		if(updateResult<=0)
		{
			 log.i("删除用户失败");
			 resp.setOperateResult(false);
			 resp.setObject(false);
			 resp.setDescription("服务器异常，请稍后再试");
		}
		else
		{
			 log.i("删除用户成功");
			 resp.setOperateResult(true);
			 resp.setObject(true);
			 resp.setDescription("用户删除成功");
		}
		return resp;
	}

}
