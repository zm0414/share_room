package com.wfuhui.modules.seat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.Session;
import com.wfuhui.modules.seat.dao.SeatDao;
import com.wfuhui.modules.seat.dao.SessionsDao;
import com.wfuhui.modules.seat.entity.SeatEntity;
import com.wfuhui.modules.seat.entity.SessionsEntity;
import com.wfuhui.modules.seat.service.SessionsService;



@Service("sessionsService")
public class SessionsServiceImpl implements SessionsService {
	@Autowired
	private SessionsDao sessionsDao;
	@Autowired
	private SeatDao seatDao;
	
	@Override
	public SessionsEntity queryObject(Integer id){
		return sessionsDao.queryObject(id);
	}
	
	@Override
	public List<SessionsEntity> queryList(Map<String, Object> map){
		return sessionsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sessionsDao.queryTotal(map);
	}
	
	@Override
	public void save(SessionsEntity sessions){
		sessionsDao.save(sessions);
	}
	
	@Override
	public void update(SessionsEntity sessions){
		sessionsDao.update(sessions);
	}
	
	@Override
	public void delete(Integer id){
		sessionsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sessionsDao.deleteBatch(ids);
	}

	@Override
	public void init(Date date, Integer storeId, Integer roomId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roomId", roomId);
		List<SeatEntity> seatList = seatDao.queryList(params);
		for (SeatEntity seat : seatList) {
			SessionsEntity sessions = new SessionsEntity();
			sessions.setReleaseDate(date);
			sessions.setCreateTime(new Date());
			sessions.setStoreId(storeId);
			sessions.setRoomId(roomId);
			sessions.setXcoord(seat.getXcoord());
			sessions.setYcoord(seat.getYcoord());
			sessions.setSeatNo(seat.getSeatNo());
			sessions.setPrice(seat.getPrice());
			sessions.setStatus(0);
			sessionsDao.save(sessions);
		}
	}
	
}
