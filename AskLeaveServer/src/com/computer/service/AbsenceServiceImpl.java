package com.computer.service;

 
import java.util.List;
 

import org.springframework.stereotype.Service;

import com.computer.entity.Absence;
import com.computer.entity.Response;
import com.computer.entity.State;
import com.computer.entity.User;
import com.computer.util.Log;
import com.computer.util.SqTools;

@Service
public class AbsenceServiceImpl extends BaseService implements AbsenceService {
	private static final Class<?> absenceClass = Absence.class;
	protected Log log = Log.getLog(absenceClass);

	@Override
	public Response<List<Absence>> queryByUid(Integer uid) {
		Response<List<Absence>> resp=new Response<List<Absence>>();
		Absence absence=new Absence();
		absence.setUid(uid);
		List<Absence> list=(List<Absence>)db.findObjects(absence, null); 
		 if(list==null)
		 {
			 log.i("查到请假记录0条!");
			 resp.setDescription("没有查到uid="+uid+"记录");
			 resp.setOperateResult(false);
		 }
		 else
		 { 
			 resp.setDescription("查询到"+list.size()+"条记录");
			 resp.setOperateResult(true);
			 resp.setObject(list);
		 }
		return resp;
	}

	@Override
	public Response<List<Absence>> queryByTid(Integer tid) {
		Response<List<Absence>> resp=new Response<List<Absence>>();
		//更新当前教师所有状态： 未审核->审核中
		db.updateByState(absenceClass, tid, State.states[1], State.states[2]);
		//
		Absence absence=new Absence();
		absence.setTid(tid);
		List<Absence> list=(List<Absence>)db.findObjects(absence, null); 
		 if(list==null)
		 {
			 log.i("查到请假记录0条!");
			 resp.setDescription("没有查到uid="+tid+"记录");
			 resp.setOperateResult(false);
		 }
		 else
		 { 
			 resp.setDescription("查询到"+list.size()+"条记录");
			 resp.setOperateResult(true);
			 resp.setObject(list);
		 }
		return resp;
	}

	@Override
	public Response<Absence> upd(Integer id, Integer tid, String sdate,
			String edate, String reason, String state, String tmsg,
			String reserve) {
		Response<Absence> resp = new Response<Absence>();
		resp.setOperateResult(false);
		Absence absence = (Absence) db.findById(absenceClass, id);
		if (absence == null) {
			log.i("absence表不存在id");
			
			resp.setDescription("不存在id=" + id + "的记录");
			return resp;
		}
		if(sdate==null||sdate.length()<4||edate==null||edate.length()<4)
		{
			resp.setDescription("请假日期不合法");
			return resp;
		}
		if(edate.compareTo(sdate)<0)
		{
			resp.setDescription("请保持日期格式一致，结束日期不能小于起始日期");
			return resp;
		}
		absence.setTid(tid);
		absence.setSdate(sdate);
		absence.setEdate(edate);
		absence.setReason(reason);
		absence.setState(state);
		absence.setTmsg(tmsg);
		absence.setReserve(reserve);
		
		int updateResult = db.updateById(absence);

		if (updateResult <= 0) {
			log.i("数据库修改失败");
			resp.setDescription("服务器异常，请稍后再试");
		} else {
			log.i("修改申请成功");
			resp.setOperateResult(true);
			resp.setObject(absence);
			resp.setDescription("修改申请成功");
		}
		return resp;
	}

	@Override
	public Response<Boolean> del(Integer id) {
		Response<Boolean> resp=new Response<Boolean>();
		Absence absence=(Absence) db.findById(absenceClass, id);
		resp.setOperateResult(false);
		if (absence == null) {
			log.i("absence表不存在id");
			resp.setDescription("absence表不存在id="+id);
			return resp;
		}
		if(!absence.getState().equals(State.states[1]))
		{
			log.i("请假记录已投递，不能撤回");
			resp.setDescription("请假记录已投递，不能撤回");
			return resp;
		}
		Boolean ret = db.deleteById(absenceClass, id) > 0;
		if (ret)
		{
			log.i("撤回请假申请成功");
			resp.setOperateResult(true);
			resp.setDescription("撤回请假申请成功");
		}
		else {
			log.i("撤回请假申请失败");
			resp.setDescription("撤回请假申请失败");
		}
		return resp;
	}

	@Override
	public Response<Absence> add(Integer uid, Integer tid, String sdate,
			String edate, String reason,   String reserve) {
		Response<Absence> resp = new Response<Absence>();
		resp.setOperateResult(false);
		User user=new User();
		user.setId(uid);
		user.setValid(0);
		user.setUserType(0);
		if(db.findObject(user, null)==null)
		{
			resp.setDescription("当前用户id不存在,且老师不能申请请假");
			return resp;
	    }
		if(sdate==null||sdate.length()<4||edate==null||edate.length()<4)
		{
			resp.setDescription("请假日期不合法");
			return resp;
		}
		if(edate.compareTo(sdate)<0)
		{
			resp.setDescription("请保持日期格式一致，结束日期不能小于起始日期");
			return resp;
		}
		if(reason==null|| reason.length()<4)
		{
			resp.setDescription("请假理由太短！");
			return resp;
		}
		
		String reqdate=SqTools.getCurrentDate("yyyy-MM-dd HH:mm");// 获取服务器 当前日期
        String state=State.states[1];
		Absence absence = new Absence(uid, tid, sdate, edate, reqdate, reason,
				state, "", reserve);
		absence = (Absence) db.insert(absence);
		if (absence == null) {
			log.i("数据库<absence>插入失败");
			resp.setDescription("服务器异常，无法注册，请稍后再试");
		} else {
			resp.setDescription("已提交申请,等待审核");
			resp.setOperateResult(true);
			resp.setObject(absence);
		}
		return resp;
	}

}
