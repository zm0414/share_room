package com.wfuhui.modules.seat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wfuhui.modules.seat.dao.RoomDao;
import com.wfuhui.modules.seat.dao.RoomPicDao;
import com.wfuhui.modules.seat.dao.SeatDao;
import com.wfuhui.modules.seat.dao.HistoryDao;
import com.wfuhui.modules.seat.entity.RoomEntity;
import com.wfuhui.modules.seat.entity.RoomPicEntity;
import com.wfuhui.modules.seat.entity.SeatEntity;
import com.wfuhui.modules.seat.service.RoomService;
import com.wfuhui.modules.store.dao.StoreDao;
import com.wfuhui.modules.member.dao.MemberDao;


@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private RoomPicDao roomPicDao;
	@Autowired
	private HistoryDao historyDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private StoreDao storeDao;
	
	@Override
	public RoomEntity queryObject(Integer id){
		RoomEntity room = roomDao.queryObject(id);
		String[] picUrls = roomPicDao.queryByRoomId(id);
		room.setPicUrls(picUrls);
		//List<SeatEntity> seatList = seatDao.queryByRoomId(id);
		//room.setSeatList(seatList);
		room.setStore(storeDao.queryObject(room.getStoreId()));
		return room;
	}
	
	@Override
	public List<RoomEntity> queryList(Map<String, Object> map){
		List<RoomEntity> roomList = roomDao.queryList(map);
		return roomList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return roomDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(RoomEntity room){
		roomDao.save(room);
		if (room.getPicUrls() != null) {
			String[] picUrls = room.getPicUrls();
			for (String picUrl : picUrls) {
				RoomPicEntity roomPic = new RoomPicEntity();
				roomPic.setRoomId(room.getId());
				roomPic.setPicUrl(picUrl);
				roomPicDao.save(roomPic);
			}
		}
		int x = 6;
		int y = 6;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				SeatEntity seat = new SeatEntity();
				seat.setRoomId(room.getId());
				seat.setStatus(0);
				seat.setXcoord(j+1);
				seat.setYcoord(i+1);
				seatDao.save(seat);
			}
		}
	}
	
	@Override
	public void update(RoomEntity room){
		if (room.getPicUrls() != null) {
			String[] picUrls = roomPicDao.queryByRoomId(room.getId());
			boolean e = isQualsPic(picUrls, room.getPicUrls());
			if (!e) {
				roomPicDao.deleteByRoomId(room.getId());
				for (String picUrl : room.getPicUrls()) {
					RoomPicEntity roomPic = new RoomPicEntity();
					roomPic.setRoomId(room.getId());
					roomPic.setPicUrl(picUrl);
					roomPicDao.save(roomPic);
				}
			}
		}
		roomDao.update(room);
	}
	
	private boolean isQualsPic(String[] picUrls, String[] picUrls2) {
		if (picUrls.length == picUrls2.length) {
			for (int i = 0; i < picUrls.length; i++) {
				if (!picUrls[i].equals(picUrls2[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void delete(Integer id){
		roomDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		roomDao.deleteBatch(ids);
	}
	
	@Override
	public void updateStatus(Integer status, Integer[] ids) {
		roomDao.updateStatus(status, ids);
	}
	
	
	/**
	 * 可能喜欢
	 * */
	@Override
    public List<RoomEntity> getLike(Long userId) {
		
        return new ArrayList<RoomEntity>();
    }
}
