package main.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.dao.BindDao;
import main.dao.TmpDao;
import main.utils.BindRet;

@Path("/user")
public class BindService {
	
	private TmpDao tdao;
	public BindService() {
		tdao = new TmpDao();
	}
	
	@GET
	@Path("/bind/{userId}/{lat}/{lng}")
	@Produces(MediaType.APPLICATION_JSON)
	public BindRet bind(@PathParam("userId") String userId, 
					@PathParam("lat") String lat, @PathParam("lng") String lng) {
		return tdao.getSetUid(Integer.parseInt(userId));
	}
}


