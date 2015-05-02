package com.computer.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.computer.entity.Response;
import com.computer.entity.AState;

@Produces("application/json")
public interface AStateService {

	@POST
	@Path("queryall")
	public Response<List<AState>> queryAllState();
	@POST
	@Path("query")
	public Response<String> queryState(
			@FormParam("id")Integer id
			);
	
	@POST
	@Path("del")
	public Response<Boolean>  delState(
			@FormParam("id")Integer id
			);
	@POST
	@Path("add")
	public Response<AState>  addState(
			@FormParam("states")String states
			);
	@POST
	@Path("upd")
	public Response<Integer>  updState(
			@FormParam("id")Integer id,
			@FormParam("states")String newstate
			);
}
