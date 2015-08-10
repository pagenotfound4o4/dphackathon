package main.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.dao.TimelineDao;
import main.dao.UserDao;
import main.model.Timeline;
import main.utils.ConfirmRet;
import main.utils.ExTimeline;

@Path("/timeline")
public class TimelineService {
	private TimelineDao tdao;
	public TimelineService() {
		this.tdao = new TimelineDao();
	}
	
	@GET
	@Path("/buy/{hid}/{bid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConfirmRet buy(@PathParam("hid") String hid, @PathParam("bid") String bid) {
		UserDao userDao = new UserDao();
		Timeline timeline = new Timeline();
		timeline.setHid(Integer.valueOf(hid));
		timeline.setGid(Integer.valueOf(userDao.findUserByHid(Integer.valueOf(hid)).getLover()));
		timeline.setBid(bid);
		timeline.setTime(new Date());
		timeline.setFlag(0);
		TimelineDao timelineDao = new TimelineDao();
		timelineDao.saveTimeline(timeline);
		return new ConfirmRet(0, "OK", 0);
	}
	
	@GET
	@Path("/check/confirm/{hid}/{bid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConfirmRet checkConfirm(@PathParam("hid") String hid, @PathParam("bid") String bid) {
		TimelineDao timelineDao = new TimelineDao();
		List<Timeline> list = timelineDao.findByHid(Integer.valueOf(hid), Integer.valueOf(bid));
		if (!list.isEmpty() && list.get(0).getFlag() == 1) {
			return new ConfirmRet(0, "confirmed", Integer.parseInt(list.get(0).getBid()));
		}
		return new ConfirmRet(1, "continue", 0);
	}
	
	@GET
	@Path("/check/invite/{gid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConfirmRet checkInvite(@PathParam("gid") String gid) {
		TimelineDao timelineDao = new TimelineDao();
		List<Timeline> list = timelineDao.findByGid(Integer.valueOf(gid));
		
		if (!list.isEmpty() && list.get(0).getFlag() == 0) {
			return new ConfirmRet(0, "invite", Integer.parseInt(list.get(0).getBid()));
		}
		return new ConfirmRet(1, "", 0);
	}
	
	@GET
	@Path("/confirm/{gid}/{bid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConfirmRet confirm(@PathParam("gid") String gid, @PathParam("bid") String bid) {
		return tdao.confirm(gid, bid);
	}
	
	@GET
	@Path("/get/all/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExTimeline> getAll(@PathParam("id") String id) {
		return tdao.getAll(id);
	}
}
