package com.computer.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.computer.entity.Absence;
import com.computer.entity.Response;

@Produces("application/json")
public interface AbsenceService {
 
	
	@POST
	@Path("queryByUid")
	public Response<List<Absence>> queryByUid(
			@FormParam("uid")Integer uid
			);
	@POST
	@Path("queryByTid")
	public Response<List<Absence>> queryByTid(
			@FormParam("tid")Integer tid
			);
	@POST
	@Path("upd")
	public Response<Absence> upd(
			@FormParam("id")Integer id,
			@FormParam("tid")Integer tid,
			@FormParam("sdate")String sdate,
			@FormParam("edate")String edate,
			@FormParam("reason")String reason,
			@FormParam("state")String state,
			@FormParam("tmsg")String tmsg,
			@FormParam("reserve")String reserve
			);
	//id, uid,tid, sdate，edate ，reason,state, tmsg, reqdate reserve
	@POST
	@Path("del")
	public Response<Boolean>  del(
			@FormParam("id")Integer id
			);
	@POST
	@Path("add")
	public Response<Absence>  add(
			@FormParam("uid")Integer uid,
			@FormParam("tid")Integer tid,
			@FormParam("sdate")String sdate,
			@FormParam("edate")String edate,
			@FormParam("reason")String reason,
			@FormParam("reserve")String reserve
			);
	 
}
